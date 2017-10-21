package lab2;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class HashSetBasedRepository<T> implements InMemoryRepository<T> {
    private Set<T> repository;

    public HashSetBasedRepository(){
        repository = new HashSet<T>();
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
