package example1;

public interface Queue<T> {
    void add(T value);

    T peek();

    T pop();
}
