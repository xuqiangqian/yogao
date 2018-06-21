package com.yogo.fm.mapper.operation.content;

import com.yogo.fm.mapper.IBaseMapper;
import com.yogo.fm.model.operation.content.FeedbackEntity;

import java.util.List;

/**
 * @author qiqiang
 * @Description 反馈
 * @date 2018-05-30
 */
public interface IFeedbackMapper extends IBaseMapper<FeedbackEntity> {
    /**
     * 通过查询条件查询
     * @param condition
     * @return
     */
    List<FeedbackEntity> findAllByCondition(FeedbackEntity condition);

}
