package org.pwgtil.calculator;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero!");
        }
        return a / b;
    }

    public int maxOf(int a, int b) {
        return Math.max(a, b);
    }

    public boolean isEven(int a) {
        return a % 2 == 0;
    }
}
