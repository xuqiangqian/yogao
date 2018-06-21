package com.yogo.fm.mapper;

import com.yogo.fm.model.BaseEntity;

import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-18
 */
public interface IBaseMapper<T extends BaseEntity>{
    /**
     * 保存单条数据
     * @param entity
     */
    void save(T entity);

    /**
     * 删除单条数据
     * @param id
     */
    void delete(Long id);

    /**
     * 更新单条数据
     * @param entity
     */
    void update(T entity);

    /**
     * 查询单条数据
     * @param id
     * @return
     */
    T find(Long id);

    /**
     * 批量保存
     * @param list
     */
    void batchSave(List<T> list);

    /**
     * 批量删除
     * @param list
     */
    void batchDelete(List<Long> list);
}
