package lab2.benchmarkTests;

import lab2.Order;
import lab2.states.RepositoryState;
import lab2.states.SizeState;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sebi on 24-Oct-17.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 20, time = 1)
@Fork(2)
public class TestAdd {

    /*
    generates new orders to add
    removes them after each invocation to avoid memory overflow
     */
    @State(Scope.Benchmark)
    public static class NewStateAdd {
        Order order;

        @Setup(Level.Invocation)
        public void generateOrder(SizeState sizeState) {
            order = sizeState.newOrderSupplier.get();
        }

        @TearDown(Level.Invocation)
        public void removeOrder(RepositoryState repoState) {
            repoState.repository.remove(order);
        }
    }

    /*
    generates existing orders to add
    if the current repo is a list then we delete the orders
     */
    @State(Scope.Benchmark)
    public static class ExistingStateAdd {
        Order order;

        @Setup(Level.Invocation)
        public void generateOrder(SizeState sizeState) {
            order = sizeState.existingOrderSupplier.get();
        }

        @TearDown(Level.Invocation)
        public void removeOrder(RepositoryState repoState) {
            if (repoState.repository instanceof List){
                repoState.repository.remove(order);
            }
        }
    }

    @Benchmark
    public void addExisting(RepositoryState repositoryState, ExistingStateAdd existingState){
        repositoryState.repository.add(existingState.order);
    }

    @Benchmark
    public void addNew(RepositoryState repositoryState, NewStateAdd newState) {
        repositoryState.repository.add(newState.order);
    }

}
