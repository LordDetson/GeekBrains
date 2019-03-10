package example1;

public interface NodeIterator<T> {
    void reset();

    void next();

    T getCurrent();

    boolean atEnd();

    void insertAfter(T value);

    void insertBefore(T value);

    void deleteCurrent();
}
