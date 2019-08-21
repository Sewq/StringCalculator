package com.sewq.stringcalculator;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.pool.TypePool;
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
    public void shouldPass_whenMethodAddIsCalled() throws Exception {
        stringCalculator.add("");
    }

    @Test
    public void shouldReturnNumber_whenMethodAddIsCalled() throws Exception {
        int calculationResult = stringCalculator.add("");
        Assertions.assertThat(calculationResult).isGreaterThanOrEqualTo(0);
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
    public void shouldReturnFive_whenTwoAndThreePassed() throws Exception {
        Assertions.assertThat(stringCalculator.add("2,3")).isEqualTo(5);
    }

    @Test
    public void shouldReturnSix_whenOneTwoAndThreePassed() throws Exception {
        Assertions.assertThat(stringCalculator.add("1,2,3")).isEqualTo(6);
    }

    @Test
    public void shouldReturnThree_whenOneAndTwoPassedWithNewLineSeparator() throws Exception {
        Assertions.assertThat(stringCalculator.add("1\n2")).isEqualTo(3);
    }

    @Test
    public void shouldReturn6_whenOneAndTwoPassedWithMixedSeparators() throws Exception {
        Assertions.assertThat(stringCalculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    public void shouldSupportVariousDelimeters_whenSpecified() throws Exception {
        Assertions.assertThat(stringCalculator.add("//;\n1;2")).isEqualTo(3);
        Assertions.assertThat(stringCalculator.add("//g\n2g2")).isEqualTo(4);
        Assertions.assertThat(stringCalculator.add("//%\n4%10")).isEqualTo(14);
    }

    @Test
    public void shouldThrowException_whenNegativeNumbersPassed() throws Exception {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> stringCalculator.add("//%\n4%10"));
    }
}
