package lab2.primitiveRepos;

import gnu.trove.list.array.TLongArrayList;
import lab2.InMemoryRepositoryPrimitive;

/**
 * Created by Sebi on 31-Oct-17.
 */
public class TroveLongArrayListRepo implements InMemoryRepositoryPrimitive {
    TLongArrayList repository;

    public TroveLongArrayListRepo(){
        repository = new TLongArrayList();
    }

    @Override
    public void add(long e) {
        repository.add(e);
    }

    @Override
    public boolean contains(long e) {
        return repository.contains(e);
    }

    @Override
    public void remove(long e) {
        repository.remove(e);
    }

    @Override
    public void clear() {
        repository.clear();
    }

    @Override
    public int size() {
        return repository.size();
    }
}
