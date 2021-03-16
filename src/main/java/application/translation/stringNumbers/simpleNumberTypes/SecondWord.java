package application.translation.stringNumbers.simpleNumberTypes;

import application.translation.stringNumbers.SimpleNumber;

public class SecondWord  extends SimpleNumber{
    public SecondWord(String stringValue, String digitValue) {
        super(stringValue, digitValue);
    }

    @Override
    public boolean canBeFirstInPair()
    {
        return false;
    }

    @Override
    public boolean canBeSecondInPair() {
        return true;
    }
}