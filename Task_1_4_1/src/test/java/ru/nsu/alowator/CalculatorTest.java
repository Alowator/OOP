package ru.nsu.alowator;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculate_value() throws ParseException {
        assertEquals(-10.5, Calculator.calculate("-10.5"));
    }

    @Test
    void calculate_add() throws ParseException {
        assertEquals(0, Calculator.calculate("+ -100 + 40 60"));
    }

    @Test
    void calculate_sub() throws ParseException {
        assertEquals(972, Calculator.calculate("- - - - 1000 7 7 7 7"));
    }

    @Test
    void calculate_mul() throws ParseException {
        assertEquals(-50, Calculator.calculate("* -1 * 5 10"));
    }

    @Test
    void calculate_div() throws ParseException {
        assertEquals(50, Calculator.calculate("/ 100 / 10 5"));
    }

    @Test
    void calculate_divByZero() throws ParseException {
        assertEquals(Double.POSITIVE_INFINITY, Calculator.calculate("/ 100 0"));
    }

    @Test
    void calculate_log() throws ParseException {
        assertEquals(0.42, Calculator.calculate("log log log 100"), 0.01);
    }

    @Test
    void calculate_logNegative() throws ParseException {
        assertEquals(Double.NaN, Calculator.calculate("log log log -123"));
    }

    @Test
    void calculate_pow() throws ParseException {
        assertEquals(1024.0, Calculator.calculate("pow 2 10"));
    }

    @Test
    void calculate_sqrt() throws ParseException {
        assertEquals(228, Calculator.calculate("sqrt 51984"));
    }

    @Test
    void calculate_sqrtMul() throws ParseException {
        assertEquals(228, Calculator.calculate("sqrt * 228 228"));
    }

    @Test
    void calculate_sin() throws ParseException {
        assertEquals(0.84, Calculator.calculate("sin 1"), 0.01);
    }

    @Test
    void calculate_cos() throws ParseException {
        assertEquals(-0.41, Calculator.calculate("cos 2"), 0.01);
    }

    @Test
    void calculate_expression() throws ParseException {
        assertEquals(
                259.74,
                Calculator.calculate("* log 10 + sqrt 100 + * 26 4 - / 8 + sin 0 cos 1 pow 2 4"),
                0.01
        );
    }

    @Test
    void calculate_expressionScanner() throws ParseException {
        Scanner scanner = new Scanner("* log 10 + sqrt 100 + * 26 4 - / 8 + sin 0 cos 1 pow 2 4");
        assertEquals(
                259.74,
                Calculator.calculate(scanner),
                0.01
        );
    }

    @Test
    void calculate_exceptionNotEnoughArgs() {
        assertThrows(ParseException.class, () -> Calculator.calculate("* 1 * 4"));
    }

    @Test
    void calculate_exceptionEmptyInput() {
        assertThrows(ParseException.class, () -> Calculator.calculate(""));
    }

    @Test
    void calculate_exceptionUnknownOperation() {
        assertThrows(ParseException.class, () -> Calculator.calculate("tg 10"));
    }

}