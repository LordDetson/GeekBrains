package example1;

public interface List<T> {
    void add(T value);

    void add(int index, T value);

    T get(int index);

    boolean isEmpty();

    boolean remove();

    boolean remove(int index);

    boolean removeValue(T value);

    boolean contains(T value);

    int find(T value);

    int size();

    NodeIterator<T> iterator();
}
