package application.translation.fromDigits;

import java.util.Map;

public abstract class FromNumberToStringTranslator {
    protected final int maxRank;
    protected String[][] numbersByRank;

    public FromNumberToStringTranslator() {
        maxRank = 4;
        numbersByRank = new String[5][];
        for (int i = 0; i < numbersByRank.length; i++) {
            numbersByRank[i] = new String[]{"", "", ""};
        }
    }

    protected abstract Map<Integer, String> getRankName();

    protected abstract Map<String, String> getNameOfFirstWordInRank();

    protected abstract Map<String, String> getNameOfSecondWordInRank();

    protected abstract Map<String, String> getNameOfThirdWordInRank();

    protected abstract Map<String, String> getExceptions();

    protected abstract String getZero();

    protected abstract String getMinus();

    public String translateNumber(String input) {
        String translatedNumber = "";
        if(input.equalsIgnoreCase("0"))
            return getZero();
        if(input.charAt(0)=='-')
            translatedNumber=getMinus();
        fillingRanks(input);
        return translatedNumber+translate();
    }

    private void fillingRanks(String input) {
        int indexInInput = input.length()-1;
        int borderOfIndexInInput = input.charAt(0)=='-'?1:0;
        for (int i = numbersByRank.length-1; i >= 0; i--) {
            for (int j = numbersByRank[i].length-1; j >= 0; j--) {
                if (indexInInput < borderOfIndexInInput)
                    break;
                numbersByRank[i][j] = String.valueOf(input.charAt(indexInInput--));
            }
            if (indexInInput < borderOfIndexInInput)
                break;
        }
    }

    protected String translate() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numbersByRank.length; i++) {
            boolean existNumbers = false;
            for (int j = 0; j < numbersByRank[i].length; j++) {
                String currentNumber = numbersByRank[i][j];
                if (currentNumber.equalsIgnoreCase("0") == false && numbersByRank[i][j].isBlank() == false) {
                    existNumbers = true;
                    switch (j) {
                        case 0:
                            result.append(getNameOfFirstWordInRank().get(currentNumber)).append(" ");
                            break;
                        case 1:
                            if(currentNumber.equalsIgnoreCase(String.valueOf(1))) {
                                String currentNumberAndNextNumbers = currentNumber + numbersByRank[i][j+1];
                                result.append(getExceptions().get(currentNumberAndNextNumbers)).append(" ");
                                j++;
                            }
                            else
                                result.append(getNameOfSecondWordInRank().get(currentNumber)).append(" ");
                            break;
                        case 2:
                            result.append(getNameOfThirdWordInRank().get(currentNumber)).append(" ");
                            break;
                    }
                }
            }
            if(i<maxRank && existNumbers)
                result.append(getRankName().get(i)).append(" ");
        }
        return result.toString();
    }
}
