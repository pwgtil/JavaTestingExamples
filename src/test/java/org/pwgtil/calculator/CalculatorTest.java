package org.pwgtil.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    @DisplayName("Add 1 and 2, result should be 3")
    void add() {
        Calculator calc = new Calculator();
        int result = calc.add(1, 2);
        assertEquals(3, result);
    }

    @Test
    void subtract() {
        Calculator calculator = new Calculator();
        int result =  calculator.subtract(2, 3);

        assertEquals(-1, result);
    }

    @Test
    void multiply() {
        Calculator calculator = new Calculator();
        int result =  calculator.multiply(2, 3);

        assertEquals(6, result);
    }

    @Test
    void divide() {
        Calculator calc = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> calc.divide(1,0));
    }
}