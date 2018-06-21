package com.yogo.fm.admin.controller.operation;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.model.operation.content.UserEntity;
import com.yogo.fm.service.operation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaoleilei
 * @Description 用户管理
 * @date 2018-05-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public FmResponse<FmPage<UserEntity>> getUsers(UserEntity ue, FmPage<UserEntity> fmPage) throws FmException {

        return FmResponse.ok("查找用户列表成功", userService.findAllUser(ue, fmPage));
    }

    /**
     * 查找用户信息
     *
     * @param id 用户账号
     * @return
     */
    @GetMapping("/id")
    public FmResponse<UserEntity> find(@RequestParam("id") Long id) {
        return FmResponse.ok("查找用户信息成功", userService.findById(id));
    }

    @DeleteMapping("/id")
    public FmResponse deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return FmResponse.ok("删除用户信息成功");
    }

    @PutMapping("/status")
    public FmResponse changeUserStatus(UserEntity ue) {
        userService.changeUserStatus(ue);
        return FmResponse.ok("更改用户状态成功");
    }
}
