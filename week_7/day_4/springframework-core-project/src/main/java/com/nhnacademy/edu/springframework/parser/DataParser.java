package com.nhnacademy.edu.springframework.parser;

import com.nhnacademy.edu.springframework.item.WaterTariff;

import java.util.List;

public interface DataParser {
    List<WaterTariff> parse(String filePath);
    String getName();
}
