package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MessageSenderImplTEST {
    public static final String MOSCOW_IP = "172.0.32.11";
    public static final String NEW_YORK_IP = "96.44.183.149";
    public GeoService geoServiceMockito = Mockito.mock(GeoServiceImpl.class);
    public LocalizationService localizationServiceMockito = Mockito.mock(LocalizationServiceImpl.class);
    public MessageSender messageSenderMockito = new MessageSenderImpl(geoServiceMockito, localizationServiceMockito);
    public MessageSender messageSender = new MessageSenderImpl(new GeoServiceImpl(), new LocalizationServiceImpl());
    public Map<String, String> headersMockito = new HashMap<String, String>();
    public Map<String, String> headers = new HashMap<String, String>();
    public int randomIP = new Random().nextInt(150);
    public int randomIP1 = new Random().nextInt(50);
    public int randomIP2 = new Random().nextInt(50);

    @Test
    public void rusLanguageTEST(){
        String anyRusIp = "172."+randomIP+"."+randomIP1+"."+randomIP2;
        Mockito.when(geoServiceMockito.byIp(MOSCOW_IP)).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationServiceMockito.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        headersMockito.put(MessageSenderImpl.IP_ADDRESS_HEADER, MOSCOW_IP);
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, anyRusIp);
        System.out.println(anyRusIp);
        String expected = messageSenderMockito.send(headersMockito);
        String actual = messageSender.send(headers);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void usaLanguageTEST(){
        String anyUsaIp = "96."+randomIP+"."+randomIP1+"."+randomIP2;
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, anyUsaIp);
        System.out.println(anyUsaIp);
        String result = messageSender.send(headers);
        Assertions.assertTrue(result.contains("W"));
    }
}
