package com.sewq.stringcalculator;

import java.util.Arrays;

public class StringCalculatorImpl implements StringCalculator {

    @Override
    public int add(String stringRepresentedNumbers) {
        if (stringRepresentedNumbers.isEmpty()) {
            return 0;
        }
        return Arrays.stream(stringRepresentedNumbers.split("[,\n]")).map(Integer::parseInt).reduce(0, Integer::sum);
    }
}
