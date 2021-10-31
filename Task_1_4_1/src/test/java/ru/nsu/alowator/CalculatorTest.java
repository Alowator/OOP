package ru.nsu.alowator;

import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculate_value() {
        assertEquals(-10.5, Calculator.calculate("-10.5"));
    }

    @Test
    void calculate_add() {
        assertEquals(0, Calculator.calculate("+ -100 + 40 60"));
    }

    @Test
    void calculate_sub() {
        assertEquals(972, Calculator.calculate("- - - - 1000 7 7 7 7"));
    }

    @Test
    void calculate_mul() {
        assertEquals(-50, Calculator.calculate("* -1 * 5 10"));
    }

    @Test
    void calculate_div() {
        assertEquals(50, Calculator.calculate("/ 100 / 10 5"));
    }

    @Test
    void calculate_divByZero() {
        assertEquals(Double.POSITIVE_INFINITY, Calculator.calculate("/ 100 0"));
    }

    @Test
    void calculate_log() {
        assertEquals(0.4234226524603038, Calculator.calculate("log log log 100"));
    }

    @Test
    void calculate_logNegative() {
        assertEquals(Double.NaN, Calculator.calculate("log log log -123"));
    }

    @Test
    void calculate_pow() {
        assertEquals(1024.0, Calculator.calculate("pow 2 10"));
    }

    @Test
    void calculate_sqrt() {
        assertEquals(228, Calculator.calculate("sqrt 51984"));
    }

    @Test
    void calculate_sqrtMul() {
        assertEquals(228, Calculator.calculate("sqrt * 228 228"));
    }

    @Test
    void calculate_sin() {
        assertEquals(0.8414709848078965, Calculator.calculate("sin 1"));
    }

    @Test
    void calculate_cos() {
        assertEquals(-0.4161468365471424, Calculator.calculate("cos 2"));
    }

    @Test
    void calculate_expression() {
        assertEquals(
                259.7466245647059,
                Calculator.calculate("* log 10 + sqrt 100 + * 26 4 - / 8 + sin 0 cos 1 pow 2 4")
        );
    }

    @Test
    void calculate_expressionScanner() {
        Scanner scanner = new Scanner("* log 10 + sqrt 100 + * 26 4 - / 8 + sin 0 cos 1 pow 2 4");
        assertEquals(
                259.7466245647059,
                Calculator.calculate(scanner)
        );
    }

}