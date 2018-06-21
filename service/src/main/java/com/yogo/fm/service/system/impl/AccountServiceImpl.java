package com.yogo.fm.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.redis.RedisKeys;
import com.yogo.fm.common.utils.BeanUtils;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.common.utils.FmPageUtils;
import com.yogo.fm.common.utils.MD5Utils;
import com.yogo.fm.mapper.system.IAccountMapper;
import com.yogo.fm.model.system.AccountEntity;
import com.yogo.fm.model.system.RoleEntity;
import com.yogo.fm.queue.ExchangeEnum;
import com.yogo.fm.queue.QueueEnum;
import com.yogo.fm.queue.QueueProvider;
import com.yogo.fm.service.IRedisHelper;
import com.yogo.fm.service.annotation.SolrHandle;
import com.yogo.fm.service.system.IAccountService;
import com.yogo.fm.service.system.IRoleService;
import com.yogo.fm.solr.SolrHandleType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-18
 */
@Service
public class AccountServiceImpl implements IAccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final QueueProvider queueProvider;
    private final IAccountMapper accountMapper;
    private final IRedisHelper<AccountEntity> redisHelper;
    private final IRoleService roleService;

    @Autowired

    public AccountServiceImpl(QueueProvider queueProvider, IAccountMapper accountMapper, IRedisHelper<AccountEntity> redisHelper, IRoleService roleService) {
        this.queueProvider = queueProvider;
        this.accountMapper = accountMapper;
        this.redisHelper = redisHelper;
        this.roleService = roleService;
    }

    @Override
    public AccountEntity findByUsername(String username) {
        return accountMapper.findByUsername(username);
    }

    @Override
    public AccountEntity findByTokenFromCache(String token) {
        return redisHelper.getValue(RedisKeys.TOKEN_ACCOUNT + "_" + token);
    }

    @Override
    @CachePut(value = RedisKeys.TOKEN_ACCOUNT, key = "'token_account_'+#token")
    public AccountEntity setIntoCacheByToken(String token, AccountEntity account) {
        return account;
    }

    @Override
    @CacheEvict(value = RedisKeys.TOKEN_ACCOUNT, key = "'token_account_'+#token")
    public void clearCacheByToken(String token) {

    }

    @Override
    public AccountEntity findByMobile(String mobile) {
        return accountMapper.findByMobile(mobile);
    }

    @Override
    public List<AccountEntity> findAll() {
        return accountMapper.findAll();
    }


    @Override
    @SolrHandle(type = SolrHandleType.ADD,exchange = ExchangeEnum.ACCOUNT_EXCHANGE,queue =QueueEnum.ACCOUNT_ADD)
    public AccountEntity save(AccountEntity entity) throws Exception {
        if (entity == null) {
            throw FmException.error("参数错误");
        }
        if (StringUtils.isBlank(entity.getUsername())) {
            throw FmException.error("用户名不能为空");
        }
        if (StringUtils.isBlank(entity.getPassword())) {
            throw FmException.error("密码不能为空");
        }
        if (entity.getPassword().length() < 6) {
            throw FmException.error("密码不能小于6位");
        }
        if (StringUtils.isBlank(entity.getMobile())) {
            throw FmException.error("手机号不能为空");
        }
        AccountEntity username = findByUsername(entity.getUsername());
        if (username != null) {
            throw FmException.error("用户名已存在");
        }
        AccountEntity mobile = findByMobile(entity.getMobile());
        if (mobile != null) {
            throw FmException.error("该手机已经注册过");
        }
        //密码加密处理
        String salt = MD5Utils.salt();
        entity.setSalt(salt);
        entity.setPassword(MD5Utils.generate(entity.getPassword(), salt));
        BeanUtils.onInsert(entity);
        RoleEntity role = roleService.find(entity.getRoleId());
        entity.setRoleName(role.getName());
        accountMapper.save(entity);
        return entity;
    }

    @Override
    public AccountEntity delete(Long id) {
        accountMapper.delete(id);
        return null;
    }

    @Override
    public List<AccountEntity> batchDelete(List<Long> id) throws FmException {
        if (CollectionUtils.isEmpty(id)) {
            throw FmException.error("参数错误");
        }
        accountMapper.batchDelete(id);
        return null;
    }

    @Override
    public AccountEntity update(AccountEntity entity) throws FmException {
        if (entity == null || entity.getId() == null) {
            throw FmException.error("参数错误");
        }
        accountMapper.update(entity);
        return entity;
    }

    @Override
    public AccountEntity find(Long id) {
        return accountMapper.find(id);
    }

    @Override
    public List<AccountEntity> batchSave(List<AccountEntity> list) {
        return null;
    }

    @Override
    public FmPage<AccountEntity> findPage(AccountEntity condition, FmPage<AccountEntity> page) throws FmException {
        PageHelper.startPage(page.getPage(), page.getPageSize(), "createTime " + "desc");
        List<AccountEntity> allUser = accountMapper.findAllByCondition(condition);
        FmPageUtils.fmPageHandler(page, new PageInfo<>(allUser));
        return page;
    }

    @Override
    public void importExcel(List<AccountEntity> list) throws FmException {
        if (list == null || list.size() == 0) {
            throw FmException.error("参数错误");
        }
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(list.get(0).getMobile());
        }
        List<String> accountEntities = accountMapper.findInfoByMobile(list1);

        if (accountEntities != null || accountEntities.size() != 0) {
            throw FmException.error("手机号码重复");
        }
        accountMapper.batchSave(list);
    }

}

