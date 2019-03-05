package example1;

public interface List<T> {
    void add(T value);

    void add(int index, T value);

    T get(int index);

    boolean isEmpty();

    boolean remove(int index);

    boolean remove(T value);
}
