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
    public void shouldPass_whenMethodAddIsCalled() {
        stringCalculator.add("");
    }

    @Test
    public void shouldReturnNumber_whenMethodAddIsCalled() {
        int calculationResult = stringCalculator.add("");
        Assertions.assertThat(calculationResult).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void shouldReturnZero_whenEmptyStringPassed() {
        Assertions.assertThat(stringCalculator.add("")).isEqualTo(0);
    }

    @Test
    public void shouldReturnOne_whenOneIsPassed() {
        Assertions.assertThat(stringCalculator.add("1")).isEqualTo(1);
    }

    @Test
    public void shouldReturnFive_whenTwoAndThreePassed() {
        Assertions.assertThat(stringCalculator.add("2,3")).isEqualTo(5);
    }

    @Test
    public void shouldReturnSix_whenOneTwoAndThreePassed() {
        Assertions.assertThat(stringCalculator.add("1,2,3")).isEqualTo(6);
    }

    @Test
    public void shouldReturnThree_whenOneAndTwoPassedWithNewLineSeparator() {
        Assertions.assertThat(stringCalculator.add("1\n2")).isEqualTo(3);
    }

    @Test
    public void ShouldReturnThree_whenOneAndTwoPassedWithMixedSeparators() {
        Assertions.assertThat(stringCalculator.add("1\n2")).isEqualTo(3);

    }
}
