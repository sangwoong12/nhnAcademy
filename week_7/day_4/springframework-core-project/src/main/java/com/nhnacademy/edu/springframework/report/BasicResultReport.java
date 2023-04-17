package com.nhnacademy.edu.springframework.report;

import com.nhnacademy.edu.springframework.item.WaterBill;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasicResultReport implements ResultReport {
    private static final int COUNT = 5;

    private List<WaterBill> waterBills;

    @Override
    public void report(List<WaterBill> waterBillList) {
        waterBills = waterBillList.stream().sorted(Comparator.comparing(WaterBill::getBillTotal))
                .limit(COUNT).collect(Collectors.toList());
        waterBills.stream().forEach(System.out::println);
    }
}
