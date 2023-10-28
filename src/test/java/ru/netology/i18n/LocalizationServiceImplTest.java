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
    void testLocale(Country country, String expectedString) {
        LocalizationService localizationService = new LocalizationServiceImpl();
            Assertions.assertEquals(expectedString, localizationService.locale(country));
    }

    static class CountryArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                    Arguments.of(Country.GERMANY, "Welcome"),
                    Arguments.of(Country.USA, "Welcome"),
                    Arguments.of(Country.BRAZIL, "Welcome")
            );
        }
    }
}