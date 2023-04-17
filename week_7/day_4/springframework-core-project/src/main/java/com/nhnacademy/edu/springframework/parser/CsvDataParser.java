package com.nhnacademy.edu.springframework.parser;

import com.nhnacademy.edu.springframework.exception.CsvParsingException;
import com.nhnacademy.edu.springframework.item.WaterTariff;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvDataParser implements DataParser {
    private static final List<WaterTariff> waterTariffList = new ArrayList<>();
    private String name = "csv";
    @Override
    public List<WaterTariff> parse(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //첫줄을 읽고 버림.
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String city = tokens[1].trim();
                String sector = tokens[2].trim();
                int startSection = 0;
                int endSection = 0;
                int sectionPrice = 0;
                if(tokens[4].matches( "[0-9]+")){
                     startSection = Integer.parseInt(tokens[4]);
                }
                if(tokens[5].matches( "[0-9]+")){
                    endSection = Integer.parseInt(tokens[5]);
                }
                if(tokens[6].matches( "[0-9]+")){
                    sectionPrice = Integer.parseInt(tokens[6]);
                }

                waterTariffList.add(new WaterTariff(city, sector, startSection, endSection, sectionPrice));
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            throw new CsvParsingException();
        }
        return waterTariffList;
    }
    @Override
    public String getName() {
        return name;
    }
}
