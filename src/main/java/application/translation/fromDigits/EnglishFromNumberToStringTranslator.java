package application.translation.fromDigits;

import java.util.HashMap;
import java.util.Map;

public class EnglishFromNumberToStringTranslator extends FromNumberToStringTranslator {
    @Override
    protected Map<Integer, String> getRankName() {
        return new HashMap<Integer, String>(){{
            put(3, "thousand");
            put(2, "million");
            put(1, "billion");
            put(0, "trillion");
        }};
    }

    @Override
    protected Map<String, String> getNameOfFirstWordInRank() {
        return new HashMap<>(){{
            put("1", "one hundred");
            put("2", "two hundred");
            put("3", "three hundred");
            put("4", "four hundred");
            put("5", "five hundred");
            put("6", "six hundred");
            put("7", "seven hundred");
            put("8", "eight hundred");
            put("9", "nine hundred");
        }};
    }

    @Override
    protected Map<String, String> getNameOfSecondWordInRank() {
        return new HashMap<>(){{
            put("2", "twenty");
            put("3", "thirty");
            put("4", "forty");
            put("5", "fifty");
            put("6", "sixty");
            put("7", "seventy");
            put("8", "eighty");
            put("9", "ninety");
        }};
    }

    @Override
    protected Map<String, String> getNameOfThirdWordInRank() {
        return new HashMap<>(){{
            put("1", "one");
            put("2", "two");
            put("3", "three");
            put("4", "four");
            put("5", "five");
            put("6", "six");
            put("7", "seven");
            put("8", "eight");
            put("9", "nine");
        }};
    }

    @Override
    protected Map<String, String> getExceptions() {
        return new HashMap<>(){{
            put("10", "ten");
            put("11", "eleven");
            put("12", "twelve");
            put("13", "thirteen");
            put("14", "fourteen");
            put("15", "fifteen");
            put("16", "sixteen");
            put("17", "seventeen");
            put("18", "eighteen");
            put("19", "nineteen");
        }};
    }

    @Override
    protected String getZero() {
        return "zero";
    }

    @Override
    protected String getMinus() {
        return "minus ";
    }
}
