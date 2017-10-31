package lab2.benchmarkTests;

import lab2.states.PrimitiveRepositoryState;
import lab2.states.SizeState;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sebi on 31-Oct-17.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 20, time = 1)
@Fork(2)
public class TestRemovePrimitive {
    @State(Scope.Benchmark)
    public static class NewStateRemove {
        long value;

        @Setup(Level.Invocation)
        public void generateLong(SizeState sizeState) {
            value = sizeState.newLongSupplier.get();
        }

    }

    @State(Scope.Benchmark)
    public static class ExistingStateRemove {
        long value;

        @Setup(Level.Invocation)
        public void generateLong(SizeState sizeState) {
            value = sizeState.existingLongSupplier.get();
        }

        @TearDown(Level.Invocation)
        public void addLong(PrimitiveRepositoryState repoState) {
           repoState.repository.add(value);
        }
    }

    @Benchmark
    public void removeExisting(PrimitiveRepositoryState repositoryState, ExistingStateRemove existingState){
        repositoryState.repository.remove(existingState.value);
    }

    @Benchmark
    public void removeNew(PrimitiveRepositoryState repositoryState, NewStateRemove newState) {
        repositoryState.repository.remove(newState.value);
    }
}
