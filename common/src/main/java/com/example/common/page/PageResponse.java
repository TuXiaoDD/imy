package com.example.common.page;

import com.github.pagehelper.Page;


import java.util.Collection;

public class PageResponse<T> {

    private long totalCount = 0;

    private int pageSize = 1;

    private Collection<T> list;

    private boolean next = false;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getPageSize() {
        if (pageSize < 1) {
            return 1;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
    }

    public Collection<T> getList() {
        return list;
    }

    public void setList(Collection<T> list) {
        this.list = list;
    }

    public static <T> PageResponse<T> of(Collection<T> list, Page page) {
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setList(list);
        pageResponse.setTotalCount(page.getTotal());
        int size = page.getPageSize();
        pageResponse.setPageSize(size);
        pageResponse.setNext((long) page.getPageNum() * size < page.getTotal());
        return pageResponse;
    }

}
