package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.item.WaterBill;
import com.nhnacademy.edu.springframework.item.WaterTariff;
import com.nhnacademy.edu.springframework.repository.WaterTariffListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicWaterBillService implements WaterBillService {
    WaterTariffListRepository waterTariffListRepository;

    @Autowired
    public BasicWaterBillService(WaterTariffListRepository waterTariffListRepository) {
        this.waterTariffListRepository = waterTariffListRepository;
    }

    @Override
    public List<WaterBill> calculateFee(int waterUsage) {
        List<WaterTariff> feeTableByWaterUsage = waterTariffListRepository.getWaterTariffByWaterUsage(waterUsage);
        List<WaterBill> waterBillList = new ArrayList<>();
        feeTableByWaterUsage
                .forEach(waterTariff -> waterBillList.add(
                        new WaterBill(waterTariff.getCity(),
                                waterTariff.getSector(),
                                waterTariff.getSectionPrice(),
                                waterTariff.getSectionPrice() * waterUsage)));
        return waterBillList;
    }
}
