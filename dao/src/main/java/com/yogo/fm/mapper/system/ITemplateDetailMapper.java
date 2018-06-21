package com.yogo.fm.mapper.system;

import com.yogo.fm.mapper.IBaseMapper;
import com.yogo.fm.model.system.TemplateDetailEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  xuqiangqiang
 * @Date: 2018/6/4 18:57
 * @Description:
 */
public interface ITemplateDetailMapper extends IBaseMapper<TemplateDetailEntity> {
    /**
     * 根据模板id找value
     * @param id
     * @return
     */
    List<TemplateDetailEntity> findTemplateDetailById(Long id);

    /**
     * 查询列表
     * @return
     */
    List<TemplateDetailEntity> findAll(TemplateDetailEntity templateDetailEntity);

    /**
     * 根据Id查找所有对应数据
     * @param tId
     * @return
     */
    List<TemplateDetailEntity> findAllByTId(@Param("tId") Long tId);

}
