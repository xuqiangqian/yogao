package com.yogo.fm.service.system.impl;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.mapper.system.IFileMapper;
import com.yogo.fm.model.system.FileEntity;
import com.yogo.fm.service.system.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-08
 */
@Service
public class FileServiceImpl implements IFileService {

    private final IFileMapper fileMapper;

    @Autowired
    public FileServiceImpl(IFileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public FileEntity save(FileEntity entity) throws FmException {
        if (entity == null) {
            throw FmException.error("参数错误");
        }
        fileMapper.save(entity);
        return entity;
    }

    @Override
    public FileEntity delete(Long id) throws FmException {
        return null;
    }

    @Override
    public FileEntity update(FileEntity entity) throws FmException {
        return null;
    }

    @Override
    public FileEntity find(Long id) throws FmException {
        return null;
    }

    @Override
    public List<FileEntity> batchSave(List<FileEntity> list) {
        return null;
    }

    @Override
    public List<FileEntity> batchDelete(List<Long> list) throws FmException {
        return null;
    }

    @Override
    public FmPage<FileEntity> findPage(FileEntity condition, FmPage<FileEntity> page) throws FmException {
        return null;
    }
}
