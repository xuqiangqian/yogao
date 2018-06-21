package com.yogo.fm.admin.exception;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.response.FmResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 作草分茶
 * @Description
 * @date 2018-05-18
 */
@RestControllerAdvice
public class FmExceptionHandle {
    private static Logger logger = LoggerFactory.getLogger(FmExceptionHandle.class);
    /**
     * 处理包装异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(FmException.class)
    public FmResponse handleKcException(FmException exception) {
        if (exception == null) {
            return FmResponse.error("未知错误");
        }
        exception.printStackTrace();
        logger.error(exception.getMessage());
        return new FmResponse<>(exception.getCode(), exception.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public FmResponse handleException(Exception exception) {
        if (exception == null) {
            return FmResponse.error("未知错误");
        }
        exception.printStackTrace();
        logger.error(exception.getMessage());
        return new FmResponse<>(500, "未知错误", null);
    }
}
