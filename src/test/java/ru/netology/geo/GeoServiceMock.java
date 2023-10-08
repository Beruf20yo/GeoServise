package ru.netology.geo;

import lombok.Setter;
import ru.netology.entity.Location;

public class GeoServiceMock implements GeoService{
    @Setter
    private Location location;
    @Override
    public Location byIp(String ip) {
        return location;
    }

    @Override
    public Location byCoordinates(double latitude, double longitude) {
        return null;
    }
}
