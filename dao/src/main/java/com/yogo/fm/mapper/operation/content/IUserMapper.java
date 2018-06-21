package com.yogo.fm.mapper.operation.content;

import com.yogo.fm.mapper.IBaseMapper;
import com.yogo.fm.model.operation.content.UserEntity;
import com.yogo.fm.model.operation.content.Userinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaoleilei
 * @Description
 * @date 2018-05-25
 */
public interface IUserMapper extends IBaseMapper<UserEntity> {

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    UserEntity findById(@Param("id") Long id);

    /**
     * 根据条件查询用户列表
     *
     * @param ue
     * @return
     */
    List<UserEntity> getAllUsers(@Param("ue") UserEntity ue);

    /**
     * 根据id删除用户信息，软删除
     *
     * @param id
     */
    void deleteUser(@Param("id") Long id);

    /**
     * 更改用户状态
     *
     * @param ue
     */
    void changeUserStatus(@Param("ue") UserEntity ue);

    /**
     * 根据id查询userInfo信息
     *
     * @param id
     * @return
     */
    Userinfo findUserInfoById(@Param("id") Long id);
}
