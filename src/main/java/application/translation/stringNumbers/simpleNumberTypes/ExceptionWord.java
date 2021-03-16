package application.translation.stringNumbers.simpleNumberTypes;

import application.translation.stringNumbers.SimpleNumber;

public class ExceptionWord extends SimpleNumber{
    public ExceptionWord(String stringValue, String digitValue) {
        super(stringValue, digitValue);
    }

    @Override
    public boolean canBeFirstInPair()
    {
        return true;
    }

    @Override
    public boolean canBeSecondInPair() {
        return false;
    }
}
