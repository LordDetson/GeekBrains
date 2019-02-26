package Stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.StringJoiner;

public class StackImpl<T> implements Stack<T>, Iterable<T> {
    private Object[] values;
    private int size;
    private int itemTop = -1;

    public StackImpl(int size) {
        values = new Object[size];
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
                return isEmpty();
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
            while (!integerStack.isEmpty()) {
                System.out.print(integerStack.pop() + " ");
            }
        }
    }
}
