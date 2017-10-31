package lab2;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by Sebi on 31-Oct-17.
 */
public class CollectionRepositoryPrimitive implements InMemoryRepositoryPrimitive {
    Collection<Long> delegate;

    public CollectionRepositoryPrimitive(Supplier<? extends Collection<Long>> supplier){
        this.delegate = supplier.get();
    }


    @Override
    public void add(long e) {
        delegate.add(e);
    }

    @Override
    public boolean contains(long e) {
        return delegate.contains(e);
    }

    @Override
    public void remove(long e) {
        delegate.remove(e);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public int size() {
        return delegate.size();
    }
}
