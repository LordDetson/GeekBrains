package ru.geekbrains;

public class TicTacToe {
    private static char[][] map;
    private static final int SIZE_MAP = 3;
    private static final int CHIPS_TO_WIN = 3;

    private static final char CHIP_EMPTY = '*';
    private static final char CHIP_X = 'X';
    private static final char CHIP_O = 'O';

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

    public static void main(String[] args) {
        initMap();
        printMap();
    }
}
