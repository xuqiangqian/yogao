package com.yogo.fm.model.system;

import com.yogo.fm.model.BaseEntity;
import lombok.Data;

/**
 * @author qiqiang
 * @Description 文件
 * @date 2018-06-08
 */
@Data
public class FileEntity extends BaseEntity {
    /**
     * 相对url
     */
    private String url;
    /**
     * 源文件名
     */
    private String originName;
    /**
     * 存储位置文件名
     */
    private String name;
    /**
     * 文件类型，0 图片，1 其它文件
     */
    private Integer type;
    /**
     * 后缀
     */
    private String suffix;
}
