package com.yogo.fm.service.system;

import com.yogo.fm.common.exception.FmException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-08
 */
public interface IOssService {

    /**
     * 上传文件到oss
     * @param multipartFile
     * @return
     * @throws FmException
     * @throws IOException
     */
    String saveFile(MultipartFile multipartFile) throws Exception;
    String saveFile(byte[] data,String name,String suffix) throws Exception;
}
