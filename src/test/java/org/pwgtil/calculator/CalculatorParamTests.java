package org.pwgtil.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CalculatorParamTests {

    @ParameterizedTest(name = "{index} => maxOf({0}, {1}) = {2}")
    @CsvSource({"2, 1, 2", "1, 2, 2", "1, 1, 1"})
    void testMax(int first, int second, int expected) {
        Calculator calc = new Calculator();

        assertEquals(expected, calc.maxOf(first, second));
    }

    @ParameterizedTest(name = "{index} => isEven({0}) = true")
    @ValueSource(ints = { 0, 2, 4, 1000 })
    void testIsEven(int arg) {
        assertTrue(new Calculator().isEven(arg));
    }

    @ParameterizedTest
    @EmptySource
    void testEmpty(int[] arg) {
        assertEquals(0, arg.length);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testNullAndEmpty(List<String> arg) {
        assertTrue(arg == null || arg.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("stringFactory")
    void testStrings(String str) {
        assertFalse(str.isEmpty());
    }

    static List<String> stringFactory() {
        return List.of("apple", "banana", "lemon", "orange");
    }

    @ParameterizedTest
    @MethodSource("argFactory")
    void testStringLength(String str, int length) {
        try {
            new File("./src/test/resources/dataset.csv").createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(length, str.length());
    }

    static List<Arguments> argFactory() {
        return List.of(arguments("apple", 5), arguments("watermelon", 10));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataset.csv", numLinesToSkip = 1)
    void testStringLengthFromCSV(String str, int length) {
        assertEquals(length, str.length());
    }
}
