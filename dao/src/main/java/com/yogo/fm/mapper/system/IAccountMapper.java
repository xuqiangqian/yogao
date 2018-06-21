package com.yogo.fm.mapper.system;

import com.yogo.fm.model.system.AccountEntity;
import com.yogo.fm.mapper.IBaseMapper;

import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-18
 */
public interface IAccountMapper extends IBaseMapper<AccountEntity> {
    /**
     * 通过用户名查找账号
     * @param username
     * @return
     */
    AccountEntity findByUsername(String username);

    /**
     * 通过手机号查找用户
     * @param mobile
     * @return
     */
    AccountEntity findByMobile(String mobile);

    List<AccountEntity> findAllByCondition(AccountEntity account);

    List<AccountEntity> findAll();

    /**
     * 查询是否有手机号重复的数据
     * @param mobiles
     */
    List<String> findInfoByMobile(List<String> mobiles);
}
