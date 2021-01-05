package mcf.UFST_test;

import javax.inject.Singleton;

@Singleton
public class NumberToStringConverter {
    public NumberToStringConverter(){

    }

    public String NumberToString(double value) {
        if(value < 0) {
            throw new IllegalArgumentException("Negative number not allowed");
        }
        if(value >= 1000000) {
            throw new IllegalArgumentException("Number in millions not allowed");
        }
        String[] numbers = String.valueOf(String.format("%.2f", value)).split("\\,");

        int decimals = numbers.length > 1 ? Integer.parseInt(numbers[1]) : 0;

        String text1 = doubleToText(Integer.parseInt(numbers[0]), "DOLLAR");
        String text2 = doubleToText(decimals, "CENT");

        return formatString(text1.concat(" AND ").concat(text2));
    }

    private String doubleToText(int number, String appendingText) {
        // making the appending text plural
        char[] numberArray = number != 0 ? String.valueOf(number).toCharArray() : new char[]{};
        appendingText = number != 1 ? appendingText.concat("S") : appendingText;

        return convert(numberArray, numberArray.length, appendingText, "");
    }

    private String convert(char[] number, int index, String appendingText, String resultText) {
        resultText += " ";

        if (index == 0 && number.length == 0 && resultText.trim().isEmpty()) {
            return String.format("ZERO %s", appendingText);
        } else if (index == 0) {
            return String.format("%1$s %2$s", resultText, appendingText);
        }

        int currentIndex = number.length - index;

        switch (index % 3) {
            case 0: // hundreds
                resultText += getNumber(Integer.parseInt(String.valueOf(number[currentIndex])), false, "HUNDRED");
                break;
            case 1: // single
                resultText += getNumber(Character.getNumericValue(number[currentIndex]), false, "");
                break;
            case 2: // tens
                int tmpNumber = Integer.parseInt(number[currentIndex] + String.valueOf(number[currentIndex + 1]));
                if (tmpNumber > 10 && tmpNumber < 20) {
                    resultText += getTeenNumbers(tmpNumber);
                    index -= 1;
                    break;
                }
                resultText += getNumber(Character.getNumericValue(number[currentIndex]), true, "");
        }

        index -= 1;

        if (index == 3) {
            resultText += " THOUSAND";
        }

        return convert(number, index, appendingText, resultText);
    }

    String formatString(String txt) {
        return txt.trim().replaceAll("\\s{2,}", " ").toUpperCase();
        //return txt.substring(0, 1).toUpperCase() + txt.substring(1);
    }

    String getTeenNumbers(int number) {
        switch (number) {
            case 11:
                return "ELEVEN";
            case 12:
                return "TWELVE";
            case 13:
                return "THIRTEEN";
            case 14:
                return "FOURTEEN";
            case 15:
                return "FIFTEEN";
            case 16:
                return "SIXTEEN";
            case 17:
                return "SEVENTEEN";
            case 18:
                return "EIGHTEEN";
            case 19:
                return "NINETEEN";
            default:
                return "";
        }
    }

    String getNumber(int number, boolean tens, String appendingText) {

        String[][] numbers = new String[][]{
                {"", ""},
                {"ONE", "TEN"},
                {"TWO", "TWENTY"},
                {"THREE", "THIRTY"},
                {"FOUR", "FORTY"},
                {"FIVE", "FIFTY"},
                {"SIX", "SIXTY"},
                {"SEVEN", "SEVENTY"},
                {"EIGHT", "EIGHTY"},
                {"NINE", "NINETY"},
        };

        return numbers[number][tens ? 1 : 0] + (number > 0 ? " " + appendingText : "");
    }
}
