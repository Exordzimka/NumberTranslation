package application.translation.stringNumbers;

import java.util.HashMap;
import java.util.Map;

public class SimpleNumber {
    Map<String, String> equivalent;
    public SimpleNumber(String stringValue, String digitValue)
    {
        equivalent = new HashMap<>()
        {{
            put(stringValue, digitValue);
        }};
    }

    public String getStringValueOfNumber()
    {
        StringBuilder stringValue = new StringBuilder();
        for(String key : equivalent.keySet())
            stringValue.append(key);
        return stringValue.toString();
    }

    public String getDigitValueOfNumber()
    {
        StringBuilder digitValue = new StringBuilder();
        for(String key : equivalent.keySet())
            digitValue.append(equivalent.get(key));
        return digitValue.toString();
    }

    public boolean canBeFirstInPair()
    {
        return true;
    }

    public boolean canBeSecondInPair()
    {
        return true;
    }
}
