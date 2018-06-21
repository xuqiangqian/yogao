package com.yogo.fm.admin.interceptor;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.redis.RedisKeys;
import com.yogo.fm.common.response.FmResponseCode;
import com.yogo.fm.common.utils.Base64Utils;
import com.yogo.fm.common.utils.ThreadLocals;
import com.yogo.fm.model.system.AccountEntity;
import com.yogo.fm.service.IRedisHelper;
import com.yogo.fm.service.system.IAccountService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 作草分茶
 * @Description 拦截器配置
 * @date 2018-04-29
 */
public class FmInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(FmInterceptor.class);

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IRedisHelper redisHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        logger.info("token : " + token);
        if (StringUtils.isBlank(token)) {
            throw new FmException(FmResponseCode.ACCOUNT_UN_LOGIN);
        }
        AccountEntity account = accountService.find(Long.parseLong(Base64Utils.decode(token.substring(32, token.length() - 1))));
        //如果redis中存在该账号的token，且两个token不一致，则提示账号在其它地方登陆
        if (redisHelper.hasKey(RedisKeys.TOKEN_ACCOUNT + "_" + account.getToken()) && !StringUtils.equals(account.getToken(), token)) {
            throw new FmException(FmResponseCode.ACCOUNT_LOGINED_BY_OTHER);
        }
        if (!redisHelper.hasKey(RedisKeys.TOKEN_ACCOUNT + "_" + account.getToken()) || !redisHelper.hasKey(RedisKeys.TOKEN_ACCOUNT + "_" + token)) {
            throw new FmException(FmResponseCode.ACCOUNT_UN_LOGIN);
        }
        ThreadLocals.tokenLocal.set(token);
        ThreadLocals.userLocal.set(account);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocals.userLocal.remove();
        ThreadLocals.tokenLocal.remove();
    }
}
