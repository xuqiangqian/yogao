package com.yogo.fm.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-28
 */
public class ListTypeUtils {
    /**
     * 传入String类型的List，转换成Long类型的List，用于删除id时的解析
     * @param list
     * @return
     */
    public static List<Long> listStringToLong(List<String> list){
        List<Long> longs = new ArrayList<>();
        list.forEach(item -> longs.add(Long.parseLong(item)));
        return longs;
    }
}
