package lab2.states;

import lab2.InMemoryRepository;
import lab2.Order;
import lab2.RepositorySupplier;
import org.openjdk.jmh.annotations.*;

import java.util.stream.IntStream;

/**
 * Created by Sebi on 24-Oct-17.
 *
 * Initializes and populates repositories
 */
@State(Scope.Benchmark)
public class RepositoryState {
    @Param
    private RepositorySupplier repositorySupplier;

    public InMemoryRepository<Order> repository;

    @Setup
    public void setUp(SizeState sizeState){
        System.out.println(getClass().getSimpleName() + " setup - populate");
        repository = repositorySupplier.get();

        IntStream.rangeClosed(1, sizeState.size)
                .mapToObj(Order::new)
                .forEach(repository::add);
    }

    @TearDown
    public void tearDown(){
        System.out.println(getClass().getSimpleName() + "teardown - clear");
        repository.clear();
        System.gc();
    }
}
