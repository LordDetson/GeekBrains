package ru.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class Homework {
    private static void gameGuessTheNumber() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        final int numberOfAttempts = 3;
        int countAttempt = 0;
        int isExit = 1;
        int hiddenNumber;
        int number = -1;
        do {
            hiddenNumber = random.nextInt(10);
            System.out.println("Угадайте число от 0 до 9");
            while (countAttempt < numberOfAttempts) {
                System.out.print(">> ");
                if (scanner.hasNextInt()) number = scanner.nextInt();
                if (number > hiddenNumber)
                    System.out.println("Загаданное число меньше " + number);
                else if (number < hiddenNumber)
                    System.out.println("Загаданное число больше " + number);
                else {
                    System.out.println("Вы угадали! Загаданное число " + hiddenNumber);
                    break;
                }
                countAttempt++;
            }
            if (countAttempt == numberOfAttempts)
                System.out.println("Вы проиграли( Загаданное число " + hiddenNumber);
            countAttempt = 0;
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            System.out.print(">> ");
            if (scanner.hasNextInt())
                isExit = scanner.nextInt();
        } while (isExit == 1);
    }

    public static void main(String[] args) {
        gameGuessTheNumber();
    }
}
