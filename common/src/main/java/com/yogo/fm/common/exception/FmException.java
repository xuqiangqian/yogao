package com.yogo.fm.common.exception;

import com.yogo.fm.common.response.FmResponseCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @author qiqiang
 * @Description 自定义异常
 * @date 2018-05-18
 */
public class FmException extends Exception {
    private int code;
    private String message;

    public FmException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public FmException(FmResponseCode responseCode) {
        this.code = responseCode.code;
        if (StringUtils.isNotBlank(responseCode.message)) {
            this.message = responseCode.message;
        }
    }

    public static FmException error(String message) {
        return new FmException(500, message);
    }

    public static FmException instance(int code, String message) {
        return new FmException(code, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
