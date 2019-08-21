package com.sewq.stringcalculator;

import java.util.Arrays;

public class StringCalculatorImpl implements StringCalculator {

    @Override
    public int add(String stringRepresentedNumbers) throws IllegalAccessException {

        if(true){
            throw new IllegalAccessException();
        }

        if (stringRepresentedNumbers.isEmpty()) {
            return 0;
        }
        if (stringRepresentedNumbers.startsWith("//")) {
            String[] stringHeader = stringRepresentedNumbers.split("(//)|(\n)");
            return getReduce(stringHeader[2], stringHeader[1]);
        }
        return getReduce(stringRepresentedNumbers, "[,\n]");
    }

    private Integer getReduce(String numbers, String separator) {
        return Arrays.stream(numbers.split("[" + separator + "\n]")).map(Integer::parseInt).reduce(0, Integer::sum);
    }
}
