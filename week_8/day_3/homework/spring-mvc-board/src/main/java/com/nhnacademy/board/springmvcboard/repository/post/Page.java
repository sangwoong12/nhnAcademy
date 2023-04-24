package com.nhnacademy.board.springmvcboard.repository.post;

import java.util.List;

public interface Page <T>{
    int getPageNumber();
    int getPageSize();
    int getTotalPageCount();

    long getTotalCount();
    List<T> getList();
}
