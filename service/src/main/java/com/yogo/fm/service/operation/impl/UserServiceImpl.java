package com.yogo.fm.service.operation.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.common.utils.FmPageUtils;
import com.yogo.fm.mapper.operation.content.IUserMapper;
import com.yogo.fm.model.operation.content.UserEntity;
import com.yogo.fm.model.operation.content.Userinfo;
import com.yogo.fm.service.operation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author zhaoleilei
 * @Description
 * @date 2018-05-28
 */
@Service
public class UserServiceImpl implements UserService {
    private final IUserMapper userRepository;

    @Autowired
    public UserServiceImpl(IUserMapper userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findById(Long id) {
        UserEntity ue = userRepository.findById(id);
        Userinfo ui = userRepository.findUserInfoById(ue.getUserInfoId());
        ue.setUserinfo(ui);
        return ue;
    }

    @Override
    public FmPage<UserEntity> findAllUser(UserEntity ue, FmPage<UserEntity> page) throws FmException {
        if (page == null) {
            throw FmException.error("参数错误");
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<UserEntity> allUser = userRepository.getAllUsers(ue);
        FmPageUtils.fmPageHandler(page,new PageInfo<>(allUser));
        return page;
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteUser(id);
    }

    @Override
    public void changeUserStatus(UserEntity ue){
        userRepository.changeUserStatus(ue);
    }
}
