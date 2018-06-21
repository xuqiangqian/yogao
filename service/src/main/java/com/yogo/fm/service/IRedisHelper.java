package com.yogo.fm.service;


/**
 * @author qiqiang
 * @Description
 * @date 2018-05-22
 */
public interface IRedisHelper<T> {
    /**
     * 通过key获取值
     *
     * @param key
     * @return
     */
    T getValue(String key);

    /**
     * 设置值
     *
     * @param key
     * @param data
     */
    void setValue(String key, T data);

    /**
     * 通过key获取值
     *
     * @param key
     * @return
     */
    String getString(String key);

    /**
     * 设置值
     *
     * @param key
     * @param value
     */
    void setString(String key, String value);

    /**
     * redis中是否存在该key
     *
     * @param key
     * @return
     */
    boolean hasKey(String key);

}
