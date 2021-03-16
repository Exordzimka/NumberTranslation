package application.translation.fromString;

import application.translation.exceptions.WordIncorrectException;
import application.translation.stringNumbers.ComplexNumber;
import application.translation.stringNumbers.SimpleNumber;
import application.translation.stringNumbers.simpleNumberTypes.CompositeWord;
import application.translation.stringNumbers.simpleNumberTypes.ExceptionWord;
import application.translation.stringNumbers.simpleNumberTypes.FirstWord;
import application.translation.stringNumbers.simpleNumberTypes.SecondWord;

import java.util.*;

public abstract class FromStringToNumberTranslator {
    public abstract Map<String, String> getFirstWords();

    public abstract Map<String, String> getSecondWords();

    public abstract Map<String, String> getStartsOfFirstWords();

    public abstract Map<String, String> getEndsOfWords();

    public abstract Map<String, String> getExceptions();

    public abstract String getMinus();

    protected ComplexNumber complexNumber = new ComplexNumber();

    public ComplexNumber createNumber(String[] words) throws WordIncorrectException {
        parsingWords(words);
        return complexNumber;
    }

    protected void parsingWords(String[] words) throws WordIncorrectException {
        if(words[0].equalsIgnoreCase(getMinus()))
        {
            complexNumber.setMinus(true);
            for(int i=1;i<words.length;i++)
                readWord(words[i]);
        }
        else
            for (String word : words)
                readWord(word);
        checkOnRepeatOfSecondWords();
    }

    protected void readWord(String word) throws WordIncorrectException {
        if (checkForAddingOfFirstWords(word))
            return;
        if (checkForAddingOfSecondWords(word))
            return;
        if (checkForAddingCompositeWords(word))
            return;
        checkForAddingOfException(word);
    }

    protected void checkOnRepeatOfSecondWords() throws WordIncorrectException {
        List<String> secondWords = new ArrayList<>();
        complexNumber.getPartsOfNumber().stream().filter(number -> number instanceof SecondWord).forEach(word -> secondWords.add(word.getDigitValueOfNumber()));
        if (secondWords.stream().filter(x -> Collections.frequency(secondWords, x) > 1).count() > 1)
            throw new WordIncorrectException();
    }

    protected boolean checkForAddingOfFirstWords(String word) throws WordIncorrectException {
        for (String key : getFirstWords().keySet()) {
            if (word.equals(key)) {
                SimpleNumber number = new FirstWord(word, getFirstWords().get(key));
                checkOnRightOrder(complexNumber, number);
                complexNumber.addPartOfNumber(number);
                return true;
            }
        }
        return false;
    }

    protected boolean checkForAddingOfSecondWords(String word) throws WordIncorrectException {
        for (String key : getSecondWords().keySet()) {
            if (word.equals(key)) {
                SimpleNumber number = new SecondWord(word, getSecondWords().get(key));
                checkOnRightOrder(complexNumber, number);
                complexNumber.addPartOfNumber(number);
                return true;
            }
        }
        return false;
    }

    protected boolean checkForAddingCompositeWords(String word) throws WordIncorrectException {
        for (String key : getStartsOfFirstWords().keySet()) {
            if (word.length()>=getStartsOfFirstWords().get(key).length() &&
                word.substring(0, (getStartsOfFirstWords().get(key).length())).equals(getStartsOfFirstWords().get(key))) {
                for (String secondKey : getEndsOfWords().keySet()) {
                    if (word.length()>=key.length() && word.substring(word.length() - secondKey.length()).equals(secondKey)) {
                        if (getEndsOfWords().get(secondKey).equals(String.valueOf(1))) {
                            SimpleNumber number = new CompositeWord(word, getEndsOfWords().get(secondKey) + getFirstWords().get(key));
                            checkOnRightOrder(complexNumber, number);
                            complexNumber.addPartOfNumber(number);
                        } else {
                            SimpleNumber number = new CompositeWord(word, getFirstWords().get(key) + getEndsOfWords().get(secondKey));
                            checkOnRightOrder(complexNumber, number);
                            complexNumber.addPartOfNumber(number);
                        }
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    protected boolean checkForAddingOfException(String word) throws WordIncorrectException {
        for (String key : getExceptions().keySet()) {
            if (word.equals(key)) {
                SimpleNumber number = new ExceptionWord(word, getExceptions().get(key));
                checkOnRightOrder(complexNumber, number);
                complexNumber.addPartOfNumber(number);
                return true;
            }
        }
        return false;
    }

    protected void checkOnRightOrder(ComplexNumber complexNumber, SimpleNumber newNumber) throws WordIncorrectException {
        SimpleNumber lastNumber;
        try {
            lastNumber = complexNumber.getPartsOfNumber().get(complexNumber.getPartsOfNumber().size() - 1);
        } catch (IndexOutOfBoundsException exception) {
            lastNumber = null;
        }
        if (newNumber.canBeSecondInPair() && newNumber.canBeFirstInPair() == false) {
            if (complexNumber.getPartsOfNumber().size() == 0 ||
                (lastNumber.canBeSecondInPair() && lastNumber.canBeFirstInPair() == false))
                throw new WordIncorrectException();
            else if (lastNumber.canBeFirstInPair() && lastNumber.canBeSecondInPair() == false)
                throw new WordIncorrectException();
        }
    }
}
