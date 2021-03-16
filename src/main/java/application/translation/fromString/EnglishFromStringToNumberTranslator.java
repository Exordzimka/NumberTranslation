package application.translation.fromString;


import application.translation.exceptions.WordIncorrectException;
import application.translation.stringNumbers.ComplexNumber;
import application.translation.stringNumbers.SimpleNumber;
import application.translation.stringNumbers.simpleNumberTypes.SecondWord;

import java.util.*;

public class EnglishFromStringToNumberTranslator extends FromStringToNumberTranslator {

    @Override
    public Map<String, String> getFirstWords() {
        Map<String, String> firstWords = new HashMap<>();
        firstWords.put("zero", "0");
        firstWords.put("one", "1");
        firstWords.put("two", "2");
        firstWords.put("three", "3");
        firstWords.put("four", "4");
        firstWords.put("five", "5");
        firstWords.put("six", "6");
        firstWords.put("seven", "7");
        firstWords.put("eight", "8");
        firstWords.put("nine", "9");
        return firstWords;
    }

    @Override
    public Map<String, String> getStartsOfFirstWords() {
        return new HashMap<>(){{
            put("one", "one");
            put("two", "tw");
            put("three", "th");
            put("four", "four");
            put("five", "fif");
            put("six", "six");
            put("seven", "seven");
            put("eight", "eight");
            put("nine", "nine");
        }};
    }

    @Override
    public Map<String, String> getSecondWords() {
        return new HashMap<>(){{
            put("hundred", "00");
            put("thousand", "000");
            put("million", "000000");
            put("billion", "000000000");
            put("trillion", "000000000000");
        }};
    }

    @Override
    public Map<String, String> getEndsOfWords() {
        return new HashMap<>(){{
            put("teen", "1");
            put("ty", "0");
        }};
    }
    @Override
    public Map<String, String> getExceptions(){
        return new HashMap<>(){{
            put("eleven", "11");
            put("twelve", "12");
        }};
    }

    @Override
    public String getMinus() {
        return "minus";
    }

    @Override
    protected void checkOnRepeatOfSecondWords() throws WordIncorrectException {
        List<String> secondWords = new ArrayList<>();
        complexNumber.getPartsOfNumber().stream().filter(number -> number instanceof SecondWord &&
                                                                   number.getStringValueOfNumber().equalsIgnoreCase("hundred")==false)
                .forEach(word -> secondWords.add(word.getDigitValueOfNumber()));
        if (secondWords.stream().filter(x -> Collections.frequency(secondWords, x) > 1).count() > 1)
            throw new WordIncorrectException();
    }

    @Override
    protected void checkOnRightOrder(ComplexNumber complexNumber, SimpleNumber newNumber) throws WordIncorrectException {
        SimpleNumber lastNumber;
        try {
            lastNumber = complexNumber.getPartsOfNumber().get(complexNumber.getPartsOfNumber().size() - 1);
        } catch (IndexOutOfBoundsException exception) {
            lastNumber = new SimpleNumber("","");
        }
        if (newNumber.canBeSecondInPair() && newNumber.canBeFirstInPair() == false) {
            if (complexNumber.getPartsOfNumber().size() == 0 ||
                (lastNumber.canBeSecondInPair() && lastNumber.canBeFirstInPair() == false &&
                 lastNumber.getStringValueOfNumber().equalsIgnoreCase("hundred")==false))
                throw new WordIncorrectException();
            else if (lastNumber.canBeFirstInPair() && lastNumber.canBeSecondInPair() == false)
                throw new WordIncorrectException();
        }
    }
}
