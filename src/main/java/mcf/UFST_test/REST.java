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
        return converter.NumberToString(number);
    }

    @Get(value = "/test", produces = MediaType.TEXT_PLAIN)
    public String test() {
        return "It's working!";
    }
}
