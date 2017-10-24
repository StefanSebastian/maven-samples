package lab2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Created by Sebi on 24-Oct-17.
 */
public enum RepositorySupplier implements Supplier<InMemoryRepository<Order>> {

    HASH_SET(){
        @Override
        public InMemoryRepository<Order> get(){
            return new CollectionRepository<>(HashSet::new);
        }
    },

    TREE_SET(){
        @Override
        public InMemoryRepository<Order> get(){
            return new CollectionRepository<>(TreeSet::new);
        }
    },

    ARRAY_LIST(){
        @Override
        public InMemoryRepository<Order> get(){
            return new CollectionRepository<>(ArrayList::new);
        }
    },

    LINKED_LIST(){
        @Override
        public InMemoryRepository<Order> get(){
            return new CollectionRepository<>(LinkedList::new);
        }
    },

    CONCURRENT_HASH_MAP(){
        @Override
        public InMemoryRepository<Order> get(){
            return new CollectionRepository<>(
                    () -> Collections.newSetFromMap(
                            new ConcurrentHashMap<Order, Boolean>()));
        }
    }
}
