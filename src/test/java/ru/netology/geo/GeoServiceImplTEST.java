package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.*;

public class GeoServiceImplTEST {
    private final Location locationLocalHost = new Location(null, null, null, 0);
    private final Location locationMoscow = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
    private final Location locationNewYork = new Location("New York", Country.USA, " 10th Avenue", 32);
    private final Location locationRus = new Location("Moscow", Country.RUSSIA, null, 0);
    private final Location locationUsa = new Location("New York", Country.USA, null, 0);
    public final String LOCALHOST = "127.0.0.1";
    public final String MOSCOW_IP = "172.0.32.11";
    public final String NEW_YORK_IP = "96.44.183.149";
    public final String anyRusIp = "172." + new Random().nextInt(150) + "." + new Random().nextInt(50) + "." + new Random().nextInt(50);
    public final String anyUsaIp = "96." + new Random().nextInt(150) + "." + new Random().nextInt(50) + "." + new Random().nextInt(50);
    public final List<Location> actual = new ArrayList<>();
    public final List<Location> expected = new ArrayList<>();
    public final GeoServiceImpl geoService = new GeoServiceImpl();


    @Test

    public void LocationByIpTEST() {
        actual.add(locationLocalHost);
        actual.add(locationMoscow);
        actual.add(locationNewYork);
        actual.add(locationRus);
        actual.add(locationUsa);
        expected.add(geoService.byIp(LOCALHOST));
        expected.add(geoService.byIp(MOSCOW_IP));
        expected.add(geoService.byIp(NEW_YORK_IP));
        expected.add(geoService.byIp(anyRusIp));
        expected.add(geoService.byIp(anyUsaIp));
        for (int i = 0; i < actual.size(); i++) {
            String a = actual.get(i).getCity() + actual.get(i).getCountry() + actual.get(i).getStreet() + actual.get(i).getBuiling();
            String b = expected.get(i).getCity() + expected.get(i).getCountry() + expected.get(i).getStreet() + expected.get(i).getBuiling();
            Assertions.assertEquals(a, b);
            System.out.println("Проверка " + (i+1) + " завершена");
        }
    }
}
