package com.yogo.fm.admin.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
 * @Auther: xuqiangqiang
 * @Date: 2018/6/4 16:07
 * @Description:
 */
@RestController
@RequestMapping("/template")
public class TemplateController {
    private final ITemplateService templateService;
    private final ITemplateDetailService templateDetailService;

    @Autowired
    public TemplateController(ITemplateService templateService, ITemplateDetailService templateDetailService) {
        this.templateService = templateService;
        this.templateDetailService = templateDetailService;
    }

    /**
     * 新增模板
     *
     * @param templateEntity
     * @return
     * @throws FmException
     */
    @PostMapping(value = "/add")
    public FmResponse<TemplateEntity> add(TemplateEntity templateEntity) throws Exception {
        templateService.save(templateEntity);
        return FmResponse.ok("新增模板成功");
    }

    /**
     * 更改模板
     *
     * @param templateEntity
     * @return
     * @throws FmException
     */
    @PutMapping(value = "/update")
    public FmResponse<TemplateEntity> update(TemplateEntity templateEntity) throws FmException {
        templateService.update(templateEntity);
        return FmResponse.ok("更新模板成功");
    }

    /**
     * 通过id删除模板，模板Id用，隔开
     *
     * @param id
     * @return
     * @throws FmException
     */
    @DeleteMapping(value = "/delete")
    public FmResponse<TemplateEntity> delete(@RequestParam("id") String id) throws FmException {
        List<String> ids = new ArrayList<>(Arrays.asList(id.split(",")));
        templateService.batchDelete(ListTypeUtils.listStringToLong(ids));
        return FmResponse.ok("删除模板成功");
    }

    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    public FmResponse<List<TemplateEntity>> find() {
        return FmResponse.ok("查找成功", templateService.findAll());
    }

    @PostMapping("/export")
    public FmResponse export(String id, String listData) throws Exception {
        List<TemplateDetailEntity> titles = templateDetailService.findDetailById(id);
        String[][] data = new String[3][titles.size()];
        for (int i = 0; i < titles.size(); i++) {
            TemplateDetailEntity t = titles.get(i);
            // 中文列头
            String chinese = t.getFieldName();
            // 英文列头
            String english = t.getField();
            // 下拉框数据集
            String value = t.getDataList();
            data[0][i] = chinese;
            data[1][i] = english;
            data[2][i] = value;
        }
        String fileName = titles.get(0).getTName()+System.currentTimeMillis();
        List<List<String>> listList = new ArrayList<>();
        JSONArray array = JSONArray.parseArray(listData);
        if (array != null) {
            String value;
            String key;
            List<String> list;
            for (Object object : array) {
                JSONObject json = (JSONObject) object;
                list = new ArrayList<>();
                for (int i = 0; i < data[1].length; i++) {
                    key = data[1][i];
                    value = (String) json.get(key);
                    list.add(value);
                }
                listList.add(list);
            }

        }
        String url = templateService.exportExcel(fileName, data, listList);
        return FmResponse.ok(url);
    }

    @GetMapping("/exportData")
    public FmResponse exportData(String id) throws FmException {
        List<TemplateDetailEntity> titles = templateDetailService.findDetailById(id);
        return FmResponse.ok("导出成功", titles);
    }
}
