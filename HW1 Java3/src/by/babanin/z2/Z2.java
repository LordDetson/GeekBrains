package by.babanin.z2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Z2 {
    public static void main(String[] args) {
        int border = 11;
        Random random = new Random();
        IntStream ints = random.ints(random.nextInt(border), -border, border);
        Integer[] integers = ints.boxed().toArray(Integer[]::new);
        System.out.println(arrayToList(integers).getClass().getSimpleName());
        System.out.println(Arrays.toString(integers));
    }

    private static <T> List<T> arrayToList(T[] massive) {
        return new ArrayList<>(Arrays.asList(massive));
    }
}
