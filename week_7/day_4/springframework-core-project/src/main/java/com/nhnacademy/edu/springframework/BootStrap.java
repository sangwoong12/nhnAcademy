package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.config.JavaConfig;
import com.nhnacademy.edu.springframework.item.WaterBill;
import com.nhnacademy.edu.springframework.report.ResultReport;
import com.nhnacademy.edu.springframework.repository.WaterTariffListRepository;
import com.nhnacademy.edu.springframework.service.WaterBillService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BootStrap {
    //확장자만 바꾸어도 동작.
    private static final String FILENAME = "/Tariff_20220331.json";

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(JavaConfig.class)) {

            //확장자에 따라 자동으로 parser 를 선택
            WaterTariffListRepository repository = context.getBean("basicWaterTariffListRepository", WaterTariffListRepository.class);

            WaterBillService service = context.getBean("basicWaterBillService", WaterBillService.class);
            ResultReport report = context.getBean("basicResultReport", ResultReport.class);

            String path = Objects.requireNonNull(BootStrap.class.getResource("/")).getPath() + FILENAME;

            repository.fileLoad(path);
            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < 3; i++) {
                System.out.print("> ");
                int waterUsage = scanner.nextInt();

                List<WaterBill> waterBillList = service.calculateFee(waterUsage);

                report.report(waterBillList);

            }
        }
    }
}
