package lab2.benchmarkTests;

import lab2.Order;
import lab2.states.RepositoryState;
import lab2.states.SizeState;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sebi on 24-Oct-17.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 20, time = 1)
@Fork(2)
public class TestContains {

    /*
    Generates new orders
     */
    @State(Scope.Benchmark)
    public static class NewStateContains {
        Order order;

        @Setup(Level.Invocation)
        public void generateOrder(SizeState sizeState) {
            order = sizeState.newOrderSupplier.get();
        }
    }

    /*
    Generates random order that are in repo
     */
    @State(Scope.Benchmark)
    public static class ExistingStateContains {
        Order order;

        @Setup(Level.Invocation)
        public void generateOrder(SizeState sizeState) {
            order = sizeState.existingOrderSupplier.get();
        }
    }

    @Benchmark
    public void containsExisting(RepositoryState repositoryState,
                                 ExistingStateContains existingState,
                                 Blackhole blackhole){
        blackhole.consume(repositoryState.repository.contains(existingState.order));
    }

    @Benchmark
    public void containsNew(RepositoryState repositoryState,
                                 NewStateContains newState,
                                 Blackhole blackhole){
        blackhole.consume(repositoryState.repository.contains(newState.order));
    }
}
