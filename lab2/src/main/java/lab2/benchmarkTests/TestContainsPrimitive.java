package lab2.benchmarkTests;

import lab2.states.PrimitiveRepositoryState;
import lab2.states.SizeState;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sebi on 31-Oct-17.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 20, time = 1)
@Fork(2)
public class TestContainsPrimitive {
    @State(Scope.Benchmark)
    public static class NewStateContains {
        long value;

        @Setup(Level.Invocation)
        public void generateLong(SizeState sizeState) {
            value = sizeState.newLongSupplier.get();
        }

    }

    @State(Scope.Benchmark)
    public static class ExistingStateContains {
        long value;

        @Setup(Level.Invocation)
        public void generateLong(SizeState sizeState) {
            value = sizeState.existingLongSupplier.get();
        }
    }

    @Benchmark
    public void containsExisting(PrimitiveRepositoryState repositoryState,
                                 ExistingStateContains existingState,
                                 Blackhole blackhole){
        blackhole.consume(repositoryState.repository.contains(existingState.value));
    }

    @Benchmark
    public void containsNew(PrimitiveRepositoryState repositoryState,
                            NewStateContains newState,
                            Blackhole blackhole) {
        blackhole.consume(repositoryState.repository.contains(newState.value));
    }
}
