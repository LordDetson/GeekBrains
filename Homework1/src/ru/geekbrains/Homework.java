package ru.geekbrains;

public class Homework {
    private static byte aByte = 27;
    private static short aShort = 120;
    private static int aInt = 180;
    private static long aLong = 45L;
    private static float aFloat = 13.58F;
    private static double aDouble = 94.8426;
    private static char aChar = 'd';
    private static boolean aBoolean = false;

    private static final String NUMBER_STR = "Число";
    private static final String POSITIVE_STR = "положительное";
    private static final String NEGATIVE_STR = "отрицательное";
    private static final String SEPARATOR = " ";

    private static double calcExpression(double a, double b, double c, double d) {
        return a * (b + (c / d));
    }

    private static boolean checkSum(double a, double b) {
        double sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    private static void checkPositiveOrNegativeNumber(int value) {
        System.out.print(NUMBER_STR + SEPARATOR + value + SEPARATOR + (value < 0 ? NEGATIVE_STR : POSITIVE_STR));
    }

    private static boolean checkNegativeNumber(int value) {
        return value < 0;
    }

    private static void printHello(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public static void main(String[] args) {

    }
}
