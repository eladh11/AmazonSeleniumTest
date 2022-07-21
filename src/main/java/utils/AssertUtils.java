package utils;

import org.testng.Assert;

public class AssertUtils {
    public static void assertTrue(boolean condition, String message) {
        if (condition) {
            System.out.println("Passed - " + message);
            Assert.assertTrue(condition, message);
        } else {
            System.out.println("Error - " + message);
            Assert.assertTrue(condition, message);
        }
    }

    public static void assertEquals(String expected, String actual, String message) {
        if (expected.equals(actual)) {
            System.out.println("Passed - " + message);
            Assert.assertEquals(expected, actual, message);
        } else {
            System.out.println("Error - " + message);
            Assert.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(int expected, int actual, String message) {
        if (expected == actual) {
            System.out.println("Passed - " + message);
            Assert.assertEquals(expected, actual, message);
        } else {
            System.out.println("Error - " + message);
            Assert.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(double expected, double actual, String message) {
        if (expected == actual) {
            System.out.println("Passed - " + message);
            Assert.assertEquals(expected, actual, message);
        } else {
            System.out.println("Error - " + message);
            Assert.assertEquals(expected, actual, message);
        }
    }
}
