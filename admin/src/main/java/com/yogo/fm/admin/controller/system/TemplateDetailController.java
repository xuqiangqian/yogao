package com.yogo.fm.admin.controller.system;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.common.utils.ListTypeUtils;
import com.yogo.fm.model.system.TemplateDetailEntity;
import com.yogo.fm.model.system.TemplateEntity;
import com.yogo.fm.service.system.ITemplateDetailService;
import com.yogo.fm.service.system.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuqiangqiang
 * @Date: 2018/6/6 11:10
 * @Description:
 */
@RestController
@RequestMapping("/templateDetail")
public class TemplateDetailController {
    private final ITemplateDetailService templateDetailService;
    @Autowired
    public TemplateDetailController(ITemplateDetailService templateDetailService) {
        this.templateDetailService = templateDetailService;
    }

    /**
     * 新增模板
     * @param templateDetailEntity
     * @return
     * @throws FmException
     */
    @PostMapping(value = "/add")
    public FmResponse<TemplateEntity> add(TemplateDetailEntity templateDetailEntity, String Tid) throws FmException {
        templateDetailService.saveDetailByTid(templateDetailEntity,Tid);
        return FmResponse.ok("新增字段信息成功");
    }

    /**
     * 更改模板
     * @param templateDetailEntity
     * @return
     * @throws FmException
     */
    @PutMapping(value="/update")
    public FmResponse<TemplateEntity> update(TemplateDetailEntity templateDetailEntity) throws  FmException{
        templateDetailService.update(templateDetailEntity);
        return FmResponse.ok("更新字段信息成功");
    }

    /**
     * 通过id删除模板，模板Id用，隔开
     * @param id
     * @return
     * @throws FmException
     */
    @DeleteMapping(value="/delete")
    public FmResponse<TemplateEntity> delete(@RequestParam("id") String id) throws  FmException{
        List<String> ids=new ArrayList<>(Arrays.asList(id.split(",")));
        templateDetailService.batchDelete(ListTypeUtils.listStringToLong(ids));
        return FmResponse.ok("删除成功");
    }

    /**
     * 查询列表
     * @return
     */
    @GetMapping(value="/list")
    public FmResponse<List<TemplateDetailEntity>> find(String id){
        return  FmResponse.ok("查找成功",templateDetailService.findAllByTid(Long.parseLong(id)));
    }

}
