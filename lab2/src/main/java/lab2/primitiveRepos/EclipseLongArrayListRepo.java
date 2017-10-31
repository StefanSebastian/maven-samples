package lab2.primitiveRepos;


import lab2.InMemoryRepositoryPrimitive;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;

/**
 * Created by Sebi on 31-Oct-17.
 */
public class EclipseLongArrayListRepo implements InMemoryRepositoryPrimitive {
    private LongArrayList repository;

    public EclipseLongArrayListRepo(){
        repository = new LongArrayList();
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
