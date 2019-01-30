import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Java 1. Homework 4.
 *
 * @author Babanin Dmitry
 * @version 14.01.2019
 */
public class TicTacToe {
    private static char[][] map;
    private static final int SIZE_MAP = 5;
    private static final int CHIPS_TO_WIN = 4;

    private static final char CHIP_EMPTY = '*';
    private static final char CHIP_X = 'X';
    private static final char CHIP_O = 'O';

    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    private enum ChainWay {
        NONE, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST
    }

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
        } while (!isSymbCoordinateValid(CHIP_EMPTY, x, y));
        map[y][x] = CHIP_X;
    }

    private static boolean isSymbCoordinateValid(char symb, int x, int y) {
        if (x >= 0 && x < SIZE_MAP && y >= 0 && y < SIZE_MAP)
            return map[y][x] == symb;
        return false;
    }

    private static void aiStep() {
        int x, y;
        do {
            x = rand.nextInt(SIZE_MAP);
            y = rand.nextInt(SIZE_MAP);
        } while (!isSymbCoordinateValid(CHIP_EMPTY, x, y));
        System.out.println("Компьютер походил в точку " + x + " " + y);
        map[y][x] = CHIP_O;
    }

    private static boolean checkWin(char symb, int amountChipsInChainForWin) {
        System.out.println("symb = " + symb);
        int[] symbCoordinate = getSymbCoordinate(symb);
        System.out.println("symbCoordinate = " + Arrays.toString(symbCoordinate));
        int x = symbCoordinate[0];
        int y = symbCoordinate[1];
        int amountChipsInChain = 0;
        ChainWay way = defineChainWayBySymbCoordinate(symb, x, y);
        System.out.println("way = " + way.name());
        for (int i = 0; i < amountChipsInChainForWin - 1; i++) {
            switch (way) {
                case EAST:
                    x++;
                    break;
                case SOUTH_EAST:
                    x++;
                    y++;
                    break;
                case SOUTH:
                    y++;
                    break;
                case SOUTH_WEST:
                    x--;
                    y++;
                    break;
                case NONE:
                    return false;
            }
            if (isSymbCoordinateValid(symb, x, y))
                amountChipsInChain++;
        }
        System.out.println("amountChipsInChain = " + amountChipsInChain);
        return amountChipsInChain == amountChipsInChainForWin - 1;
    }

    private static int[] getSymbCoordinate(char symb) {
        for (int i = 0; i < SIZE_MAP; i++)
            for (int j = 0; j < SIZE_MAP; j++)
                if (map[i][j] == symb)
                    return new int[]{j, i};
        return new int[]{-1, -1};
    }

    private static ChainWay defineChainWayBySymbCoordinate(char symb, int x, int y) {
        if (isSymbCoordinateValid(symb, x + 1, y))
            return ChainWay.EAST;
        if (isSymbCoordinateValid(symb, x + 1, y + 1))
            return ChainWay.SOUTH_EAST;
        if (isSymbCoordinateValid(symb, x, y + 1))
            return ChainWay.SOUTH;
        if (isSymbCoordinateValid(symb, x - 1, y + 1))
            return ChainWay.SOUTH_WEST;
        return ChainWay.NONE;
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
            if (checkWin(CHIP_X, CHIPS_TO_WIN)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiStep();
            printMap();
            if (checkWin(CHIP_O, CHIPS_TO_WIN)) {
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
