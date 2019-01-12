package ru.geekbrains;

import java.util.Arrays;
import java.util.Random;

public class Homework {

    private static Random random = new Random();

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

    private static void z5() {
        int[] mass = new int[20];
        Arrays.parallelSetAll(mass, operand -> random.nextInt(100));
        System.out.println("Исходные данные задания 5:\t\t\t" + Arrays.toString(mass));
        System.out.println("Результаты выполнения задания 5:\t" + Arrays.stream(mass).summaryStatistics());
    }

    private static void z6() {
        //int[] mass = {1, 1, 1, 2, 1};
        int[] mass = new int[20];
        Arrays.parallelSetAll(mass, operand -> random.nextInt(100));
        System.out.println("Исходные данные задания 6:\t\t\t" + Arrays.toString(mass));
        System.out.println("Результаты выполнения задания 6:\t" + checkBalance(mass));
    }

    private static boolean checkBalance(int[] mass) {
        for (int i = 1; i < mass.length - 1; i++) {
            int sumLeft = 0, sumRight = 0;
            for (int j = 0; j < i; j++)
                sumLeft += mass[j];
            for (int j = i; j < mass.length; j++)
                sumRight += mass[j];
            if (sumLeft == sumRight)
                return true;
        }
        return false;
    }

    private static void z7() {
        int[] mass = new int[20];
        int n = random.nextInt(40) - 20;
        Arrays.parallelSetAll(mass, operand -> random.nextInt(100));
        System.out.println("Исходные данные задания 7:\t\t\t" + Arrays.toString(mass) + " n = " + n);
        System.out.println("Результаты выполнения задания 7:\t" + Arrays.toString(doSlip(mass, n)));
    }

    private static int[] doSlip(int[] mass, int n) {
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                int buf = mass[mass.length - 1];
                for (int j = mass.length - 1; j > 0; j--)
                    mass[j] = mass[j - 1];
                mass[0] = buf;
            }
        } else if (n < 0) {
            n *= -1;
            for (int i = 0; i < n; i++) {
                int buf = mass[0];
                for (int j = 0; j < mass.length - 1; j++)
                    mass[j] = mass[j + 1];
                mass[mass.length - 1] = buf;
            }
        }
        return mass;
    }

    public static void main(String[] args) {
        z1();
        System.out.println();
        z2();
        System.out.println();
        z3();
        System.out.println();
        z4();
        System.out.println();
        z5();
        System.out.println();
        z6();
        System.out.println();
        z7();
    }
}
