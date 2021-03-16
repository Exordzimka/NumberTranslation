package application.translation.fromDigits;

import java.util.HashMap;
import java.util.Map;

public class RussianFromNumberToStringTranslator extends FromNumberToStringTranslator {
    @Override
    protected Map<Integer, String> getRankName() {
        return new HashMap<Integer, String>() {{
            put(3, "тысяч");
            put(2, "миллион");
            put(1, "миллиард");
            put(0, "триллион");
        }};
    }

    private HashMap<String, String> getEndsOfWords(int rank) {
        if (rank == 3)
            return new HashMap<>() {{
                put("1", "а");
                put("2", "и");
                put("3", "и");
                put("4", "и");
            }};
        else
            return new HashMap<>() {{
                put("0", "ов");
                put("2", "а");
                put("3", "а");
                put("4", "а");
                put("5", "ов");
                put("6", "ов");
                put("7", "ов");
                put("8", "ов");
                put("9", "ов");
            }};
    }

    @Override
    protected Map<String, String> getNameOfFirstWordInRank() {
        return new HashMap<>() {{
            put("1", "сто");
            put("2", "двести");
            put("3", "триста");
            put("4", "четыреста");
            put("5", "пятьсот");
            put("6", "шестьсот");
            put("7", "семьсот");
            put("8", "восемьсот");
            put("9", "девятьсот");
        }};
    }

    @Override
    protected Map<String, String> getNameOfSecondWordInRank() {
        return new HashMap<>() {{
            put("2", "двадцать");
            put("3", "тридцать");
            put("4", "сорок");
            put("5", "пятьдесят");
            put("6", "шестьдесят");
            put("7", "семьдесят");
            put("8", "восемьдесят");
            put("9", "девяносто");
        }};
    }

    @Override
    protected Map<String, String> getNameOfThirdWordInRank() {
        return new HashMap<>() {{
            put("1", "один");
            put("2", "два");
            put("3", "три");
            put("4", "четыре");
            put("5", "пять");
            put("6", "шесть");
            put("7", "семь");
            put("8", "восемь");
            put("9", "девять");
        }};
    }

    @Override
    protected Map<String, String> getExceptions() {
        return new HashMap<>() {{
            put("10", "десять");
            put("11", "одиннадцать");
            put("12", "двенадцать");
            put("13", "тринадцать");
            put("14", "четырнадцать");
            put("15", "пятнадцать");
            put("16", "шестнадцать");
            put("17", "семнадцать");
            put("18", "восемнадцать");
            put("19", "девятнадцать");
        }};
    }

    @Override
    protected String getZero() {
        return "ноль";
    }

    @Override
    protected String translate() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numbersByRank.length; i++) {
            boolean existNumbers = false;
            boolean lastNumberIsDecided = false;
            for (int j = 0; j < numbersByRank[i].length; j++) {
                String currentNumber = numbersByRank[i][j];
                if (currentNumber.equals("0") == false && numbersByRank[i][j].isBlank() == false) {
                    switch (j) {
                        case 0:
                            result.append(getNameOfFirstWordInRank().get(currentNumber)).append(" ");
                            break;
                        case 1:
                            if (currentNumber.equals(String.valueOf(1))) {
                                String currentNumberAndNextNumbers = currentNumber + numbersByRank[i][j + 1];
                                result.append(getExceptions().get(currentNumberAndNextNumbers)).append(" ");
                                j++;
                            } else
                                result.append(getNameOfSecondWordInRank().get(currentNumber)).append(" ");
                            break;
                        case 2:
                            lastNumberIsDecided = true;
                            if(currentNumber.equals(String.valueOf(1)) && existNumbers == false) { }
                            else if(i==3 && currentNumber.equals(String.valueOf(1)))
                                result.append("одна").append(" ");
                            else
                                result.append(getNameOfThirdWordInRank().get(currentNumber)).append(" ");
                            break;
                    }
                    existNumbers = true;
                }
            }
            if (i < maxRank && existNumbers) {
                String lastNumber = numbersByRank[i][numbersByRank[i].length - 1];
                String endOfWord = null;
                if(i != 3)
                    endOfWord = getEndsOfWords(i).get(lastNumber);
                else if(lastNumberIsDecided)
                    endOfWord = getEndsOfWords(i).get(lastNumber);
                if (endOfWord == null)
                    result.append(getRankName().get(i)).append(" ");
                else
                    result.append(getRankName().get(i)).append(endOfWord).append(" ");
            }
        }
        return result.toString();
    }

    @Override
    protected String getMinus() {
        return "минус ";
    }
}
