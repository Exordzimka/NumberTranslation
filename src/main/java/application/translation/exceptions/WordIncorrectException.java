package application.translation.exceptions;

public class WordIncorrectException extends Exception {
    public WordIncorrectException()
    {
        super("Incorrect word in input string!");
    }
}
