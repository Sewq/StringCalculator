package com.sewq.stringcalculator.parser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DelimiterParserTest {

    private StringParser delimiterParser;

    @BeforeEach
    public void setup() {
        delimiterParser = new DelimiterParser();
    }

    @Test
    public void implementationClassExists() {
        StringParser delimiterParser = new DelimiterParser();
    }

    @Test
    public void shouldExtendInterface_whenProvidingStringCalculatorImplementation() {
        Assertions.assertThat(delimiterParser).isInstanceOf(StringParser.class);
    }

    @Test
    public void shouldReturnSameDelimiter_ifNoCustomIndicatorSpecified() {
        Assertions.assertThat(delimiterParser.parse("*")).isEqualTo("*");
        Assertions.assertThat(delimiterParser.parse("$$$")).isEqualTo("$$$");
        Assertions.assertThat(delimiterParser.parse("@@")).isNotEqualTo("@@@");
    }

    @Test
    public void shouldReturnTwoDelimiters_whenMultipleDelimitersIndicatorSpecified() {
        Assertions.assertThat(delimiterParser.parse("[@@][%%]")).isEqualTo("\\Q@@\\E|\\Q%%\\E");
    }

    @Test
    public void shouldReturnThreeDelimetersWIthTheSameLength_whenMultipleDelimitersIndicatorSpecified() {
        Assertions.assertThat(delimiterParser.parse("[@@@@][%%][$]")).isEqualTo("\\Q@@@@\\E|\\Q%%\\E|\\Q$\\E");
    }
}
