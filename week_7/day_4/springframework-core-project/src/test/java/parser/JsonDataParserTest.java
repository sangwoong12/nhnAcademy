package parser;

import com.nhnacademy.edu.springframework.BootStrap;
import com.nhnacademy.edu.springframework.exception.JsonParsingException;
import com.nhnacademy.edu.springframework.item.WaterTariff;
import com.nhnacademy.edu.springframework.parser.DataParser;
import com.nhnacademy.edu.springframework.parser.JsonDataParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Objects;

public class JsonDataParserTest {
    private static final DataParser dataParser = new JsonDataParser();

    @Test
    void parse() {
        String path = Objects.requireNonNull(BootStrap.class.getResource("/")).getPath() + "/Tariff_20220331.json";
        Assertions.assertDoesNotThrow(() -> dataParser.parse(path));

        //성공적으로 데이터가 들어갔는지 확인
        List<WaterTariff> waterTariffs = (List<WaterTariff>) ReflectionTestUtils.getField(dataParser, "waterTariffList");

        Assertions.assertEquals(2, waterTariffs.size());

        Assertions.assertEquals("동두천시", waterTariffs.get(0).getCity());
        Assertions.assertEquals(1, waterTariffs.get(0).getStartSection());
        Assertions.assertEquals(20, waterTariffs.get(0).getEndSection());
        Assertions.assertEquals(690, waterTariffs.get(0).getSectionPrice());
        Assertions.assertEquals("가정용", waterTariffs.get(0).getSector());
    }

    @Test
    void loadExceptionTest() {
        String path = Objects.requireNonNull(BootStrap.class.getResource("/")).getPath() + "/Tariff_20220331.csv";
        Assertions.assertThrows(JsonParsingException.class, () -> dataParser.parse(path));
    }
}
