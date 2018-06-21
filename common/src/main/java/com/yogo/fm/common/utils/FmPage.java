package com.yogo.fm.common.utils;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * @author 作草分茶
 * @Description 自定义分页信息
 * @date 2018-05-03
 */
@ApiModel
public class FmPage<T> implements Serializable {
    /**
     * 当前页
     */
    @ApiModelProperty
    private int page = 1;
    /**
     * 上一页
     */
    private int lastPage;
    /**
     * 下一页
     */
    private int nextPage;
    /**
     * 每页数据大小
     */
    @ApiModelProperty
    private int pageSize = 8;
    /**
     * 数据
     */
    private List<T> data;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 总数量
     */
    private long dataSize;
    /**
     * 排序
     */
    @JSONField(serialize = false)
    private Sort sort = new Sort(Sort.Direction.DESC, "createTime");

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getDataSize() {
        return dataSize;
    }

    public void setDataSize(long dataSize) {
        this.dataSize = dataSize;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "FmPage{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", dataSize=" + dataSize +
                ", sort=" + sort +
                '}';
    }
}
