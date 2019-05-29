package edu.health.core.base.dto;

import javax.persistence.Transient;

/**
 * 分页 基础信息
 *
 * @author 执笔
 */
public class BaseDomain {

    /**
     * 分页信息
     */
    @Transient
    private Integer pageNum  = 0;
    @Transient
    private Integer pageSize = 10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
