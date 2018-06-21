package com.yogo.fm.common.response;

/**
 * 自定义状态码
 */
public enum FmResponseCode {
    /**
     * 请求成功
     */
    OK(200),
    /**
     * 系统错误
     */
    ERROR(500),
    /***
     * 参数错误
     */
    PARAM_ERROR(501,"参数错误"),
    /**
     * 账号不存在
     */
    ACCOUNT_NOT_EXIST(310, "账号不存在"),
    ACCOUNT_UN_LOGIN(311, "用户未登录"),
    ACCOUNT_PASSWORD_ERROR(312, "账号或密码错误"),
    ACCOUNT_IS_LOCKED(313, "账号被锁定"),
    ACCOUNT_LOGINED_BY_OTHER(314, "账号已经在其它地方登陆"),


    PERMISSION_DENIED(320, "权限不足，请向管理员申请");
    public int code;
    public String message;

    FmResponseCode(int code) {
        this.code = code;
    }

    FmResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
