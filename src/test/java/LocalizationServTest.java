import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.entity.Country;

import java.util.stream.Stream;

public class LocalizationServTest {

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("Начало всех тестов LocalizationService");
    }

    @AfterAll
    public static void afterAllTests() {
        System.out.println("Все тесты LocalizationService завершены");
    }

    @ParameterizedTest
    @MethodSource("parametersDefinition")
    public void localizationServiceTest(Country country, String expectedMessage) {
        System.out.println("Тестирование локализации для страны: " + country);
        LocalizationService localizationServiceImpl = new LocalizationServiceImpl();
        String message = localizationServiceImpl.locale(country);
        Assertions.assertEquals(expectedMessage, message);
        System.out.println("Ожидаемое сообщение: \"" + expectedMessage + "\". Полученное сообщение: \"" + message + "\".");
    }

    private static Stream<Arguments> parametersDefinition() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.Korea, "Welcome"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.Japan, "Welcome")
        );
    }
}
