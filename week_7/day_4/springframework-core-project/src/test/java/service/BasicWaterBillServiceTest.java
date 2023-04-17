package service;

import com.nhnacademy.edu.springframework.item.WaterBill;
import com.nhnacademy.edu.springframework.item.WaterTariff;
import com.nhnacademy.edu.springframework.repository.WaterTariffListRepository;
import com.nhnacademy.edu.springframework.service.BasicWaterBillService;
import com.nhnacademy.edu.springframework.service.WaterBillService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicWaterBillServiceTest {

    @Test
    void calculateFee() {
        WaterTariffListRepository mock = mock(WaterTariffListRepository.class);
        WaterBillService service = new BasicWaterBillService(mock);
        //테스트 데이터
        WaterTariff test1 = new WaterTariff("test1", "", 1, 1000, 100);
        WaterTariff test2 = new WaterTariff("test2", "", 1, 1000, 200);
        List<WaterTariff> waterTariffList = Arrays.asList(test1, test2);

        when(mock.getWaterTariffByWaterUsage(1000)).thenReturn(waterTariffList);

        List<WaterBill> result = service.calculateFee(1000);

        //결과의 billTotal이 가격 * 사용량으로 나오는지 확인.
        assertEquals(test1.getSectionPrice()*1000,result.get(0).getBillTotal());
        assertEquals(test2.getSectionPrice()*1000,result.get(1).getBillTotal());
    }
}
