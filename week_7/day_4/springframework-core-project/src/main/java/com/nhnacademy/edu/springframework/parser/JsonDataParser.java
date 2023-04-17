package com.nhnacademy.edu.springframework.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.exception.JsonParsingException;
import com.nhnacademy.edu.springframework.item.WaterTariff;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonDataParser implements DataParser {
    private List<WaterTariff> waterTariffList = new ArrayList<>();
    private String name = "json";
    @Override
    public List<WaterTariff> parse(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(filePath);

            waterTariffList = objectMapper.readValue(file, new TypeReference<>() {
            });

            return waterTariffList;
        } catch (IOException e) {
            throw new JsonParsingException();
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
