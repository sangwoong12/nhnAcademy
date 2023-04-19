package com.nhnacademy.edu.springframework.repository;

import com.nhnacademy.edu.springframework.item.WaterTariff;
import com.nhnacademy.edu.springframework.parser.DataParser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class BasicWaterTariffListRepository implements WaterTariffListRepository {

    DataParser[] parsers;

    private List<WaterTariff> waterTariffList = new ArrayList<>();

    public BasicWaterTariffListRepository(DataParser... parser) {
        this.parsers = parser;
    }

    @Override
    public void fileLoad(String filePath) {
        String extension = filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();
        //없을경우 parsers[0] 을 사용.
        DataParser dataParser = parsers[0];
        for (DataParser parser : parsers) {
            if (Objects.equals(parser.getName(), extension)) {
                dataParser = parser;
                break;
            }
        }
        this.waterTariffList = dataParser.parse(filePath);
    }

    @Override
    public List<WaterTariff> getWaterTariffByWaterUsage(int waterUsage) {
        return waterTariffList.stream()
                .filter(tariff -> tariff.getStartSection() <= waterUsage && tariff.getEndSection() >= waterUsage)
                .collect(Collectors.toList());
    }
}
