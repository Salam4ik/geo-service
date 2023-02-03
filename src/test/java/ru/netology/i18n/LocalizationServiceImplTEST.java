package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationServiceImplTEST {
    public final LocalizationService localizationService = new LocalizationServiceImpl();
    @Test
    public void localeTest(){
        for (int i = 0; i < Country.values().length; i++){
            System.out.println("Проверка страны " + Country.values()[i]);
            if (Country.values()[i].equals(Country.RUSSIA)){
                Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.values()[i]));
            } else {
                Assertions.assertEquals("Welcome", localizationService.locale(Country.values()[i]));
            }
        }
    }
}
