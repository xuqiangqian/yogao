package com.yogo.fm.mapper.system;

import com.yogo.fm.mapper.IBaseMapper;
import com.yogo.fm.model.system.TemplateEntity;

import java.util.List;

/**
 * @author xuqiangqiang
 * @Date: 2018/6/4 13:43
 * @Description:
 */
public interface ITemplateMapper extends IBaseMapper<TemplateEntity> {
    /**
     * 查询所有模板
     * @return
     */
    List<TemplateEntity> findAll();

    String  findNameById(Long id);

}
