package lab2.benchmarkTests;

import lab2.Order;
import lab2.states.RepositoryState;
import lab2.states.SizeState;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sebi on 24-Oct-17.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 20, time = 1)
@Fork(2)
public class TestRemove {
    /*
    Generates new orders
     */
    @State(Scope.Benchmark)
    public static class NewStateRemove {
        Order order;

        @Setup(Level.Invocation)
        public void generateOrder(SizeState sizeState) {
            order = sizeState.newOrderSupplier.get();
        }
    }

    /*
    Generates random orders that are in repo
    reinserts them afterwards
     */
    @State(Scope.Benchmark)
    public static class ExistingStateRemove {
        Order order;

        @Setup(Level.Invocation)
        public void generateOrder(SizeState sizeState) {
            order = sizeState.existingOrderSupplier.get();
        }

        @TearDown(Level.Invocation)
        public void addOrder(RepositoryState repositoryState){
            repositoryState.repository.add(order);
        }
    }

    @Benchmark
    public void removeExisting(RepositoryState repositoryState,
                                 ExistingStateRemove existingState){
        repositoryState.repository.remove(existingState.order);
    }

    @Benchmark
    public void removeNew(RepositoryState repositoryState,
                            TestContains.NewStateContains newState){
        repositoryState.repository.remove(newState.order);
    }
}
