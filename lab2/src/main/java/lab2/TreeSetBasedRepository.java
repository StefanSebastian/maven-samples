package lab2;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class TreeSetBasedRepository<T> implements InMemoryRepository<T> {
    private Set<T> repository;

    public TreeSetBasedRepository(){
        repository = new TreeSet<T>();
    }

    @Override
    public void add(T elem) {
        repository.add(elem);
    }

    @Override
    public boolean contains(T elem) {
        return repository.contains(elem);
    }

    @Override
    public void remove(T elem) {
        repository.remove(elem);
    }
}
