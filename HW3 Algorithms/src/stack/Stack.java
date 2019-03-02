package stack;

public interface Stack<T> extends Iterable<T> {
    int MAX_SIZE = Integer.MAX_VALUE;
    int MIN_SIZE = 0;

    void push(T value);

    T pop();

    T peek();

    boolean isEmpty();

    boolean isFull();

    int getSize();
}
