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
        System.out.println(NUMBER_STR + SEPARATOR + value + SEPARATOR + (value < 0 ? NEGATIVE_STR : POSITIVE_STR));
    }

    private static boolean checkNegativeNumber(int value) {
        return value < 0;
    }

    private static void printHello(String name) {
        System.out.println("Hello, " + name + "!");
    }

    private static boolean checkLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public static void main(String[] args) {
        int a = 10, b = 20, c = 30, d = 40;
        String name = "Dima";
        int year = 2019;

        System.out.println("Исходные данные задания 3: a = " + a + ", b = " + b + ", c = " + c + ", d = " + d);
        System.out.println("Результат выполнения заданий 3: " + calcExpression(a, b, c, d));
        System.out.println();

        System.out.println("Исходные данные задания 4: a = " + a + ", b = " + b);
        System.out.println("Результат выполнения заданий 4: " + checkSum(a, b));
        System.out.println();

        System.out.println("Исходные данные задания 5: a = " + a);
        System.out.print("Результат выполнения заданий 5: ");
        checkPositiveOrNegativeNumber(a);
        System.out.println();

        a = -8;
        System.out.println("Исходные данные задания 6: a = " + a);
        System.out.println("Результат выполнения заданий 6: " + checkNegativeNumber(a));
        System.out.println();

        System.out.println("Исходные данные задания 7: name = " + name);
        System.out.print("Результат выполнения заданий 7: ");
        printHello(name);
        System.out.println();

        System.out.println("Исходные данные задания 8: year = " + year);
        System.out.println("Результат выполнения заданий 8: " + year + " " + (checkLeapYear(year)?"високосный":"невисокосный") + " год");
    }
}
