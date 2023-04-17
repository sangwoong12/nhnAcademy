package report;

import com.nhnacademy.edu.springframework.item.WaterBill;
import com.nhnacademy.edu.springframework.report.BasicResultReport;
import com.nhnacademy.edu.springframework.report.ResultReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings(value = "-Xlint:unchecked")
public class BasicResultReportTest {
    private static final ResultReport report = new BasicResultReport();
    @Test
    void report(){
        WaterBill bill1 = new WaterBill("서울", "공업", 1000, 30000);
        WaterBill bill2 = new WaterBill("서울", "공업", 1200, 24000);
        WaterBill bill3 = new WaterBill("부산", "공업", 900, 27000);
        WaterBill bill4 = new WaterBill("부산", "공업", 1100, 22000);
        WaterBill bill5 = new WaterBill("인천", "공업", 800, 32000);
        WaterBill bill6 = new WaterBill("인천", "공업", 1300, 26000);

        List<WaterBill> waterBillList = Arrays.asList(bill1,bill2,bill3,bill4,bill5,bill6);

        report.report(waterBillList);

        //report 동작이 잘 수행되었는지 확인하기 위해 강제로 list 를 가지고옴.
        List<WaterBill> waterBills = (List<WaterBill>) ReflectionTestUtils.getField(report, "waterBills");

        //길이가 5개 인지 테스트
        assert waterBills != null;
        Assertions.assertEquals(5,waterBills.size());

        //billTotal 순으로 받아오는지 테스트
        Assertions.assertEquals(bill4,waterBills.get(0));
        Assertions.assertEquals(bill2,waterBills.get(1));
        Assertions.assertEquals(bill6,waterBills.get(2));
        Assertions.assertEquals(bill3,waterBills.get(3));
        Assertions.assertEquals(bill1,waterBills.get(4));

    }
}
