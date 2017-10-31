package lab2.benchmarkTests;

import lab2.Order;
import lab2.states.PrimitiveRepositoryState;
import lab2.states.RepositoryState;
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
public class TestAddPrimitive {
    @State(Scope.Benchmark)
    public static class NewStateAdd {
        long value;

        @Setup(Level.Invocation)
        public void generateLong(SizeState sizeState) {
            value = sizeState.newLongSupplier.get();
        }

        @TearDown(Level.Invocation)
        public void removeLong(PrimitiveRepositoryState repoState) {
            repoState.repository.remove(value);
        }
    }

    @State(Scope.Benchmark)
    public static class ExistingStateAdd {
        long value;
        int repoSize;

        @Setup(Level.Invocation)
        public void generateLong(SizeState sizeState) {
            value = sizeState.existingLongSupplier.get();
            repoSize = sizeState.size;
        }

        @TearDown(Level.Invocation)
        public void removeLong(PrimitiveRepositoryState repoState) {
            if (repoState.repository.size() > repoSize){
                repoState.repository.remove(value);
            }
        }
    }

    @Benchmark
    public void addExisting(PrimitiveRepositoryState repositoryState, ExistingStateAdd existingState){
        repositoryState.repository.add(existingState.value);
    }

    @Benchmark
    public void addNew(PrimitiveRepositoryState repositoryState, NewStateAdd newState) {
        repositoryState.repository.add(newState.value);
    }
}
