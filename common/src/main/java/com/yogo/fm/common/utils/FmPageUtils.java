package com.yogo.fm.common.utils;


import com.github.pagehelper.PageInfo;
import com.yogo.fm.common.exception.FmException;
import org.springframework.data.domain.PageRequest;

/**
 * @author 作草分茶
 * @Description 分页工具类
 * @date 2018-05-06
 */
public class FmPageUtils<T> {
    public static <T> void fmPageHandler(FmPage<T> kcPage, PageInfo<T> page) {
        kcPage.setData(page.getList());
        kcPage.setTotalPage(page.getPages());
        kcPage.setDataSize(page.getTotal());
        //是否有上一页
        if (page.getPageNum() == 0) {
            kcPage.setLastPage(-1);
        } else {
            kcPage.setLastPage(page.getPageNum() - 1);
        }
        //是否有下一页
        if (page.getPageNum() == page.getPages()) {
            kcPage.setNextPage(-1);
        } else {
            kcPage.setNextPage(page.getPageNum() + 1);
        }
    }

    public static PageRequest pageRequest(FmPage entity) throws FmException {
        if (entity == null) {
            throw FmException.error("参数错误");
        }
        return new PageRequest(entity.getPage()-1, entity.getPageSize(), entity.getSort());
    }
}
