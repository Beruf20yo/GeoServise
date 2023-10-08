package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class GeoServiceImplTest {
    private final GeoService geoService = new GeoServiceImpl();

    @Test
    void byIpRussiaTest(){
        assertThat(geoService.byIp("172.0.32.11"),
                samePropertyValuesAs( new Location("Moscow", Country.RUSSIA, "Lenina", 15)));
    }
    @Test
    void byIpUSATest(){
        assertThat(geoService.byIp("96.44.183.149"),
                samePropertyValuesAs(new Location("New York", Country.USA, " 10th Avenue", 32)));

    }
}
