package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.item.WaterBill;

import java.util.List;

public interface WaterBillService {
    List<WaterBill> calculateFee(int waterUsage);
}
