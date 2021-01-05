package mcf.UFST_test;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

@Controller("/")
public class REST {

    private final NumberToStringConverter converter;

    public REST(NumberToStringConverter converter) {
        this.converter = converter;
    }

    @Get(value = "/numbertotext", produces = MediaType.TEXT_PLAIN)
    public String numberToText(@QueryValue String input) {
        try {
            System.out.println(input);
            double value = Double.parseDouble(input);
            System.out.println(value);
            return converter.NumberToString(value);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
            System.out.println(ex.toString());
            return "Error: " + ex.getMessage();
        }

    }

    @Get(value = "/test", produces = MediaType.TEXT_PLAIN)
    public String test() {
        return "It's working!";
    }
}
