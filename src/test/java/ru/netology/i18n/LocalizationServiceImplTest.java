package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @ParameterizedTest
    @ArgumentsSource(CountryArgumentsProvider.class)
    void testLocale(Country country) {
        LocalizationService localizationService = new LocalizationServiceImpl();
        if (country.equals(Country.RUSSIA)){
            String expectedValue = "Добро пожаловать";
            Assertions.assertEquals(expectedValue, localizationService.locale(country));
        } else {
            String expectedValue = "Welcome";
            Assertions.assertEquals(expectedValue, localizationService.locale(country));
        }
    }

    static class CountryArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(Country.RUSSIA),
                    Arguments.of(Country.GERMANY),
                    Arguments.of(Country.USA),
                    Arguments.of(Country.BRAZIL)
            );
        }
    }
}