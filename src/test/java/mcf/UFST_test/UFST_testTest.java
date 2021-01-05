package mcf.UFST_test;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;

@MicronautTest
public class UFST_testTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testFormatString() {
        NumberToStringConverter converter = new NumberToStringConverter();
        Assertions.assertEquals("THIS IS FORMATTED CORRECT!", converter.formatString(" tHiS  is   formatted CORRECT! "));
    }

    @Test
    void testGetTeenNumber() {
        NumberToStringConverter converter = new NumberToStringConverter();
        Assertions.assertEquals("FOURTEEN",converter.getTeenNumbers(14));
        Assertions.assertEquals("",converter.getTeenNumbers(9));
        Assertions.assertEquals("",converter.getTeenNumbers(20));
    }

    @Test
    void testGetNumber() {
        NumberToStringConverter converter = new NumberToStringConverter();
        Assertions.assertEquals("FORTY ",converter.getNumber(4, true, ""));
        Assertions.assertEquals("ONE ",converter.getNumber(1, false, ""));
    }

    @Test
    void testConverterNegativeValue() {
        NumberToStringConverter converter = new NumberToStringConverter();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            converter.NumberToString(-111.2);
        });
    }

    @Test
    void testConverterValueInMillion() {
        NumberToStringConverter converter = new NumberToStringConverter();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            converter.NumberToString(1000000.2);
        });
    }

    /*@Test
    void testConverterCorrectValue() {
        NumberToStringConverter converter = new NumberToStringConverter();
        Assertions.assertEquals("THIRTY THOUSAND TWO HUNDRED ELEVEN DOLLARS AND FORTY FOUR CENTS"
                                , converter.NumberToString(30211.44));
    }*/

}
