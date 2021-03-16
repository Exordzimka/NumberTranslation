package application.translation.stringNumbers;

import java.util.ArrayList;
import java.util.List;

public class ComplexNumber {
    private boolean numberWithMinus = false;
    private List<SimpleNumber> partsOfNumber;

    public ComplexNumber() {
        partsOfNumber = new ArrayList<>();
    }

    public List<SimpleNumber> getPartsOfNumber() {
        return partsOfNumber;
    }

    public void addPartOfNumber(SimpleNumber simpleStringNumber) {
        partsOfNumber.add(simpleStringNumber);
    }

    public boolean isHaveMinus(){
        return numberWithMinus;
    }

    public void setMinus(boolean minus){
        numberWithMinus = true;
    }

    public String getDigitValues() {
        String digitValues = "";
        for (int i = partsOfNumber.size() - 1; i >= 0; i--)
            digitValues = deleteZeros(partsOfNumber.get(i).getDigitValueOfNumber(), digitValues) + digitValues;
        return isHaveMinus()?"-"+digitValues:digitValues;
    }

    private String deleteZeros(String currentValue, String nextValue) {
        if(nextValue.isBlank() || nextValue.charAt(0) != '0')
        {
            try {
                boolean countOfZerosMoreThanLength;
                StringBuilder zeros = new StringBuilder();
                zeros.append("0".repeat(nextValue.length()));
                countOfZerosMoreThanLength = zeros.length() > currentValue.length();
                return countOfZerosMoreThanLength ?
                       currentValue.replaceAll("0", "") :
                       currentValue.replaceFirst(zeros.toString(), "");
            } catch (NullPointerException ignored) { }
        }
        return currentValue;
    }
}
