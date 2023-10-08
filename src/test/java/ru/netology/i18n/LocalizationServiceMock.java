package ru.netology.i18n;

import lombok.Setter;
import ru.netology.entity.Country;

public class LocalizationServiceMock implements LocalizationService{


    @Override
    public String locale(Country country) {
        return country.name();
    }
}
