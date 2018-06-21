package com.yogo.fm.annotation;

import java.lang.annotation.*;

/**
 * @Auther: xuqiangqiang
 * @Date: 2018/6/6 19:21
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface ExcelFieldValue {
    /**
     * excel导入的中文字段
     * @return
     */
    String value() default "";
}
