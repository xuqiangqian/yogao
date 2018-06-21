package com.yogo.fm.service.system;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.model.system.TemplateDetailEntity;
import com.yogo.fm.service.IBaseService;

import java.util.List;

/**
 * @author xuqiangqiang
 * @Date: 2018/6/6 09:37
 * @Description:
 */
public interface ITemplateDetailService extends IBaseService<TemplateDetailEntity> {

    void saveDetailByTid(TemplateDetailEntity entity, String id) throws FmException;

    /**
     * 查询所有
     * @return
     */
    List<TemplateDetailEntity> findAll(TemplateDetailEntity templateDetailEntity);
    List<TemplateDetailEntity> findAllByTid(Long tid);

    /**
     * 通过模板id找详细
     * @return
     */
    List<TemplateDetailEntity> findDetailById(String id);
}
