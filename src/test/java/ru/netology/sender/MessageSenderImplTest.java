package ru.netology.sender;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceMock;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceMock;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {
    @Mock
    private LocalizationService localizationService;
    @Mock
    private GeoService geoService;
    @InjectMocks
    private MessageSenderImpl messageSender;

    //Возвращает true, если строка только из букв латинского алфавита
    boolean checkLatin(String s) {
        return s.matches("^[a-zA-Z0-9]+$");
    }

    @Test
    void sendRussiaWithCustomMock() {
        Location location = new Location("Казань", Country.RUSSIA, "Красивая", 21);
        var geoService = new GeoServiceMock();
        geoService.setLocation(location);

        var localizationService = new LocalizationServiceMock();
        localizationService.setCountryName("Россия");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        Assertions.assertFalse(checkLatin(messageSender.send(headers)));

    }

    @Test
    void sendUSAWithCustomMock() {
        Location location = new Location("LA", Country.USA, "North Iceland", 29);
        var geoService = new GeoServiceMock();
        geoService.setLocation(location);

        var localizationService = new LocalizationServiceMock();
        localizationService.setCountryName("America");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        Assertions.assertTrue(checkLatin(messageSender.send(headers)));

    }

    @Test
    void sendRussiaWithMockito() {
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Россия");
        Mockito.when(geoService.byIp("172.123.12.19"))
                .thenReturn(new Location("Казань", Country.RUSSIA, "Красивая", 21));

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        Assertions.assertFalse(checkLatin(messageSender.send(headers)));
    }

    @Test
    void sendUSAWithMockito() {
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("USA");
        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("LA", Country.USA, "North Iceland", 29));

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        Assertions.assertTrue(checkLatin(messageSender.send(headers)));
    }
}
