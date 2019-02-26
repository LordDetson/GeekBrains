package stack;

import java.util.*;

public final class StackImpl<T> implements Stack<T> {
    private Object[] values;
    private int size;
    private int itemTop = -1;

    public StackImpl(int size) {
        if (size >= MIN_SIZE)
            values = new Object[size];
        else
            values = new Object[MIN_SIZE];
        this.size = values.length;
    }

    public StackImpl(T... values) {
        this.values = values;
        size = values.length;
        itemTop = size;
    }

    @Override
    public void push(T value) {
        values[++itemTop] = value;
    }

    @Override
    public T pop() {
        return (T) values[itemTop--];
    }

    @Override
    public T peek() {
        return (T) values[itemTop];
    }

    @Override
    public boolean isEmpty() {
        return itemTop == -1;
    }

    @Override
    public boolean isFull() {
        return itemTop == size - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getItemTop() {
        return itemTop;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StackImpl.class.getSimpleName() + "[", "]")
                .add("values=" + Arrays.toString(values))
                .add("size=" + size)
                .add("itemTop=" + peek())
                .toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !isEmpty();
            }

            @Override
            public T next() {
                return pop();
            }
        };
    }

    private static class Test {
        private static Random random = new Random();
        public static void main(String[] args) {
            Stack<Integer> integerStack = new StackImpl<Integer>( 5);
            while (!integerStack.isFull()) {
                Integer temp = random.nextInt(100);
                System.out.print(temp + " ");
                integerStack.push(temp);
            }
            System.out.println();
            System.out.println(integerStack);
            for (Integer integer : integerStack) {
                System.out.print(integer + " ");
            }
        }
    }
}
