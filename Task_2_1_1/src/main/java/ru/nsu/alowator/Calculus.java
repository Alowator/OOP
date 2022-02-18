package ru.nsu.alowator;

public class Calculus {

    public static boolean isPrime(Integer x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0)
                return false;
        }
        return true;
    }

}
