package mcf.UFST_test;

import javax.inject.Singleton;

@Singleton
public class NumberToStringConverter {
    public NumberToStringConverter(){

    }

    public String NumberToString(double value) {
        return "implement this "+ value;
    }
}
