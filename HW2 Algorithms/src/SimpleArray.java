import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;

public final class SimpleArray<T extends Comparable<T>> implements Cloneable {
    public static final int MAX_SIZE = Integer.MAX_VALUE;
    public static final int MIN_SIZE = 0;
    private Object[] values;
    private int size;
    private int length;

    public SimpleArray(int size) {
        if (size >= MIN_SIZE) {
            this.size = size;
        } else {
            this.size = MIN_SIZE;
        }
        this.length = 0;
        this.values = new Object[size];
    }

    public SimpleArray(T... values) {
        this.values = values;
        size = values.length;
        length = size;
    }

    public boolean insert(T value) {
        if (length < size && !isNull(value)) {
            values[length++] = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, T value) {
        if (!isNull(value) && index < size && index >= MIN_SIZE) {
            values[index] = value;
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public int binarySearch(T value) {
        selectionSort();
        int bottom = 0;
        int top = length;
        int middle;
        while (!isNull(value) && bottom < top) {
            middle = (top + bottom) / 2;
            if (value.compareTo((T) values[middle]) == 0) {
                return middle;
            } else if (value.compareTo((T) values[middle]) < 0) {
                top = middle;
            } else {
                bottom = middle + 1;
            }
        }
        return -1;
    }

    public int search(T value) {
        for (int i = 0; i < length; i++) {
            if (values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public void selectionSort() {
        int index;
        for (int i = 0; i < length - 1; i++) {
            index = i;
            for (int j = i + 1; j < length; j++)
                if (((T) values[index]).compareTo((T) values[j]) > 0)
                    index = j;
            swap(i, index);
        }
    }

    @SuppressWarnings("unchecked")
    public void bubbleSort() {
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length - i; j++) {
                if (((T) values[j - 1]).compareTo((T) values[j]) > 0) {
                    swap(j - 1, j);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void insertSort() {
        int i, j;
        for (i = 1; i < length; i++) {
            Object buf = values[i];
            for (j = i - 1; j >= 0 && ((T) values[j]).compareTo((T) buf) > 0; j--) {
                values[j + 1] = values[j];
            }
            values[j + 1] = buf;
        }
    }

    public void swap(int index1, int index2) {
        Object buf = values[index1];
        values[index1] = values[index2];
        values[index2] = buf;
    }

    public boolean delete(int index) {
        if (index >= MIN_SIZE && index < length) {
            for (int i = index; i < length - 1; ) {
                values[i] = values[++i];
            }
            length--;
        }
        return false;
    }

    public boolean deleteByValue(T value) {
        return delete(binarySearch(value));
    }

    public int getSize() {
        return size;
    }

    public int getLength() {
        return length;
    }

    @SuppressWarnings("unchecked")
    public T getValue(int index) {
        if (index >= MIN_SIZE) {
            return (T) values[index];
        }
        return null;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < length; i++) joiner.add(values[i].toString());
        return joiner.toString();
    }

    private boolean isNull(Object value) {
        return value == null;
    }

    private static class Test {
        enum SortedType {
            SELECTION, BUBBLE, INSERTION
        }

        private static final Random random = new Random();
        private static Integer[] ints;
        private static SimpleArray<Integer> integers;
        private static long startTime;
        private static long finishTime;

        public static void main(String[] args) {
            for (int i = 1; i <= 5; i++) {
                ints = toGenerateIntegers((int) Math.pow(10, i), -100, 101);
                System.out.println(Arrays.toString(ints));
                for (SortedType type : SortedType.values()) {
                    integers = new SimpleArray<>(ints);
                    startTime = System.nanoTime();
                    switch (type) {
                        case BUBBLE:
                            integers.bubbleSort();
                            break;
                        case INSERTION:
                            integers.insertSort();
                            break;
                        case SELECTION:
                            integers.selectionSort();
                            break;
                    }
                    finishTime = System.nanoTime();
                    System.out.println("sorted " + type + "\n[" + (finishTime - startTime) + "] length: " + integers.getLength());
                }
            }
        }

        private static @NotNull
        Integer[] toGenerateIntegers(int size, int bottomLine, int topLine) {
            Integer[] ints = new Integer[size];
            if (bottomLine < topLine) {
                for (int i = 0; i < ints.length; i++) {
                    ints[i] = randInt(bottomLine, topLine);
                }
            }
            return ints;
        }

        private static @NotNull
        Integer[] toGenerateIntegers(int size, int topLine) {
            return toGenerateIntegers(size, 0, topLine);
        }

        private static int randInt(int bottomLine, int topLine) {
            return (int) (bottomLine + random.nextDouble() * (topLine - bottomLine));
        }
    }
}
