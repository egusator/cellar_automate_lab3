package com.example.cellar_automate_lab3;


import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Row {
    private final String rule;

    private final Integer amount;

    public Integer getRowNumber() {
        return rowNumber;
    }

    private Integer rowNumber;

    private ArrayList<Boolean> values;

    {
        rowNumber = 0;
    }

    public Row(Integer rule, int amount) {

        String newRule;
        newRule = "";

        for (int i = 0; i <= 7; i++) {
            newRule += String.valueOf(rule % 2);
            rule /= 2;
        }

        this.rule = new StringBuilder(newRule).reverse().toString();

        this.amount = amount;

        values = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if (i == 0 || i == amount - 1) {
                values.add(false);
            } else {
                values.add(ThreadLocalRandom.current().nextBoolean());
            }
        }
    }

    private Boolean getValueForNewRow(int index) {
        String baseValues =
                (values.get(index - 1) ? "1" : "0")
                        + (values.get(index) ? "1" : "0")
                        + (values.get(index + 1) ? "1" : "0");
        Boolean result = null;
        switch (baseValues) {
            case "111":
                result = (rule.charAt(0) == '1');
                break;
            case "110":
                result = (rule.charAt(1) == '1');
                break;
            case "101":
                result = (rule.charAt(2) == '1');
                break;
            case "100":
                result = (rule.charAt(3) == '1');
                break;
            case "011":
                result = (rule.charAt(4) == '1');
                break;
            case "010":
                result = (rule.charAt(5) == '1');
                break;
            case "001":
                result = (rule.charAt(6) == '1');
                break;
            case "000":
                result = (rule.charAt(7) == '1');
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + baseValues);
        };
        return result;
    }

    public ArrayList<Boolean> getValues() {
        return values;
    }

    public void evolve() {
        ArrayList<Boolean> evolvedRow = new ArrayList<>();
        evolvedRow.add(false);
        for (int i = 1; i < amount - 1; i++) {
            evolvedRow.add(getValueForNewRow(i));
        }
        evolvedRow.add(false);
        rowNumber++;
        this.values = evolvedRow;
    }


}
