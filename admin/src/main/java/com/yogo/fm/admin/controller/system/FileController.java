package com.yogo.fm.admin.controller.system;

import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.service.system.IOssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-08
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);
    private final IOssService ossService;

    @Autowired
    public FileController(IOssService ossService) {
        this.ossService = ossService;
    }

    @PostMapping("/upload")
    public FmResponse upload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        String path = ossService.saveFile(multipartFile);
        logger.info("文件上传成功,地址为："+path);
        return FmResponse.ok("文件上传成功,地址为："+path);
}
}
