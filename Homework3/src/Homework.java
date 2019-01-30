import java.util.Random;
import java.util.Scanner;
/**
 * Java 1. Homework 3.
 *
 * @author Babanin Dmitry
 * @version 14.01.2019
 */
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

    private static void gameGuessTheWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String hiddenWord = words[random.nextInt(words.length)];
        String word = "";
        do {
            System.out.println("Введите слово");
            System.out.print(">> ");
            if (scanner.hasNextLine())
                word = scanner.nextLine();
            char[] charsInHiddenWord = hiddenWord.toCharArray();
            char[] charsInWord = word.toCharArray();
            int length = charsInHiddenWord.length < charsInWord.length ? charsInHiddenWord.length : charsInWord.length;
            for (int i = 0; i < 15; i++)
                if (i < length && charsInHiddenWord[i] == charsInWord[i])
                    System.out.print(charsInHiddenWord[i]);
                else
                    System.out.print('*');
            System.out.println();
        } while (!hiddenWord.equals(word));
        System.out.println("Загаданное слово: " + hiddenWord);
    }

    public static void main(String[] args) {
        gameGuessTheNumber();
        //gameGuessTheWord();
    }
}
