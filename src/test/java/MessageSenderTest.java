import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.entity.Country.*;

public class MessageSenderTest {

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("Начало всех тестов отправки сообщений");
    }

    @AfterAll
    public static void afterAllTests() {
        System.out.println("Все тесты отправки сообщений завершены");
    }

    @ParameterizedTest
    @CsvSource({
            "172.123.123.123, Добро пожаловать",
            "172.32.32.32, Добро пожаловать",
            "96.96.96.96, Welcome"
    })
    public void messageSendTest(String ipAdress, String expectedMessage) {
        System.out.println("Тестирование отправки сообщения с IP-адресом: " + ipAdress);
        Map<String, String> headers = new HashMap<>();
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        Mockito.when(geoService.byIp(Mockito.anyString())).thenAnswer(i -> {
            String ip = i.getArgument(0, String.class);
            if (ip.startsWith("172")) {
                return new Location("Moscow", RUSSIA, null, 0);
            } else {
                return new Location("New York", USA, null, 0);
            }
        });

        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER,ipAdress);
        String message = messageSender.send(headers);
        Assertions.assertEquals(expectedMessage, message);
        System.out.println(" Ожидаемое сообщение: " + expectedMessage + ". Полученное сообщение: " + message);
    }
}
