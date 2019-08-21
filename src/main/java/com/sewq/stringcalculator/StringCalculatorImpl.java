package com.sewq.stringcalculator;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalculatorImpl implements StringCalculator {

    private static final int SPLIT_LIMIT = 3;
    private static final String DEFAULT_DELIMITER = ",";
    private static final String DELIMITERS_FROM_NUMBERS_SEPARATOR = "//|\n";
    private static final String NEW_LINE = "|\n";
    private static final String MULTIPLE_DELIMITERS_SEPARATOR = "]\\[";
    private static final String JOINING_SEPARATOR = "|";
    private static final String CUSTOM_DELIMITERS_INDICATOR = "//";
    private static final String MULTIPLE_DELIMITERS_INDICATOR = "[";

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

    private boolean noInputProvided(final String stringRepresentedNumbers) {
        return stringRepresentedNumbers.isEmpty();
    }

    private int calculateWithCustomSeparator(final String calculationInput) {
        String[] inputs = calculationInput.split(DELIMITERS_FROM_NUMBERS_SEPARATOR, SPLIT_LIMIT);
        String possibleDelimiters = inputs[1];
        String numbersSeparatedWithDelimiters = inputs[2];
        String delimiter = parseDelimiter(possibleDelimiters);
        return performCalculations(numbersSeparatedWithDelimiters, delimiter);
    }

    private String parseDelimiter(final String possibleDelimiters) {
        String targetDelimiter = possibleDelimiters;
        if (possibleDelimiters.startsWith(MULTIPLE_DELIMITERS_INDICATOR)) {
            String delimitersWithoutSurroundings = possibleDelimiters.substring(1, possibleDelimiters.length() - 1);
            targetDelimiter = Stream.of(delimitersWithoutSurroundings.split(MULTIPLE_DELIMITERS_SEPARATOR)).map(Pattern::quote)
                    .collect(Collectors.joining(
                            JOINING_SEPARATOR));
        }
        return targetDelimiter;
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
        return getParsedNumbers(numbers, separator).filter(numbersLessOrEqualThanThousand()).reduce(0, Integer::sum);
    }

    private Predicate<Integer> numbersLessOrEqualThanThousand() {
        return x -> x <= 1000;
    }

}
