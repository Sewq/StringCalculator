package com.sewq.stringcalculator.parser;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DelimiterParser implements StringParser {

    private static final String MULTIPLE_DELIMITERS_SEPARATOR = "]\\[";
    private static final String JOINING_SEPARATOR = "|";
    private static final String MULTIPLE_DELIMITERS_INDICATOR = "[";

    @Override
    public String parse(String possibleDelimiters) {
        String targetDelimiter = possibleDelimiters;
        if (possibleDelimiters.startsWith(MULTIPLE_DELIMITERS_INDICATOR)) {
            String delimitersWithoutSurroundings = extractDelimitersWithoutSquareBrackets(possibleDelimiters);
            targetDelimiter = Stream.of(delimitersWithoutSurroundings.split(MULTIPLE_DELIMITERS_SEPARATOR)).map(Pattern::quote)
                    .collect(Collectors.joining(
                            JOINING_SEPARATOR));
        }
        return targetDelimiter;
    }

    private String extractDelimitersWithoutSquareBrackets(String possibleDelimiters) {
        return possibleDelimiters.substring(1, possibleDelimiters.length() - 1);
    }
}
