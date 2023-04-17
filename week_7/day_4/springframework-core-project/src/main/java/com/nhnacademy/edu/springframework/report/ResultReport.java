package com.nhnacademy.edu.springframework.report;

import com.nhnacademy.edu.springframework.item.WaterBill;

import java.util.List;

public interface ResultReport {
    void report(List<WaterBill> waterBillList);
}
