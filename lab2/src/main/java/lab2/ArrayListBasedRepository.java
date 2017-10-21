package lab2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class ArrayListBasedRepository<T> implements InMemoryRepository<T>{
    private List<T> repository;

    public ArrayListBasedRepository(){
        repository = new ArrayList<T>();
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
