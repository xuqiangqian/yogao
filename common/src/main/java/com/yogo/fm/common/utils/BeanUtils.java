package com.yogo.fm.common.utils;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 新增updattime，insertdate等字段的数据，并设置默认值
 * String =""  number=0
 * 每个表里必须有createtime和modidiedtime的字段
 * @author 小鱼
 * @description
 * @date Create in 9:10 2018/5/25
 */
public class BeanUtils {
    private static final String UPDATE_TIME_KEY = "modifyTime";
    private static final String CREATE_TIME_KEY = "createTime";

    /**
     * 设置默认值
     *
     * @param target
     * @param tClass
     * @param <T>
     */
    public static <T> void setDefaultProp(T target, Class<T> tClass) {
        PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(tClass);
        for (PropertyDescriptor propertyDescriptor : descriptors) {
            String fieldName = propertyDescriptor.getName();
            Object value;
            try {
                value = PropertyUtils.getProperty(target, fieldName);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException("不能从" + target + "和类" + tClass + "的字段:" + fieldName + "中设置属性");
            }
            if (String.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
                try {
                    PropertyUtils.setProperty(target, fieldName, "");
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException("不能从" + target + "和类" + tClass + "的字段:" + fieldName + "中设置属性");
                }
            } else if (Number.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
                try {
                    PropertyUtils.setProperty(target, fieldName, 0);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException("不能从" + target + "和类" + tClass + "的字段:" + fieldName + "中设置属性");
                }
            }
        }
    }

    public static <T> void onUpdate(T target) {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        try {
            PropertyUtils.setProperty(target, UPDATE_TIME_KEY, date);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return;
        }
    }

    public static <T> void onInsert(T target) {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        try {
            PropertyUtils.setProperty(target, UPDATE_TIME_KEY, date);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }
        try {
            PropertyUtils.setProperty(target, CREATE_TIME_KEY, date);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }
    }

}
