package com.nhnacademy.edu.springframework.repository;

import com.nhnacademy.edu.springframework.item.WaterTariff;

import java.util.List;

public interface WaterTariffListRepository {
    void fileLoad(String filePath);
    List<WaterTariff> getWaterTariffByWaterUsage(int waterUsage);
}
