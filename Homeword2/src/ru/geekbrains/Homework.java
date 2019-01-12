package ru.geekbrains;

import java.util.Arrays;

public class Homework {

    private static void z1() {
        byte[] mass = {1, 0, 1, 0, 1, 1, 1, 0};
        System.out.println("Исходные данные задания 1:\t" + Arrays.toString(mass));
        for (int i = 0; i < mass.length; i++) mass[i] = (byte) (mass[i] == 1 ? 0 : 1);
        System.out.println("Результаты выполнения задания 1:\t" + Arrays.toString(mass));
    }

    public static void main(String[] args) {

    }
}
