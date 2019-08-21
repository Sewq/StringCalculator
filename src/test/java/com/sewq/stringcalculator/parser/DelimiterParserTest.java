package com.sewq.stringcalculator.parser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DelimiterParserTest {

    @Test
    public void shouldReturnSameDelimeter_ifNoCustomIndicatorSpecified() {
        Assertions.assertThat(DelimiterParser.parseDelimiter("*")).isEqualTo("*");
        Assertions.assertThat(DelimiterParser.parseDelimiter("$$$")).isEqualTo("$$$");
        Assertions.assertThat(DelimiterParser.parseDelimiter("@@")).isNotEqualTo("@@@");
    }

    @Test
    public void shouldReturnTwoDelimiters_whenMultipleDelimitersIndicatorSpecified() {
        Assertions.assertThat(DelimiterParser.parseDelimiter("[@@][%%]")).isEqualTo("\\Q@@\\E|\\Q%%\\E");
    }

    @Test
    public void shouldReturnThreeDelimetersWIthTheSameLength_whenMultipleDelimitersIndicatorSpecified() {
        Assertions.assertThat(DelimiterParser.parseDelimiter("[@@@@][%%][$]")).isEqualTo("\\Q@@@@\\E|\\Q%%\\E|\\Q$\\E");
    }
}
