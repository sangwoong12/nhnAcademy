package com.nhnacademy.edu.springframework.item;

public class WaterBill {
    private final String city;
    private final String sector;
    private final int unitPrice;
    private final int billTotal;

    public WaterBill(String city, String sector, int unitPrice, int billTotal) {
        this.city = city;
        this.sector = sector;
        this.unitPrice = unitPrice;
        this.billTotal = billTotal;
    }

    public int getBillTotal() {
        return billTotal;
    }

    @Override
    public String toString() {
        return "WaterBill{" +
                "city='" + city + '\'' +
                ", sector='" + sector + '\'' +
                ", unitPrice=" + unitPrice +
                ", billTotal=" + billTotal +
                '}';
    }
}
