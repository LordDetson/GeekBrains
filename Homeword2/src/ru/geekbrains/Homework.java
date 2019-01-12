package ru.geekbrains;

import java.util.Arrays;

public class Homework {

    private static void z1() {
        byte[] mass = {1, 0, 1, 0, 1, 1, 1, 0};
        System.out.println("Исходные данные задания 1:\t\t\t" + Arrays.toString(mass));
        for (int i = 0; i < mass.length; i++)
            mass[i] = (byte) (mass[i] == 1 ? 0 : 1);
        System.out.println("Результаты выполнения задания 1:\t" + Arrays.toString(mass));
    }

    private static void z2() {
        byte[] mass = new byte[8];
        System.out.println("Исходные данные задания 2:\t\t\t" + Arrays.toString(mass));
        for (int i = 0, j = 0; i < mass.length; i++, j += 3)
            mass[i] = (byte) j;
        System.out.println("Результаты выполнения задания 2:\t" + Arrays.toString(mass));
    }

    private static void z3() {
        byte[] mass = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Исходные данные задания 3:\t\t\t" + Arrays.toString(mass));
        for (int i = 0; i < mass.length; i++)
            if (mass[i] < 6) mass[i] *= 2;
        System.out.println("Результаты выполнения задания 3:\t" + Arrays.toString(mass));
    }

    private static void z4() {
        byte[][] mass = new byte[5][5];
        System.out.println("Исходные данные задания 4:");
        printMass(mass);

        for (int i = 0, j = 0; i < mass.length; i++, j++)
            mass[i][j] = 1;
        for (int i = 0, j = mass.length - 1; i < mass.length; i++, j--)
            mass[i][j] = 1;

        System.out.println("Результаты выполнения задания 4:");
        printMass(mass);
    }

    private static void printMass(byte[][] mass) {
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[i].length; j++)
                System.out.print(mass[i][j] + "\t");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        z1();
        System.out.println();
        z2();
        System.out.println();
        z3();
        System.out.println();
        z4();
    }
}
