package application.translation.stringNumbers.simpleNumberTypes;

import application.translation.stringNumbers.SimpleNumber;

public class FirstWord extends SimpleNumber{
    public FirstWord(String stringValue, String digitValue) {
        super(stringValue, digitValue);
    }

    @Override
    public boolean canBeFirstInPair()
    {
        return true;
    }

    @Override
    public boolean canBeSecondInPair() {
        return true;
    }
}
