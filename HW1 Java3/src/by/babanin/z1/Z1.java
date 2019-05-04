package by.babanin.z1;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Z1 {
    private final Random RANDOM = new Random();
    private final int BORDER = 11;
    private int size;
    private int index1;
    private int index2;
    private Integer[] integers;

    public Z1() {
        size = RANDOM.nextInt(BORDER);
        index1 = RANDOM.nextInt(size);
        index2 = RANDOM.nextInt(size);
        IntStream ints = RANDOM.ints(size, -BORDER, BORDER);
        integers = ints.boxed().toArray(Integer[]::new);
    }

    public void printInputData() {
        System.out.println("input=" + Arrays.toString(integers));
        System.out.println("index1=" + index1);
        System.out.println("index2=" + index2);
    }

    public void printOutputData() {
        System.out.println("output=" + Arrays.toString(integers));
    }

    public <T> void swap(T[] massive, int index1, int index2) {
        if (massive != null &&
                massive.length > 1 &&
                index1 >= 0 &&
                index1 < massive.length &&
                index2 >= 0 &&
                index2 < massive.length &&
                index1 != index2 &&
                !massive[index1].equals(massive[index2])) {
            T buffer = massive[index1];
            massive[index1] = massive[index2];
            massive[index2] = buffer;
        }
    }

    private static class Test {
        public static void main(String[] args) {
            Z1 z1 = new Z1();
            z1.printInputData();
            z1.swap(z1.integers, z1.index1, z1.index2);
            z1.printOutputData();
        }
    }
}
