package com.yogo.fm.service;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.model.BaseEntity;
import com.yogo.fm.service.annotation.SolrHandle;

import java.util.List;

/**
 * @author qiqiang
 * @Description 基础service接口
 * @date 2018-05-18
 */
public interface IBaseService<T extends BaseEntity> {
    T save(T entity) throws Exception;

    T delete(Long id) throws FmException;

    T update(T entity) throws FmException;

    T find(Long id) throws FmException;

    List<T> batchSave(List<T> list);

    List<T> batchDelete(List<Long> list) throws FmException;

    /**
     * 根据查询条件查询分页信息
     *
     * @param condition
     * @param page
     * @return
     * @throws FmException
     */
    FmPage<T> findPage(T condition, FmPage<T> page) throws FmException;
}
