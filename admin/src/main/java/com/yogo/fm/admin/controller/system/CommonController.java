package com.yogo.fm.admin.controller.system;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.common.response.FmResponseCode;
import com.yogo.fm.common.utils.Base64Utils;
import com.yogo.fm.common.utils.MD5Utils;
import com.yogo.fm.common.utils.ThreadLocals;
import com.yogo.fm.model.system.AccountEntity;
import com.yogo.fm.service.system.IAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-18
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    private final IAccountService accountService;

    @Autowired
    public CommonController(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * PC后台登录
     *
     * @param username
     * @param password
     * @return 将token传给前台
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public FmResponse<String> login(String username, String password) throws Exception {
        //登录验证
        AccountEntity accountEntity = accountService.findByUsername(username);
        if (accountEntity == null) {
            throw new FmException(FmResponseCode.ACCOUNT_NOT_EXIST);
        }
        if (!MD5Utils.verify(password, accountEntity.getPassword())) {
            throw new FmException(FmResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        if (!accountEntity.getFlag()) {
            throw new FmException(FmResponseCode.ACCOUNT_IS_LOCKED);
        }
        return FmResponse.ok("登录成功", createToken(accountEntity));
    }

    /**
     * 如果之前登录的token尚未过期，则清除之前的token，生成token，将用户信息存入redis
     * 生成权限信息，存储到redis里
     *
     * @param accountEntity
     * @return
     * @throws FmException
     */
    private String createToken(AccountEntity accountEntity) throws FmException, UnsupportedEncodingException {
        //如果之前登录的token尚未过期，则清除之前的token，生成token，将用户信息存入redis
        String token = accountEntity.getToken();
        if (StringUtils.isNotBlank(token)) {
            accountService.clearCacheByToken(token);
        }
        token = MD5Utils.uuid()+Base64Utils.encode(String.valueOf(accountEntity.getId()));
        accountEntity.setToken(token);
        accountService.setIntoCacheByToken(token,accountEntity);
        accountService.update(accountEntity);
        ThreadLocals.userLocal.set(accountEntity);
        return token;
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public FmResponse<AccountEntity> logout() {
        accountService.clearCacheByToken(ThreadLocals.tokenLocal.get());
        return FmResponse.ok("退出成功");
    }
}
