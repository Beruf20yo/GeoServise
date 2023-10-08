package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {
    private final LocalizationService localizationService = new LocalizationServiceImpl();

    //Возвращает true, если строка только из букв латинского алфавита
    boolean checkLatin(String s) {
        return s.matches("^[a-zA-Z0-9]+$");
    }
    @Test
    void localeRussiaTest(){
        Assertions.assertFalse(checkLatin(localizationService.locale(Country.RUSSIA)));
    }
    @Test
    void localeUSATest(){

        Assertions.assertTrue(checkLatin(localizationService.locale(Country.USA)));
    }

}
