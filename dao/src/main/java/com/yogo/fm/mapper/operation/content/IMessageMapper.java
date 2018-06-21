package com.yogo.fm.mapper.operation.content;

import com.yogo.fm.model.operation.content.MessageEntity;
import com.yogo.fm.mapper.IBaseMapper;

import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-26
 */
public interface IMessageMapper extends IBaseMapper<MessageEntity> {

    List<MessageEntity> findAllByCondition(MessageEntity condition);

}
