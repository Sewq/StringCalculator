package com.sewq.stringcalculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    private StringCalculatorImpl stringCalculator;

    @BeforeEach
    public void setup() {
        stringCalculator = new StringCalculatorImpl();
    }

    @Test
    public void implementationClassExists() {
        StringCalculatorImpl stringCalculator = new StringCalculatorImpl();
    }

    @Test
    public void shouldExtendInterface_whenProvidingStringCalculatorImplementation() {
        Assertions.assertThat(stringCalculator).isInstanceOf(StringCalculator.class);
    }

    @Test
    public void shouldReturnZero_whenEmptyStringPassed() throws Exception {
        Assertions.assertThat(stringCalculator.add("")).isEqualTo(0);
    }

    @Test
    public void shouldReturnOne_whenOneIsPassed() throws Exception {
        Assertions.assertThat(stringCalculator.add("1")).isEqualTo(1);
    }

    @Test
    public void shouldReturnFive_whenTwoAndThreePassedWithDefaultDelimiter() throws Exception {
        Assertions.assertThat(stringCalculator.add("2,3")).isEqualTo(5);
    }

    @Test
    public void shouldReturnSix_whenOneTwoAndThreePassedWithDefaultDelimiter() throws Exception {
        Assertions.assertThat(stringCalculator.add("1,2,3")).isEqualTo(6);
    }

    @Test
    public void shouldReturnThree_whenOneAndTwoPassedWithNewLineDelimiter() throws Exception {
        Assertions.assertThat(stringCalculator.add("1\n2")).isEqualTo(3);
    }

    @Test
    public void shouldReturn6_whenOneAndTwoPassedWithMixedDelimiters() throws Exception {
        Assertions.assertThat(stringCalculator.add("1\n2,3\n4")).isEqualTo(10);
    }

    @Test
    public void shouldSupportVariousDelimiters_whenProvided() throws Exception {
        Assertions.assertThat(stringCalculator.add("//;\n1;2")).isEqualTo(3);
        Assertions.assertThat(stringCalculator.add("//g\n2g2")).isEqualTo(4);
        Assertions.assertThat(stringCalculator.add("//%\n4%10\n1")).isEqualTo(15);
    }

    @Test
    public void shouldThrowIllegalArgumentException_whenNegativeNumbersPassed() throws Exception {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> stringCalculator.add("//%\n4%-10"))
                .withMessage("negatives not allowed: -10");
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> stringCalculator.add("//%\n4%-10%-23%3%-76"))
                .withMessage("negatives not allowed: -10, -23, -76");
    }

    @Test
    public void shouldIgnoreNumbersBiggerThan1000_whenIncluded() throws Exception {
        Assertions.assertThat(stringCalculator.add("//[***]\n1***2***3000***1023***1000")).isEqualTo(1003);
    }

    @Test
    public void shouldSupportDelimitersWithAnyLength_whenProvided() throws Exception {
        Assertions.assertThat(stringCalculator.add("//[***]\n1***2***3")).isEqualTo(6);
        Assertions.assertThat(stringCalculator.add("//[%%%%]\n1%%%%2\n3")).isEqualTo(6);
    }

    @Test
    public void shouldSupportDifferentDelimiters_whenProvided() throws Exception {
        Assertions.assertThat(stringCalculator.add("//[***][!!!]\n1***2!!!3")).isEqualTo(6);
    }

    @Test
    public void shouldSupportDifferentDelimitersWithDifferentLengths_whenProvided() throws Exception {
        Assertions.assertThat(stringCalculator.add("//[*][!!!][@@@@@]\n1*2!!!3@@@@@14*1000")).isEqualTo(1020);
    }
}
