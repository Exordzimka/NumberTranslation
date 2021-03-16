package application.translation.fromString;


import application.translation.stringNumbers.SimpleNumber;

import java.util.HashMap;
import java.util.Map;

public class RussianFromStringToNumberTranslator extends FromStringToNumberTranslator {
    @Override
    public Map<String, String> getFirstWords() {
        Map<String, String> firstWords = new HashMap<>();
        firstWords.put("ноль", "0");
        firstWords.put("один", "1");
        firstWords.put("два", "2");
        firstWords.put("три", "3");
        firstWords.put("четыре", "4");
        firstWords.put("пять", "5");
        firstWords.put("шесть", "6");
        firstWords.put("семь", "7");
        firstWords.put("восемь", "8");
        firstWords.put("девять", "9");
        return firstWords;
    }

    @Override
    public Map<String, String> getSecondWords() {
        Map<String, String> secondWords = new HashMap<>();
        secondWords.put("тысяч", "000");
        secondWords.put("тысяча", "000");
        secondWords.put("тысячи", "000");
        secondWords.put("миллион", "000000");
        secondWords.put("миллиона", "000000");
        secondWords.put("миллионов", "000000");
        secondWords.put("миллиард", "000000000");
        secondWords.put("миллиардов", "000000000");
        secondWords.put("триллион", "000000000000");
        secondWords.put("триллионов", "000000000000");
        return secondWords;
    }

    @Override
    public Map<String, String> getStartsOfFirstWords() {
        Map<String, String> startsOfFirstWords = new HashMap<>();
        startsOfFirstWords.put("один", "один");
        startsOfFirstWords.put("два", "дв");
        startsOfFirstWords.put("три", "тр");
        startsOfFirstWords.put("четыре", "четыр");
        startsOfFirstWords.put("пять", "пят");
        startsOfFirstWords.put("шесть", "шест");
        startsOfFirstWords.put("семь", "сем");
        startsOfFirstWords.put("восемь", "восем");
        startsOfFirstWords.put("девять", "девят");
        return startsOfFirstWords;
    }

    @Override
    public Map<String, String> getEndsOfWords() {
        return new HashMap<>() {{
            put("надцать", "1");
            put("сот", "00");
            put("ста", "00");
            put("сти", "00");
            put("десят", "0");
            put("дцать", "0");
        }};
    }

    @Override
    public Map<String, String> getExceptions() {
        return new HashMap<>() {{
            put("десять", "10");
            put("сорок", "40");
            put("девяносто", "90");
            put("сто", "100");
            put("тысяча", "1000");
        }};
    }

    @Override
    protected boolean checkForAddingCompositeWords(String word) {
        for (String key : getStartsOfFirstWords().keySet()) {
            if (word.length()>=getStartsOfFirstWords().get(key).length() &&
                word.substring(0, (getStartsOfFirstWords().get(key).length())).equals(getStartsOfFirstWords().get(key))) {
                for (String secondKey : getEndsOfWords().keySet()) {
                    if (word.length()>="надцать".length()&&word.substring(word.length() - "надцать".length()).equals("надцать")) {
                        complexNumber.addPartOfNumber(new SimpleNumber(word, getEndsOfWords().get("надцать") + getFirstWords().get(key)));
                        return true;
                    } else if (word.length()>=secondKey.length() && word.substring(word.length() - secondKey.length()).equals(secondKey)) {
                        complexNumber.addPartOfNumber(new SimpleNumber(word, getFirstWords().get(key) + getEndsOfWords().get(secondKey)));
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    @Override
    public String getMinus() {
        return "минус";
    }
}

