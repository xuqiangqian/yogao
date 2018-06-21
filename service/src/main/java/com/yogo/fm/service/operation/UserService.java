package com.yogo.fm.service.operation;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.model.operation.content.UserEntity;

/**
 * @author zhaoleilei
 * @Description
 * @date 2018-05-25
 */
public interface UserService {

    /**
     * 根据id获取用户信息
     *
     * @param id 用户账号
     * @return
     */
    UserEntity findById(Long id);

    /**
     * 根据条件查询所有用户
     *
     * @param ue   查询条件
     * @param page 分页信息
     * @return
     * @throws FmException
     */
    FmPage<UserEntity> findAllUser(UserEntity ue, FmPage<UserEntity> page) throws FmException;

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 更改用户状态
     *
     * @param ue
     */
    void changeUserStatus(UserEntity ue);
}
