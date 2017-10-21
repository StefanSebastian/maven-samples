package lab2;

/**
 * Created by Sebi on 21-Oct-17.
 */
public interface InMemoryRepository<T> {
    void add(T elem);
    boolean contains(T elem);
    void remove(T elem);
}
