package ru.nsu.alowator;

public class Calculus {

    public static boolean isPrime(int x) {
        if (x <= 0)
            throw new IllegalArgumentException("x must be natural");
        else if (x == 1)
            return false;

        for (long i = 2; i * i <= (long) x; i++) {
            if ((long) x % i == 0)
                return false;
        }
        return true;
    }

}
