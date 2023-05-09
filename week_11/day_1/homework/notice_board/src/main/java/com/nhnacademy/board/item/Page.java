package com.nhnacademy.board.item;

import java.util.List;

public interface Page<T> {
    int getPageNumber();

    int getPageSize();

    int getTotalPageCount();

    long getTotalCount();

    List<T> getList();
}
