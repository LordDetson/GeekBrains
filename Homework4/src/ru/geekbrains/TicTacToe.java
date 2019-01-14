package ru.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static char[][] map;
    private static final int SIZE_MAP = 3;
    private static final int CHIPS_TO_WIN = 3;

    private static final char CHIP_EMPTY = '*';
    private static final char CHIP_X = 'X';
    private static final char CHIP_O = 'O';

    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    private static void initMap() {
        map = new char[SIZE_MAP][SIZE_MAP];
        for (int i = 0; i < SIZE_MAP; i++)
            for (int j = 0; j < SIZE_MAP; j++)
                map[i][j] = CHIP_EMPTY;
    }

    private static void printMap() {
        System.out.print("\t");
        for (int i = 0; i < SIZE_MAP; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < SIZE_MAP; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < SIZE_MAP; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void humanStep() {
        int x = -1, y = -1;
        do {
            System.out.println("Введите координату X");
            System.out.print(">> ");
            if (sc.hasNextInt()) x = sc.nextInt();
            System.out.println("Введите координату Y");
            System.out.print(">> ");
            if (sc.hasNextInt()) y = sc.nextInt();
        } while (!isStepValid(x, y));
        map[y][x] = CHIP_X;
    }

    private static boolean isStepValid(int x, int y) {
        if (x >= 0 && x < SIZE_MAP && y >= 0 && y < SIZE_MAP)
            return map[y][x] == CHIP_EMPTY;
        return false;
    }

    private static void iiStep() {
        int x, y;
        do {
            x = rand.nextInt(SIZE_MAP);
            y = rand.nextInt(SIZE_MAP);
        } while (!isStepValid(x, y));
        System.out.println("Компьютер походил в точку " + x + " " + y);
        map[y][x] = CHIP_O;
    }

    private static boolean checkWin(char symb) {
        if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
        return false;
    }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                if (map[i][j] == CHIP_EMPTY) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanStep();
            printMap();
            if (checkWin(CHIP_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            iiStep();
            printMap();
            if (checkWin(CHIP_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }
}
