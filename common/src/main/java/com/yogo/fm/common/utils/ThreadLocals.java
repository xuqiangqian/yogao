package com.yogo.fm.common.utils;


import com.yogo.fm.model.system.AccountEntity;


/**
 * @author 作草分茶
 * @Description
 * @date 2018-04-29
 */
public class ThreadLocals {
    /**
     * 将用户存到线程中
     */
    public static ThreadLocal<AccountEntity> userLocal = new ThreadLocal<>();
    /**
     * 将token存到线程中
     */
    public static ThreadLocal<String> tokenLocal = new ThreadLocal<>();
}
