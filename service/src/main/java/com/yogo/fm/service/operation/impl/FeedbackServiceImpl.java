package com.yogo.fm.service.operation.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.common.utils.FmPageUtils;
import com.yogo.fm.mapper.operation.content.IFeedbackMapper;
import com.yogo.fm.model.operation.content.FeedbackEntity;
import com.yogo.fm.service.operation.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author qiqiang
 * @Description 反馈
 * @date 2018-05-30
 */
@Service
public class FeedbackServiceImpl implements IFeedbackService {
    private final IFeedbackMapper feedbackMapper;

    @Autowired
    public FeedbackServiceImpl(IFeedbackMapper feedbackMapper) {
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public FeedbackEntity save(FeedbackEntity entity) throws FmException {
        if (entity == null) {
            throw FmException.error("参数错误");
        }
        feedbackMapper.save(entity);
        return entity;
    }

    @Override
    public FeedbackEntity delete(Long id) throws FmException {
        if (id == null) {
            throw FmException.error("参数错误");
        }
        feedbackMapper.delete(id);
        return null;
    }

    @Override
    public FeedbackEntity update(FeedbackEntity entity) throws FmException {
        if (entity == null) {
            throw FmException.error("参数错误");
        }
        feedbackMapper.update(entity);
        return entity;
    }

    @Override
    public FeedbackEntity find(Long id) throws FmException {
        if (id == null) {
            throw FmException.error("参数错误");
        }
        return feedbackMapper.find(id);
    }

    @Override
    public List<FeedbackEntity> batchSave(List<FeedbackEntity> list) {
        return null;
    }

    @Override
    public List<FeedbackEntity> batchDelete(List<Long> list) throws FmException {
        if (CollectionUtils.isEmpty(list)) {
            throw FmException.error("参数错误");
        }
        feedbackMapper.batchDelete(list);
        return null;
    }

    @Override
    public FmPage<FeedbackEntity> findPage(FeedbackEntity condition, FmPage<FeedbackEntity> page) throws FmException {
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<FeedbackEntity> allUser = feedbackMapper.findAllByCondition(condition);
        FmPageUtils.fmPageHandler(page, new PageInfo<>(allUser));
        return page;
    }
}
