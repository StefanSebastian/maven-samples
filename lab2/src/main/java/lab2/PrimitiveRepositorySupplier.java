package lab2;

import lab2.primitiveRepos.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Created by Sebi on 31-Oct-17.
 */
public enum PrimitiveRepositorySupplier implements Supplier<InMemoryRepositoryPrimitive> {
    HASH_SET(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new CollectionRepositoryPrimitive(HashSet::new);
        }
    },

    TREE_SET(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new CollectionRepositoryPrimitive(TreeSet::new);
        }
    },

    ARRAY_LIST(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new CollectionRepositoryPrimitive(ArrayList::new);
        }
    },

    LINKED_LIST(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new CollectionRepositoryPrimitive(LinkedList::new);
        }
    },

    CONCURRENT_HASH_MAP(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new CollectionRepositoryPrimitive(
                    () -> Collections.newSetFromMap(
                            new ConcurrentHashMap<Long, Boolean>()));
        }
    },

    ECLIPSE_LONG_ARRAY_LIST(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new EclipseLongArrayListRepo();
        }

    },

    ECLIPSE_LONG_HASH_SET(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new EclipseLongHashSetRepo();
        }
    },

    KOLOBOKE_LONG_HASH_SET(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new KolobokeLongHashSetRepo();
        }
    },

    KOLOBOKE_LONG_BOOL_MAP(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new KolobokeLongBoolMapRepo();
        }
    },

    TROVE_LONG_HASH_SET(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new TroveLongHashSetRepo();
        }
    },

    TROVE_LONG_ARRAY_LIST(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new TroveLongArrayListRepo();
        }
    },

    F_U_LONG_ARRAY_LIST(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new FastUtilLongArrayListRepo();
        }
    },

    F_U_LONG_HASH_SET(){
        @Override
        public InMemoryRepositoryPrimitive get(){
            return new FastUtilLongHashSetRepo();
        }
    }
}
