package ru.nsu.alowator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void calculator() {
        calculator = new Calculator();
    }

    @Test
    void calculate_value() {
        assertEquals(10.5, calculator.calculate("10.5"));
    }

    @Test
    void calculate_negativeValue() {
        assertEquals(-10.0, calculator.calculate("-10"));
    }

    @Test
    void calculate_add() {
        assertEquals(8, calculator.calculate("+ 5 3"));
    }

}