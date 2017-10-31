package lab2.primitiveRepos;

import com.koloboke.collect.set.hash.HashLongSet;
import com.koloboke.collect.set.hash.HashLongSets;
import lab2.InMemoryRepositoryPrimitive;

/**
 * Created by Sebi on 31-Oct-17.
 */
public class KolobokeLongHashSetRepo implements InMemoryRepositoryPrimitive {
    private HashLongSet repository;

    public KolobokeLongHashSetRepo(){
        repository = HashLongSets.newMutableSet();
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
