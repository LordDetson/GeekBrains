package queue;

import java.util.*;

final class QueueImplSE<T> extends LinkedList<T> implements java.util.Queue<T> {
    public QueueImplSE() {
    }

    public QueueImplSE(Collection<? extends T> c) {
        super(c);
    }
}

public final class QueueImpl<T> implements Queue<T> {
    private Object[] values;
    private int size;
    private int end;
    private int front;
    private int length;

    public QueueImpl(int size) {
        values = new Object[size];
        this.size = values.length;
        end = MIN_SIZE;
        front = MIN_SIZE;
        length = MIN_SIZE;
    }

    @Override
    public void push(T value) {
        values[end++] = value;
        if (end == size)
            end = MIN_SIZE;
        length++;
    }

    @Override
    public T pop() {
        T temp = (T) values[front++];
        if (front == size) {
            front = 0;
        }
        length--;
        return temp;
    }

    @Override
    public T peek() {
        return (T) values[front];
    }

    @Override
    public boolean isEmpty() {
        return length == MIN_SIZE;
    }

    @Override
    public boolean isFull() {
        return length == size;
    }

    @Override
    public int getSize() {
        return size;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", QueueImpl.class.getSimpleName() + "[", "]")
                .add("values=" + Arrays.toString(values))
                .add("size=" + size)
                .add("end=" + end)
                .add("front=" + front)
                .add("length=" + length)
                .toString();
    }

    private static class Test {
        private static Random random = new Random();

        public static void main(String[] args) {
            Queue<Integer> integerQueue = new QueueImpl<>(5);
            Integer temp;
            for (int i = 0; i < 5; i++) {
                while (!integerQueue.isFull()) {
                    temp = random.nextInt(100);
                    integerQueue.push(temp);
                    System.out.println("push " + temp);
                    System.out.println(integerQueue);
                }
                for (Integer integer : integerQueue) {
                    System.out.println("pop " + integer);
                    System.out.println(integerQueue);
                }
                System.out.println();
            }
        }
    }

}
