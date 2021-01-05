package mcf.UFST_test;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/")
public class REST {

    private final NumberToStringConverter converter;

    public REST(NumberToStringConverter converter) {
        this.converter = converter;
    }

    @Get(value = "/numbertotext/{number}", produces = MediaType.TEXT_PLAIN)
    public String numberToText(double number) {
        try {
            return converter.NumberToString(number);
        } catch (IllegalArgumentException ex) {
            return "Error: " + ex.getMessage();
        }

    }

    @Get(value = "/test", produces = MediaType.TEXT_PLAIN)
    public String test() {
        return "It's working!";
    }
}
