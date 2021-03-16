package application.translation.stringNumbers.simpleNumberTypes;

import application.translation.stringNumbers.SimpleNumber;

public class CompositeWord extends SimpleNumber{
    public CompositeWord(String stringValue, String digitValue) {
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
