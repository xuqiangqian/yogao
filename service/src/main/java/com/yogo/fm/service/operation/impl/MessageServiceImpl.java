package com.yogo.fm.service.operation.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.common.utils.FmPageUtils;
import com.yogo.fm.mapper.operation.content.IMessageMapper;
import com.yogo.fm.model.operation.content.MessageEntity;
import com.yogo.fm.service.operation.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-26
 */
@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private IMessageMapper messageMapper;

    @Override
    public MessageEntity save(MessageEntity entity) throws FmException {
        if (entity == null) {
            throw FmException.error("参数错误");
        }
        messageMapper.save(entity);
        return entity;
    }

    @Override
    public MessageEntity delete(Long id) {
        return null;
    }

    @Override
    public List<MessageEntity> batchDelete(List<Long> id) throws FmException {
        if (CollectionUtils.isEmpty(id)) {
            throw FmException.error("参数错误");
        }
        messageMapper.batchDelete(id);
        return null;
    }

    @Override
    public MessageEntity update(MessageEntity entity) throws FmException {
        if (entity == null || entity.getId() == null) {
            throw FmException.error("参数错误");
        }
        messageMapper.update(entity);
        return entity;
    }

    @Override
    public MessageEntity find(Long id) {
        return messageMapper.find(id);
    }

    @Override
    public List<MessageEntity> batchSave(List<MessageEntity> list) {
        return null;
    }

    @Override
    public FmPage<MessageEntity> findPage(MessageEntity condition, FmPage<MessageEntity> page) throws FmException {
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<MessageEntity> allUser = messageMapper.findAllByCondition(condition);
        FmPageUtils.fmPageHandler(page, new PageInfo<>(allUser));
        return page;
    }
}
