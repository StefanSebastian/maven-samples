package lab2;

import com.koloboke.collect.map.hash.HashObjObjMaps;
import com.koloboke.collect.set.hash.HashObjSet;
import com.koloboke.collect.set.hash.HashObjSets;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.TCustomHashMap;
import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TLinkedHashSet;
import it.unimi.dsi.fastutil.objects.*;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;

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
    },

    // eclipse collections
    ECLIPSE_MUTABLE_LIST(){
        @Override
        public InMemoryRepository<Order> get() {
            return new CollectionRepository<>(Lists.mutable::empty);
        }
    },

    ECLISPE_MUTABLE_SET(){
        @Override
        public InMemoryRepository<Order> get() {
            return new CollectionRepository<>(Sets.mutable::empty);
        }
    },

    ECLIPSE_MUTABLE_MAP(){
        @Override
        public InMemoryRepository<Order> get() {
            return new CollectionRepository<>(() -> Collections.newSetFromMap(Maps.mutable.empty()));
        }
    },

    // Koloboke
    KOLOBOKE_HASH_SET(){
        @Override
        public InMemoryRepository<Order> get() {
            return new CollectionRepository<>(HashObjSets::newMutableSet);
        }
    },

    KOLOBOKE_HASH_MAP(){
      @Override
        public InMemoryRepository<Order> get() {
          return new CollectionRepository<>(
                  () -> Collections.newSetFromMap(HashObjObjMaps.newMutableMap()));
      }
    },

    TROVE_HASH_SET() {
        @Override
        public InMemoryRepository<Order> get() {
            return new CollectionRepository<>(THashSet::new);
        }
    },

    TROVE_LINKED_HASH_SET() {
        @Override
        public InMemoryRepository<Order> get() {
            return new CollectionRepository<>(TLinkedHashSet::new);
        }
    },

    FASTUTILS_ARRAY_LIST() {
        @Override
        public InMemoryRepository<Order> get() {
            return new CollectionRepository<>(ObjectArrayList::new);
        }
    },

    FASTUTILS_ARRAY_SET() {
        @Override
        public InMemoryRepository<Order> get() {
            return new CollectionRepository<>(ObjectArraySet::new);
        }
    },

    FASTUTILS_AVL_SET() {
        @Override
        public InMemoryRepository<Order> get() {
            return new CollectionRepository<>(ObjectAVLTreeSet::new);
        }
    },

    FASTUTILS_RTB_SET() {
        @Override
        public InMemoryRepository<Order> get() {
            return new CollectionRepository<>(ObjectRBTreeSet::new);
        }
    }
}
