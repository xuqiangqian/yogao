package com.yogo.fm.service.system.impl;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.model.system.FileEntity;
import com.yogo.fm.service.system.IFileService;
import com.yogo.fm.service.system.IOssService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-08
 */
@Service
public class OssServiceImpl implements IOssService {
    @Value("${oss.host}")
    private String host;
    @Value("${oss.port}")
    private String port;
    @Value("${oss.upload-path}")
    private String uploadPath;
    @Value("${oss.download-path}")
    private String downloadPath;

    private final IFileService fileService;

    @Autowired
    public OssServiceImpl(IFileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 保存文件到oss
     *
     * @param multipartFile
     * @return
     * @throws FmException
     * @throws IOException
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String saveFile(MultipartFile multipartFile) throws Exception {
        if (multipartFile == null) {
            throw FmException.error("参数错误");
        }
        FileEntity file = new FileEntity();
        String fileName = multipartFile.getOriginalFilename();
        //文件名
        file.setOriginName(fileName);
        //后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        file.setSuffix(suffixName);
        //文件路径=文件夹路径+nano时间+文件名
        String name = System.nanoTime() + fileName;
        String uploadUrl = uploadPath + "/" + name;
        file.setName(name);
        file.setUrl(downloadPath + "/" + name);
        file.setType(1);
        File ossFile = new File(uploadUrl);
        // 检测是否存在目录,不存在则新建文件夹
        if (!ossFile.getParentFile().exists()) {
            ossFile.getParentFile().mkdirs();
        }
        multipartFile.transferTo(ossFile);
        fileService.save(file);
        return host + ":" + port + downloadPath + "/" + name;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String saveFile(byte[] data, String name, String suffix) throws Exception {
        if (data == null||name==null||suffix==null) {
            throw FmException.error("参数错误");
        }
        FileEntity file = new FileEntity();
        //文件名
        file.setOriginName(name+suffix);
        file.setSuffix(suffix);
        //文件路径=文件夹路径+nano时间+文件名
        name = System.nanoTime() + name+suffix;
        String uploadUrl = uploadPath + "/" + name;
        file.setName(name);
        file.setUrl(downloadPath + "/" + name);
        file.setType(1);
        File ossFile = new File(uploadUrl);
        // 检测是否存在目录,不存在则新建文件夹
        if (!ossFile.getParentFile().exists()) {
            ossFile.getParentFile().mkdirs();
        }
        FileUtils.writeByteArrayToFile(ossFile,data);
        fileService.save(file);
        return host + ":" + port + downloadPath + "/" + name;
    }
}
