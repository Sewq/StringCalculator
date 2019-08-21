package com.sewq.stringcalculator;

import com.sewq.stringcalculator.parser.DelimiterParser;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalculatorImpl implements StringCalculator {

    private static final int SPLIT_LIMIT = 3;
    private static final String DEFAULT_DELIMITER = ",";
    private static final String DELIMITERS_FROM_NUMBERS_SEPARATOR = "//|\n";
    private static final String NEW_LINE = "|\n";
    private static final String CUSTOM_DELIMITERS_INDICATOR = "//";
    private static final int LIMIT = 1000;

    @Override
    public int add(String calculationInput) throws IllegalAccessException {

        if (noInputProvided(calculationInput)) {
            return 0;
        }

        if (calculationInput.startsWith(CUSTOM_DELIMITERS_INDICATOR)) {
            return calculateWithCustomSeparator(calculationInput);
        }
        return performCalculations(calculationInput, DEFAULT_DELIMITER);
    }

    private boolean noInputProvided(String stringRepresentedNumbers) {
        return stringRepresentedNumbers.isEmpty();
    }

    private int calculateWithCustomSeparator(String calculationInput) {
        String[] inputs = calculationInput.split(DELIMITERS_FROM_NUMBERS_SEPARATOR, SPLIT_LIMIT);
        String possibleDelimiters = inputs[1];
        String numbersSeparatedWithDelimiters = inputs[2];
        String delimiter = DelimiterParser.parseDelimiter(possibleDelimiters);
        return performCalculations(numbersSeparatedWithDelimiters, delimiter);
    }

    private Integer performCalculations(final String numbers, final String separator) {
        String negativeNumbers = getNegativeNumbers(numbers, separator);
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " + negativeNumbers);
        } else {
            return calculateSumOfNumbers(numbers, separator);
        }
    }

    private String getNegativeNumbers(final String numbers, final String separator) {
        return getParsedNumbers(numbers, separator).filter(x -> x < 0).map(String::valueOf).collect(Collectors.joining(", "));
    }

    private Stream<Integer> getParsedNumbers(final String numbers, final String separator) {
        return Arrays.stream(numbers.split(separator + NEW_LINE)).map(Integer::parseInt);
    }

    private Integer calculateSumOfNumbers(final String numbers, final String separator) {
        return getParsedNumbers(numbers, separator).filter(numbersLessOrEqualThan(LIMIT)).reduce(0, Integer::sum);
    }

    private Predicate<Integer> numbersLessOrEqualThan(final Integer limit) {
        return number -> number <= limit;
    }
}
