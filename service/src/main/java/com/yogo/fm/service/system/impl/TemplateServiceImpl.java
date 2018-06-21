package com.yogo.fm.service.system.impl;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.utils.ExcelUtils;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.mapper.system.ITemplateMapper;
import com.yogo.fm.model.system.TemplateEntity;
import com.yogo.fm.service.system.IOssService;
import com.yogo.fm.service.system.ITemplateService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;


/**
 * @author yangyuhang
 */
@Service
public class TemplateServiceImpl implements ITemplateService {
    @Autowired
    private ITemplateMapper templateMapper;
    @Autowired
    private IOssService ossService;


    @Override
    public String exportExcel(String fileName, String[][] data, List<List<String>> listDate) throws Exception {
        HSSFWorkbook workbook = ExcelUtils.exportExcel(fileName, data, listDate);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        return ossService.saveFile(os.toByteArray(), fileName, ".xls");
    }

    @Override
    public String exportExcel(String fileName, String[][] data) throws Exception {
        HSSFWorkbook workbook = ExcelUtils.exportExcel(fileName, data, null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        return ossService.saveFile(os.toByteArray(), fileName, ".xls");
    }



    @Override
    public List<TemplateEntity> findAll() {
        return templateMapper.findAll();
    }


    @Override
    public TemplateEntity save(TemplateEntity entity) throws FmException {
        if (entity == null) {
            throw FmException.error("参数错误");
        }

        if (StringUtils.isBlank(entity.getTableName())) {
            throw FmException.error("表名不能为空");
        }
        if (StringUtils.isBlank(entity.getName())) {
            throw FmException.error("模板名称不能为空");
        }
        if (entity.getLevel() == null) {
            throw FmException.error("模板级别不能为空");
        }
        templateMapper.save(entity);
        return entity;
    }

    @Override
    public TemplateEntity delete(Long id) {
        return null;
    }

    @Override
    public TemplateEntity update(TemplateEntity entity) throws FmException {
        if (entity == null || entity.getId() == null) {
            throw FmException.error("参数错误");
        }
        templateMapper.update(entity);
        return null;
    }

    @Override
    public TemplateEntity find(Long id) {
        return null;
    }

    @Override
    public List<TemplateEntity> batchSave(List<TemplateEntity> list) {
        return null;
    }

    @Override
    public List<TemplateEntity> batchDelete(List<Long> id) throws FmException {
        if (CollectionUtils.isEmpty(id)) {
            throw FmException.error("参数错误");
        }
        templateMapper.batchDelete(id);
        return null;
    }

    @Override
    public FmPage<TemplateEntity> findPage(TemplateEntity condition, FmPage<TemplateEntity> page) throws FmException {
        return null;
    }
}
