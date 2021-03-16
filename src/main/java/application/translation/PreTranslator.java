package application.translation;

import application.translation.exceptions.WordIncorrectException;
import application.translation.fromDigits.EnglishFromNumberToStringTranslator;
import application.translation.fromDigits.FromNumberToStringTranslator;
import application.translation.fromDigits.RussianFromNumberToStringTranslator;
import application.translation.fromString.EnglishFromStringToNumberTranslator;
import application.translation.fromString.RussianFromStringToNumberTranslator;
import application.translation.fromString.FromStringToNumberTranslator;
import application.translation.stringNumbers.ComplexNumber;

import java.util.HashMap;
import java.util.Map;

public class PreTranslator {
    private final Validator validator = new Validator();
    private final Map<String, FromStringToNumberTranslator> digitCreatorMap;
    private final Map<String, FromNumberToStringTranslator> stringNumberCreatorMap;

    public PreTranslator()
    {
        digitCreatorMap = new HashMap<>(){{
           put("russian", new RussianFromStringToNumberTranslator());
           put("english", new EnglishFromStringToNumberTranslator());
        }};
        stringNumberCreatorMap = new HashMap<>(){{
            put("russian", new RussianFromNumberToStringTranslator());
            put("english", new EnglishFromNumberToStringTranslator());
        }};
    }

    public String translate(String input, String language, String fromTo) throws WordIncorrectException {
        if(language.equalsIgnoreCase("russian") && validator.containLatin(input))
            return "Input contains latin characters!";
        else if(language.equalsIgnoreCase("english") && validator.containCyrillic(input))
            return "Input contains cyrillic characters!";
        if(fromTo.equalsIgnoreCase("toDigits") && validator.wordIsStringNumber(input)==false)
            return "Input contains digits";
        if(fromTo.equalsIgnoreCase("toString") && validator.wordIsNumber(input)==false)
            return "Input contains not only digits";
        String[] words = input.replaceAll("[ |\r|\n]*  *", " ").trim().split(" ");
        return fromTo.equalsIgnoreCase("toDigits")?fromStringToNumber(words, language) : fromNumberToString(words[0], language);
    }

    public String fromNumberToString(String input, String language) throws WordIncorrectException {
        input = input.replaceAll(" ", "");
        if(validator.wordIsNumber(input)==false)
            return "Can not translate";
        FromNumberToStringTranslator creator = stringNumberCreatorMap.get(language);
        return creator.translateNumber(input);
    }

    public String fromStringToNumber(String[] input, String language) throws WordIncorrectException {
        FromStringToNumberTranslator creator = digitCreatorMap.get(language);
        ComplexNumber complexStringNumber = creator.createNumber(input);
        return complexStringNumber.getDigitValues();
    }
}
