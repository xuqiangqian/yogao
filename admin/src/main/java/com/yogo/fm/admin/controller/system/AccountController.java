package com.yogo.fm.admin.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.yogo.fm.admin.annotation.VerifyResource;
import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.common.response.FmResponseCode;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.common.utils.ListTypeUtils;
import com.yogo.fm.common.utils.ThreadLocals;
import com.yogo.fm.model.system.AccountEntity;
import com.yogo.fm.model.system.ResourceEntity;
import com.yogo.fm.service.system.IAccountService;
import com.yogo.fm.service.system.ITemplateDetailService;
import com.yogo.fm.service.system.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

/**
 * @author 作草分茶
 * @Description account 账号 controller
 * @date 2018-04-28
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final IAccountService accountService;
    private final ITemplateService templateService;
    private final ITemplateDetailService templateDetailService;

    @Autowired
    public AccountController(IAccountService accountService, ITemplateService templateService, ITemplateDetailService templateDetailService) {
        this.accountService = accountService;
        this.templateService = templateService;
        this.templateDetailService = templateDetailService;
    }

    /**
     * 新增账号
     *
     * @param admin
     * @return
     */
    @PostMapping("/add")
    public FmResponse<AccountEntity> add(AccountEntity admin) throws Exception {
        accountService.save(admin);
        return FmResponse.ok("新增账号成功");
    }

    /**
     * 通过id删除账号，账号id用“,”隔开
     *
     * @param id
     * @return
     * @throws FmException
     */
    @DeleteMapping("/delete")
    public FmResponse<ResourceEntity> delete(@RequestParam("id") String id) throws FmException {
        List<String> ids = new ArrayList<>(Arrays.asList(id.split(",")));
        accountService.batchDelete(ListTypeUtils.listStringToLong(ids));
        return FmResponse.ok("删除成功");
    }

    /**
     * 更新账号
     *
     * @param account
     * @return
     * @throws FmException
     */
    @PutMapping("/update")
    public FmResponse<AccountEntity> update(AccountEntity account) throws FmException {
        accountService.update(account);
        return FmResponse.ok("更新账号成功");
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @GetMapping("/find")
    public FmResponse<AccountEntity> find(@RequestParam("id") Long id) throws FmException {
        return FmResponse.ok("查找账号成功", accountService.find(id));
    }

    /**
     * 通过查询条件和分页信息查询
     *
     * @param accountEntity
     * @param fmPage
     * @return
     * @throws FmException
     */
    @GetMapping("/list")
    @VerifyResource("/account/list")
    public FmResponse<FmPage<AccountEntity>> findPage(AccountEntity accountEntity, FmPage<AccountEntity> fmPage) throws FmException {
        return FmResponse.ok("查找成功", accountService.findPage(accountEntity, fmPage));
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    @GetMapping("/current")
    public FmResponse<AccountEntity> currentAccount() throws FmException {
        AccountEntity accountEntity = ThreadLocals.userLocal.get();
        if (accountEntity == null) {
            throw new FmException(FmResponseCode.ACCOUNT_UN_LOGIN);
        }
        return FmResponse.ok(accountEntity);
    }

    /**
     * 账号启用禁用
     *
     * @return
     * @throws FmException
     */
    @PutMapping("/enable")
    public FmResponse enable(AccountEntity entity) throws FmException {
        accountService.update(entity);
        return FmResponse.ok("账号" + (entity.getFlag() ? "启用成功" : "禁用成功"));
    }

    @PostMapping("/importExcel")
    public FmResponse<AccountEntity> importExcel(String data) throws FmException {
        List<AccountEntity> accountEntities = JSONArray.parseArray(data,AccountEntity.class );
        accountService.importExcel(accountEntities);
        return FmResponse.ok("导入成功");
    }

    @PutMapping("/exportExcel")
    public FmResponse<List<AccountEntity>> exportExcel(String listData) throws FmException{
        List<String> alist=JSONArray.parseArray(listData,String.class);
         return FmResponse.ok("导出成功");
    }
}
