package com.module.zhupeng.data;

import java.util.LinkedHashMap;

/**
 * Paging
 * <p>date : 2019-11-07</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class Paging {
    /**
     * 页面索引 ， 默认显示第一页
     */
    private int pageIndex = 1;

    /**
     * 每页显示笔数 查询返回的行数（每页显示的行数），默认每页显示10行
     */
    private int pageSize = 10;

    /**
     * 总页数
     */
    private int pageCount;
    /**
     * 总记录数
     */
    private int recordCount;


    public static final String DESC = "DESC";
    public static final String ASC = "ASC";

    /**
     * 获取查询起始行数
     *
     * @return 返回查询行数
     */
    public int getRecordIndex() {
        return (this.getPageIndex() - 1) * this.getPageSize();
    }

    /**
     * 排序, Key为排序属性,Value为asc/desc,如:
     * LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
     * orderBy.put("email", "asc");
     * orderBy.put("password", "desc");
     */
    private LinkedHashMap<String, String> orderBy = new LinkedHashMap<>();

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        if (this.pageIndex < 1) {
            this.pageIndex = 1;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
        this.pageCount = this.recordCount / pageSize;
        if (this.recordCount % this.pageSize > 0) {
            this.pageCount = this.pageCount + 1;
        }
        if (this.pageIndex > this.pageCount) {
            this.pageIndex = this.pageCount;
        }
    }

    /**
     * 排序
     *
     * @param key   Key为排序属性
     * @param value Value为asc/desc
     * @return 返回排序集合
     */
    public LinkedHashMap<String, String> addOrder(String key, String value) {
        this.orderBy.put(key, value);
        return this.orderBy;
    }

    public LinkedHashMap<String, String> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(LinkedHashMap<String, String> orderBy) {
        this.orderBy = orderBy;
    }

    public static Paging newPaging(int index, int size) {
        Paging paging = new Paging();
        paging.setPageIndex(index);
        paging.setPageSize(size);
        return paging;
    }
}
