import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;

import ru.netology.geo.GeoServiceImpl;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;


import java.util.stream.Stream;

public class GeoServTest {
    @Test
    public void geoServiceTestByCoordinates() {
        System.out.println("Тестирование метода byCoordinates");
        Class<RuntimeException> excepted = RuntimeException.class;
        double latitude = 0;
        double longitude = 0;
        System.out.println("Проверка выбрасывания исключения в geoServiceTestByCoordinates при координатах: широта " + latitude + ", долгота " + longitude);
        Executable executable = () -> sut.byCoordinates(latitude, longitude);
        Assertions.assertThrows(excepted, executable);
        System.out.println("Тест geoServiceTestByCoordinates завершен, исключение выброшено как ожидалось");
    }
    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("Начало выполнения всех тестов GeoService");
    }
    @AfterAll
    public static void afterAllTests() {
        System.out.println("Все тесты GeoService выполнены");
    }
    GeoService sut = new GeoServiceImpl();
    @ParameterizedTest
    @MethodSource("parametersDefinition")
    public void geoServiceTestByIP(String ipAdress, Country expectedCountry) {
        System.out.println("Тестирование метода byIp с IP-адресом: " + ipAdress);
        Location result = sut.byIp(ipAdress);
        System.out.println("Результат теста geoServiceTestByIP: ожидаемая страна - " + expectedCountry + ", полученная страна - " + (result != null ? result.getCountry() : "null"));
        Assertions.assertEquals(expectedCountry, result != null ? result.getCountry() : null);
    }

    private static Stream<Arguments> parametersDefinition() {
        return Stream.of(
                Arguments.of("172.25.100.100", Country.RUSSIA),
                Arguments.of("96.168.168.168", Country.USA),
                Arguments.of("111.1.1.1", null)
        );
    }
}
