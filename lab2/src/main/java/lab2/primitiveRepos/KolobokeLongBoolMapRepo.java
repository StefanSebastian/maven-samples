package lab2.primitiveRepos;

import com.koloboke.collect.map.hash.HashLongObjMap;
import com.koloboke.collect.map.hash.HashLongObjMaps;
import lab2.InMemoryRepositoryPrimitive;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by Sebi on 31-Oct-17.
 */
public class KolobokeLongBoolMapRepo implements InMemoryRepositoryPrimitive {
    Collection<Long> repository = Collections.newSetFromMap(HashLongObjMaps.newMutableMap());

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
