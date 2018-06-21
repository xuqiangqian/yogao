package com.yogo.fm.admin.controller.system;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.common.utils.ListTypeUtils;
import com.yogo.fm.model.system.AccountEntity;
import com.yogo.fm.model.system.ResourceEntity;
import com.yogo.fm.model.system.RoleEntity;
import com.yogo.fm.service.system.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-22
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private final IRoleService roleService;

    @Autowired
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "/add")
    public FmResponse<AccountEntity> add(RoleEntity role,String button) throws FmException {
        roleService.saveRoleAndRelation(role,button);
        return FmResponse.ok("新增角色成功");
    }

    /**
     * 查找角色列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public FmResponse<FmPage<RoleEntity>> findPage(RoleEntity accountEntity, FmPage<RoleEntity> fmPage) throws FmException {
        return FmResponse.ok("查找成功", roleService.findPage(accountEntity, fmPage));
    }

    /**
     * 更新资源
     * @param roleEntity
     * @return
     * @throws FmException
     */
    @PutMapping("/update")
    public FmResponse<RoleEntity> update(RoleEntity roleEntity,String btn) throws FmException {
        roleService.updateRoleAndRelation(roleEntity,btn);
        return FmResponse.ok("更新账号成功");

    }



    /**
     * 根据Id删除角色
     * @param id
     * @return
     * @throws FmException
     */
    @DeleteMapping(value = "delete")
    public FmResponse<RoleEntity> delete(@RequestParam("id") String id) throws FmException {
        List<String> ids=new ArrayList<>(Arrays.asList(id.split(",")));
        roleService.batchDelete(ListTypeUtils.listStringToLong(ids));
        return FmResponse.ok("删除成功");
    }

}

