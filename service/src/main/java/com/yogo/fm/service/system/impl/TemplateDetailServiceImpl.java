package com.yogo.fm.service.system.impl;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.mapper.system.ITemplateDetailMapper;
import com.yogo.fm.mapper.system.ITemplateMapper;
import com.yogo.fm.model.system.TemplateDetailEntity;
import com.yogo.fm.service.system.ITemplateDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author xuqiangqiang
 * @Date: 2018/6/6 09:39
 * @Description:
 */
@Service
public class TemplateDetailServiceImpl implements ITemplateDetailService {

    @Autowired
    private ITemplateDetailMapper templateDetailMapper;
    @Autowired
    private ITemplateMapper templateMapper;

    @Override
    public TemplateDetailEntity save(TemplateDetailEntity entity) throws FmException {
        return entity;
    }

    @Override
    public void saveDetailByTid(TemplateDetailEntity entity, String id) throws FmException {
        if (entity == null) {
            throw FmException.error("参数错误");
        }

        if (StringUtils.isBlank(entity.getField())) {
            throw FmException.error("字段不能为空");
        }
        if (StringUtils.isBlank(entity.getFieldName())) {
            throw FmException.error("字段名不能为空");
        }
        if (entity.getSort() == null) {
            throw FmException.error("顺序号不能为空");
        }
        entity.setTName(templateMapper.findNameById(Long.valueOf(id)));
        entity.setTId(Long.valueOf(id));
        templateDetailMapper.save(entity);
    }

    @Override
    public List<TemplateDetailEntity> findAll(TemplateDetailEntity templateDetailEntity) {
        return templateDetailMapper.findAll(templateDetailEntity);
    }

    @Override
    public List<TemplateDetailEntity> findAllByTid(Long tid) {
        return templateDetailMapper.findAllByTId(tid);
    }

    @Override
    public List<TemplateDetailEntity> findDetailById(String id) {
        return templateDetailMapper.findTemplateDetailById(Long.valueOf(id));
    }

    @Override
    public TemplateDetailEntity delete(Long id) throws FmException {
        return null;
    }

    @Override
    public TemplateDetailEntity update(TemplateDetailEntity entity) throws FmException {
        if (entity == null || entity.getId() == null) {
            throw FmException.error("参数错误");
        }
        templateDetailMapper.update(entity);
        return null;
    }

    @Override
    public TemplateDetailEntity find(Long id) throws FmException {
        return null;
    }

    @Override
    public List<TemplateDetailEntity> batchSave(List<TemplateDetailEntity> list) {
        return null;
    }

    @Override
    public List<TemplateDetailEntity> batchDelete(List<Long> list) throws FmException {
        if (CollectionUtils.isEmpty(list)) {
            throw FmException.error("参数错误");
        }
        templateDetailMapper.batchDelete(list);
        return null;
    }

    @Override
    public FmPage<TemplateDetailEntity> findPage(TemplateDetailEntity condition, FmPage<TemplateDetailEntity> page) throws FmException {
        return null;
    }

}
