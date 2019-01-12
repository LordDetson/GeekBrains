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

    private static double calcExpression(double a, double b, double c, double d) {
        return a * (b + (c / d));
    }

    private static boolean checkSum(double a, double b) {
        double sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static void main(String[] args) {

    }
}
