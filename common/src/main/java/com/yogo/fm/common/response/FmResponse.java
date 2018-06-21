package com.yogo.fm.common.response;

/**
 * @author qiqiang
 * @Description 自定义返回信息
 * @date 2018-05-18
 */
public class FmResponse<T> {
    private int code;
    private String message;
    private T data;

    public FmResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> FmResponse<T> error(String message){
        return new FmResponse<>(500,message,null);
    }
    public static <T> FmResponse<T> ok(String message){
        return new FmResponse<>(200,message,null);
    }
    public static <T> FmResponse<T> ok(String message,T data){
        return new FmResponse<>(200,message,data);
    }
    public static <T> FmResponse<T> ok(T data){
        return new FmResponse<>(200,"请求成功",data);
    }
}
