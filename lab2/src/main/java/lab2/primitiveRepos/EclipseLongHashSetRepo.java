package lab2.primitiveRepos;

import lab2.InMemoryRepositoryPrimitive;
import org.eclipse.collections.impl.set.mutable.primitive.LongHashSet;

/**
 * Created by Sebi on 31-Oct-17.
 */
public class EclipseLongHashSetRepo implements InMemoryRepositoryPrimitive {
    private LongHashSet repository;

    public EclipseLongHashSetRepo(){
        repository = new LongHashSet();
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
