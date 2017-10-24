package lab2;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by Sebi on 24-Oct-17.
 */
public class CollectionRepository<T> implements InMemoryRepository<T> {

    private final Collection<T> delegate;

    public CollectionRepository(Supplier<? extends Collection<T>> supplier){
        this.delegate = supplier.get();
    }

    @Override
    public void add(T elem) {
        delegate.add(elem);
    }

    @Override
    public boolean contains(T elem) {
        return delegate.contains(elem);
    }

    @Override
    public void remove(T elem) {
        delegate.remove(elem);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public int size(){
        return delegate.size();
    }

    @Override
    public String toString() {
        return String.valueOf(delegate);
    }

    protected Collection<T> getDelegate(){
        return delegate;
    }
}
