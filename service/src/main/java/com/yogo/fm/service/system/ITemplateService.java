package com.yogo.fm.service.system;

import com.yogo.fm.model.system.TemplateEntity;
import com.yogo.fm.service.IBaseService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: xuqiangqiang
 * @Date: 2018/6/4 15:15
 * @Description:
 *
 */
public interface ITemplateService extends IBaseService<TemplateEntity> {
    /**
     * 传进去一个文档头部生成一个Excel文档
     * @param fileName 文件名
     * @param data  表头
     * @param listDate 表数据
     * @return
     */

    String exportExcel(String fileName, String[][] data, List<List<String>> listDate) throws Exception;

    /**
     * 传进去一个文档头部生成一个Excel文档
     * @param fileName
     * @param data
     * @return
     */
    String exportExcel(String fileName, String[][] data) throws Exception;

    /**
     * 查找所有
     * @return
     */
    List<TemplateEntity> findAll();
}
