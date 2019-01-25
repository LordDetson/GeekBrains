package ai;

import java.util.Random;
import java.util.Scanner;

enum ChipType {

    CHIP_EMPTY('*'),
    CHIP_X('X'),
    CHIP_O('O');

    private char value;

    ChipType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}

enum GamerType {
    PERSON(ChipType.CHIP_X),
    AI(ChipType.CHIP_O);

    private ChipType chip;

    GamerType(ChipType chip) {
        this.chip = chip;
    }

    public ChipType getChip() {
        return chip;
    }
}

public class TicTacToeWithAI {

    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();

    private final int SIZE_MAP = 3;
    private final int CHIPS_WIN = 3;
    private ChipType[][] map = new ChipType[SIZE_MAP][SIZE_MAP];
    private int[][] winPositions = {
            {0, 0}, {0, 2}, {2, 0}, {2, 2}, {1, 1}
    };

    private boolean victory = false;            //Определяет, закончена ли игра
    private GamerType victoryGamer;             //игрока-победителя
    private boolean gameOver = false;           //Никто не выиграл, поле заполнено

    public TicTacToeWithAI() {
        initMap();
    }

    private void showMap() {
        System.out.print("\t");
        for (int i = 0; i < SIZE_MAP; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < SIZE_MAP; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < SIZE_MAP; j++) {
                System.out.print(map[i][j].getValue() + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void initMap() {
        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                map[i][j] = ChipType.CHIP_EMPTY;
            }
        }
    }

    private void personStep() {
        int x = -1, y = -1;
        do {
            System.out.println("Введите координату X");
            System.out.print(">> ");
            if (sc.hasNextInt()) {
                x = sc.nextInt();
            }
            System.out.println("Введите координату Y");
            System.out.print(">> ");
            if (sc.hasNextInt()) {
                y = sc.nextInt();
            }
        } while (!isSymbCoordinateValid(ChipType.CHIP_EMPTY, x, y));
        map[y][x] = GamerType.PERSON.getChip();
    }

    private boolean isSymbCoordinateValid(ChipType chipType, int x, int y) {
        if (x >= 0 && x < SIZE_MAP && y >= 0 && y < SIZE_MAP) {
            return map[y][x].equals(chipType);
        }
        return false;
    }

    private void aiStep() {
        if (!checkDanger()) {
            return;
        }
        boolean isCycle = true;
        while (isCycle) {
            if (checkGameOver()) break;
            if (!checkWinPosition()) {
                for (int i = 0; i < SIZE_MAP; i++) {
                    for (int j = 0; j < SIZE_MAP; j++) {
                        if (map[i][j].equals(ChipType.CHIP_EMPTY)) {
                            map[i][j] = GamerType.AI.getChip();
                            return;
                        }
                    }
                }
            }
            if (checkCornPos()) {
                return;
            }
            int indexWin = random.nextInt(5);
            if (map[winPositions[indexWin][0]][winPositions[indexWin][1]].equals(ChipType.CHIP_EMPTY)) {
                map[winPositions[indexWin][0]][winPositions[indexWin][1]] = GamerType.AI.getChip();
                isCycle = false;
            }
        }
    }

    private void step(GamerType gamerType) {
        if (victory) {
            victoryGamer = gamerType;
            return;
        }
        switch (gamerType) {
            case PERSON:
                personStep();
                break;
            case AI:
                aiStep();
                break;
        }
    }

    private boolean checkDanger() {
        ChipType insiderChip = GamerType.AI.getChip();
        ChipType strangerChip = GamerType.PERSON.getChip();
        boolean[] insider = new boolean[CHIPS_WIN];       //свой
        boolean[] stranger = new boolean[CHIPS_WIN];      //чужой
        int lineSequenceNumberCounter = 0;

        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                if (map[i][j].equals(insiderChip)) insider[lineSequenceNumberCounter] = true;
                if (map[i][j].equals(strangerChip)) stranger[lineSequenceNumberCounter] = true;
                lineSequenceNumberCounter++;
            }

            if (checkVictory(insider) || checkVictory(stranger)) {
                return true;
            }

            if ((evalSum(insider) == 2) && (evalSum(stranger) == 0)) {
                int empty = findEmpty(insider);
                map[i][empty] = insiderChip;
                return false;
            }

            if ((evalSum(insider) == 0) && (evalSum(stranger) == 2)) {
                int empty = findEmpty(stranger);
                map[i][empty] = insiderChip;
                return false;
            }

            lineSequenceNumberCounter = 0;
            insider = new boolean[CHIPS_WIN];
            stranger = new boolean[CHIPS_WIN];
        }

        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                if (map[j][i].equals(insiderChip)) insider[lineSequenceNumberCounter] = true;
                if (map[j][i].equals(strangerChip)) stranger[lineSequenceNumberCounter] = true;
                lineSequenceNumberCounter++;
            }

            if (checkVictory(insider) || checkVictory(stranger)) {
                return true;
            }

            if ((evalSum(insider) == 2) && (evalSum(stranger) == 0)) {
                int empty = findEmpty(insider);
                map[empty][i] = insiderChip;
                return false;
            }

            if ((evalSum(insider) == 0) && (evalSum(stranger) == 2)) {
                int empty = findEmpty(stranger);
                map[empty][i] = insiderChip;
                return false;
            }

            lineSequenceNumberCounter = 0;
            insider = new boolean[CHIPS_WIN];
            stranger = new boolean[CHIPS_WIN];
        }

        for (int j = 0; j < SIZE_MAP; j++) {
            if (map[j][j].equals(insiderChip)) insider[lineSequenceNumberCounter] = true;
            if (map[j][j].equals(strangerChip)) stranger[lineSequenceNumberCounter] = true;
            lineSequenceNumberCounter++;
        }

        if (checkVictory(insider) || checkVictory(stranger)) {
            return true;
        }

        if ((evalSum(insider) == 2) && (evalSum(stranger) == 0)) {
            int empty = findEmpty(insider);
            map[empty][empty] = insiderChip;
            return false;
        }

        if ((evalSum(insider) == 0) && (evalSum(stranger) == 2)) {
            int empty = findEmpty(stranger);
            map[empty][empty] = insiderChip;
            return false;
        }

        lineSequenceNumberCounter = 0;
        insider = new boolean[CHIPS_WIN];
        stranger = new boolean[CHIPS_WIN];

        for (int j = 0; j < SIZE_MAP; j++) {
            if (map[j][SIZE_MAP - j - 1].equals(insiderChip)) insider[lineSequenceNumberCounter] = true;
            if (map[j][SIZE_MAP - j - 1].equals(strangerChip)) stranger[lineSequenceNumberCounter] = true;
            lineSequenceNumberCounter++;
        }

        if (checkVictory(insider) || checkVictory(stranger)) {
            return true;
        }

        if ((evalSum(insider) == 2) && (evalSum(stranger) == 0)) {
            int empty = findEmpty(insider);
            map[empty][SIZE_MAP - empty - 1] = insiderChip;
            return false;
        }

        if ((evalSum(insider) == 0) && (evalSum(stranger) == 2)) {
            int empty = findEmpty(stranger);
            map[empty][SIZE_MAP - empty - 1] = insiderChip;
            return false;
        }

        return true;
    }

    private boolean checkVictory(boolean[] arg) {
        int ret = 0;
        for (boolean b : arg) {
            if (b) {
                ret++;
            }
        }
        if (ret == CHIPS_WIN) {
            victory = true;
            return true;
        }
        return false;
    }

    private int evalSum(boolean[] arg) {
        int ret = 0;
        for (boolean b : arg) {
            if (b) {
                ret++;
            }
        }
        return ret;
    }

    private int findEmpty(boolean[] arg) {
        for (int i = 0; i < arg.length; i++) {
            if (!arg[i]) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkGameOver() {
        int cnt = 0;
        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                if (map[i][j].equals(ChipType.CHIP_EMPTY)) {
                    cnt++;
                }
            }
        }
        if (cnt == (int) Math.pow(SIZE_MAP, 2)) {
            gameOver = true;
            return true;
        }
        return false;
    }

    private boolean checkWinPosition() {
        int cnt = 0;
        for (int[] winPosition : winPositions) {
            if (!map[winPosition[0]][winPosition[1]]
                    .equals(ChipType.CHIP_EMPTY)) {
                cnt++;
            }
        }
        return cnt != winPositions.length;
    }

    private boolean checkCornPos() {
        ChipType aiChip = GamerType.AI.getChip();
        ChipType emptyChip = ChipType.CHIP_EMPTY;
        boolean centralCellBusyFlag = map[1][1].equals(emptyChip);

        if ((map[0][0].equals(aiChip)) && (map[2][0].equals(aiChip)) && (map[1][0].equals(emptyChip))) {
            if (map[0][2].equals(emptyChip)) {
                map[0][2] = aiChip;
                return true;
            }
            if (map[2][2].equals(emptyChip)) {
                map[2][2] = aiChip;
                return true;
            }
            if (centralCellBusyFlag) {
                map[1][1] = aiChip;
                return true;
            }
        }

        if ((map[0][2].equals(aiChip)) && (map[2][2].equals(aiChip)) && (map[1][2].equals(emptyChip))) {
            if (map[0][0].equals(emptyChip)) {
                map[0][0] = aiChip;
                return true;
            }
            if (map[2][0].equals(emptyChip)) {
                map[2][0] = aiChip;
                return true;
            }
            if (centralCellBusyFlag) {
                map[1][1] = aiChip;
                return true;
            }
        }

        if ((map[0][0].equals(aiChip)) && (map[0][2].equals(aiChip)) && (map[0][1].equals(emptyChip))) {
            if (map[2][0].equals(emptyChip)) {
                map[2][0] = aiChip;
                return true;
            }
            if (map[2][2].equals(emptyChip)) {
                map[2][2] = aiChip;
                return true;
            }
            if (centralCellBusyFlag) {
                map[1][1] = aiChip;
                return true;
            }
        }

        if ((map[2][0].equals(aiChip)) && (map[2][2].equals(aiChip)) && (map[2][1].equals(emptyChip))) {
            if (map[0][0].equals(emptyChip)) {
                map[0][0] = aiChip;
                return true;
            }
            if (map[2][0].equals(emptyChip)) {
                map[2][0] = aiChip;
                return true;
            }
            if (centralCellBusyFlag) {
                map[1][1] = aiChip;
                return true;
            }
        }
        return false;
    }

    public void game() {
        showMap();
        while (!victory && !gameOver) {
            System.out.println("victory = " + victory);
            System.out.println("gameOver = " + gameOver);
            if (gameOver) {
                break;
            }
            for (GamerType gamer : GamerType.values()) {
                step(gamer);
                showMap();
            }
        }
        if (gameOver) {
            System.out.println("Game over!");
        } else if (victory) {
            System.out.println("Player " + victoryGamer.name() + " has win!");
        }
    }

    public static void main(String[] args) {
        TicTacToeWithAI ticTacToe = new TicTacToeWithAI();
        ticTacToe.game();
    }
}
