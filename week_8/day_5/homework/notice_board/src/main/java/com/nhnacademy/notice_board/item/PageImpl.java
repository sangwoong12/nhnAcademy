package com.nhnacademy.notice_board.item;

import java.util.List;

public class PageImpl<T> implements Page<T> {

    private int pageNumber;
    private int pageSize;
    private int totalPageCount;
    private long totalCount;
    private List<T> list;

    public PageImpl(int pageNumber, int pageSize, int totalPageCount, long totalCount, List<T> list) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPageCount = totalPageCount;
        this.totalCount = totalCount;
        this.list = list;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getTotalPageCount() {
        return totalPageCount;
    }

    @Override
    public long getTotalCount() {
        return totalCount;
    }

    @Override
    public List<T> getList() {
        return list;
    }
}