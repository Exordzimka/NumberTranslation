package application.translation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public boolean wordIsNumber(String word) {
        return containsOnlyDigit(word);
    }

    public boolean wordIsStringNumber(String word) {
        return !containsDigit(word);
    }

    private boolean containsDigit(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (Character.isDigit(word.charAt(i)))
                return true;
        }
        return false;
    }

    private boolean containsOnlyDigit(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (Character.isDigit(word.charAt(i)) == false && i != 0 && word.charAt(0) == '-')
                return false;
        }
        return true;
    }

    public boolean containLatin(String input) {
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public boolean containCyrillic(String input) {
        Pattern patter = Pattern.compile("[а-яА-Я]");
        Matcher matcher = patter.matcher(input);
        return matcher.find();
    }
}
