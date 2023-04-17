package com.nhnacademy.edu.springframework.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = {"순번", "단계", "단계별 기본요금(원)"})
public class WaterTariff {
    @JsonProperty("지자체명")
    private String city;
    @JsonProperty("업종")
    private String sector;
    @JsonProperty("구간시작(세제곱미터)")
    private int startSection;
    @JsonProperty("구간끝(세제곱미터)")
    private int endSection;
    @JsonProperty("구간금액(원)")
    private int sectionPrice;

    public WaterTariff() {
    }

    public WaterTariff(String city, String sector, int startSection, int endSection, int sectionPrice) {
        this.city = city;
        this.sector = sector;
        this.startSection = startSection;
        this.endSection = endSection;
        this.sectionPrice = sectionPrice;
    }


    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public int getStartSection() {
        return startSection;
    }

    public int getEndSection() {
        return endSection;
    }

    public int getSectionPrice() {
        return sectionPrice;
    }

}
