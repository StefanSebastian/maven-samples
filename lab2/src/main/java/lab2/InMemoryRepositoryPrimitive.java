package lab2;

/**
 * Created by Sebi on 31-Oct-17.
 */
public interface InMemoryRepositoryPrimitive {
    void add(long e);

    boolean contains(long e);

    void remove(long e);

    void clear();

    int size();
}
