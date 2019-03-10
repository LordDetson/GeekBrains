package example1;

public interface Deque<T> {
    void addFirst(T value);

    void addLast(T value);

    T getFirst();

    T getLast();

    T removeFirst();

    T removeLast();
}
