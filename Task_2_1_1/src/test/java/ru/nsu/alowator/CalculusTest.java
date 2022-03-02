package ru.nsu.alowator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculusTest {

    @Test
    void isPrime_notNatural() {
        assertThrows(IllegalArgumentException.class, () -> Calculus.isPrime(0));
    }

    @Test
    void isPrime_one() {
        assertFalse(Calculus.isPrime(1));
    }

    @Test
    void isPrime_two() {
        assertTrue(Calculus.isPrime(2));
    }

    @Test
    void isPrime_notPrime() {
        assertFalse(Calculus.isPrime(900));
    }

    @Test
    void isPrime_prime() {
        assertTrue(Calculus.isPrime(1048571));
    }

    @Test
    void isPrime_maxInt() {
        assertTrue(Calculus.isPrime(0x7fffffff));
    }

}