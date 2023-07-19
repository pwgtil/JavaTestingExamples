package org.pwgtil.foreign_exchange;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/*
 * Remember about the below dependencies:
 *  testImplementation 'org.mockito:mockito-core:3.11.2'
 *  testImplementation 'org.mockito:mockito-junit-jupiter:3.11.2'
 * */
@ExtendWith(MockitoExtension.class)
class FXConverterTest {

    @Mock
    private RemoteFXRateService service;

    private FXConverter converter;

    @BeforeEach
    void setup() {
        converter = new FXConverter(service);
    }

    @Test
    @DisplayName("Given 100.00 USD, when convert to USD, then return 100.00")
    void test1() {
        when(service.getRate("USD", "USD")).thenReturn("1.0000");
        BigDecimal result = converter.convert("USD", "USD", "100.00");
        assertEquals("100.00", result.toString());
    }

    @Test
    @DisplayName("Given 100.00 USD, when convert to EUR, then return 84.97")
    void test2() {
        when(service.getRate("USD", "EUR")).thenReturn("0.8497");

        BigDecimal result = converter.convert("USD", "EUR", "100.00");

        assertEquals("84.97", result.toString());
    }

    @Test
    @DisplayName("Test with argument matchers. Wrong currency")
    void test3() {
        // 1st arg is "USD" and 2nd arg is any string that contains "coin"
        when(service.getRate(eq("USD"), contains("coin"))).thenReturn("0.0000");

/*        // both 1st arg and 2nd arg is any string
        when(service.getRate(anyString(), contains("PL"))).thenReturn("42");

        // 1st arg is any string that starts with "US"
        // and 2nd arg is any string that ends with "BP"
        when(service.getRate(startsWith("US"), endsWith("BP"))).thenReturn("0.7266");*/

        BigDecimal result = converter.convert("USD", "Bitcoin", "100.00");

        assertEquals("0.00", result.toString());
    }

    @Test
    @DisplayName("Given any args, when service throws exception, then return -1.00")
    void test4() {
        when(service.getRate(anyString(), anyString())).thenThrow(new IllegalStateException());

        BigDecimal result = converter.convert("USD", "EUR", "100.00");

        assertEquals("-1.00", result.toString());
    }
}



/*
 * Alternative approach for Mockito without annotations. But it's limited with how to instantiate clasess.
 * Better to use the above option
 * */
/*
class FXConverterTest2 {

    private RemoteFXRateService service = mock(RemoteFXRateService.class);

    private FXConverter converter = new FXConverter(service);

}*/
