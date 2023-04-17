package repository;

import com.nhnacademy.edu.springframework.item.WaterTariff;
import com.nhnacademy.edu.springframework.parser.DataParser;
import com.nhnacademy.edu.springframework.repository.BasicWaterTariffListRepository;
import com.nhnacademy.edu.springframework.repository.WaterTariffListRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicWaterTariffListRepositoryTest {


    private static WaterTariffListRepository repository;

    @Test
    void fileLoad() {
        //메서드만 테스트하기위해 묵으로 객체주입
        DataParser mock = mock(DataParser.class);
        repository = new BasicWaterTariffListRepository(mock);

        WaterTariff test1 = new WaterTariff("test1", "", 1, 1000, 100);
        WaterTariff test2 = new WaterTariff("test2", "", 1, 1000, 200);
        WaterTariff test3 = new WaterTariff("test3", "", 1001, 2000, 1000);

        // parser.parse() 메서드 stubbing
        List<WaterTariff> tariffList = Arrays.asList(test1, test2, test3);
        when(mock.parse(anyString())).thenReturn(tariffList);

        // fileLoad() 메서드 호출
        repository.fileLoad("test");

        // 결과 확인
        assertEquals(tariffList, ReflectionTestUtils.getField(repository, "waterTariffList"));
    }

    @Test
    void getWaterTariffByWaterUsage() {
        //메서드만 테스트하기위해 묵으로 객체주입
        DataParser mock = mock(DataParser.class);
        repository = new BasicWaterTariffListRepository(mock);

        //테스트 데이터
        WaterTariff test1 = new WaterTariff("test1", "", 1, 1000, 100);
        WaterTariff test2 = new WaterTariff("test2", "", 1, 1000, 200);
        WaterTariff test3 = new WaterTariff("test3", "", 1001, 2000, 1000);

        List<WaterTariff> tariffList = Arrays.asList(test1, test2, test3);

        //test 데이터 강제 주입.
        ReflectionTestUtils.setField(repository, "waterTariffList", tariffList);

        //2개만 나오길 기대했는데 결과가 똑같은지 확인.
        List<WaterTariff> waterTariffByWaterUsage = repository.getWaterTariffByWaterUsage(1000);

        Assertions.assertEquals(waterTariffByWaterUsage.get(0), test1);
        Assertions.assertEquals(waterTariffByWaterUsage.get(1), test2);
        Assertions.assertEquals(2, waterTariffByWaterUsage.size());
    }
}
