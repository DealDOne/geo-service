package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    static class LocationPlusIp{
        static Location location;
        static String ip;

        public LocationPlusIp(Location location, String ip){
            this.location = location;
            this.ip = ip;
        }
        public Location getLocation() {
            return location;
        }
        public String getIp() {
            return ip;
        }
    };

    @ParameterizedTest
    @MethodSource
    void testWithMethodSource(LocationPlusIp argument) {

        Location location = argument.getLocation();
        String ip = argument.getIp();

        GeoService geoService = new GeoServiceImpl();
        Assertions.assertEquals(location.getCity(), geoService.byIp(ip).getCity());
        Assertions.assertEquals(location.getCountry(), geoService.byIp(ip).getCountry());
        Assertions.assertEquals(location.getStreet(), geoService.byIp(ip).getStreet());
        Assertions.assertEquals(location.getBuiling(), geoService.byIp(ip).getBuiling());
    }

    static Stream<LocationPlusIp> testWithMethodSource() {
        return Stream.of(new LocationPlusIp(new Location("Moscow", Country.RUSSIA, "Lenina", 15), "172.0.32.11"),
                new LocationPlusIp(new Location("New York", Country.USA, "10th Avenue", 32), "96.44.183.149"),
                new LocationPlusIp(new Location(null, null, null, 0), "127.0.0.1"));
    }

    @ParameterizedTest
    @ArgumentsSource(LocationsArgumentsProvider.class)
    void testByIpArgumentsSource(Location location, String ip) {
        GeoService geoService = new GeoServiceImpl();
        Assertions.assertEquals(location.getCity(), geoService.byIp(ip).getCity());
        Assertions.assertEquals(location.getCountry(), geoService.byIp(ip).getCountry());
        Assertions.assertEquals(location.getStreet(), geoService.byIp(ip).getStreet());
        Assertions.assertEquals(location.getBuiling(), geoService.byIp(ip).getBuiling());
    }
    static class LocationsArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new Location("Moscow", Country.RUSSIA, "Lenina", 15), "172.0.32.11"),
                    Arguments.of(new Location("New York", Country.USA, " 10th Avenue", 32), "96.44.183.149"),
                    Arguments.of(new Location(null, null, null, 0), "127.0.0.1")
            );
        }
    }
}