package lab2.states;

import lab2.InMemoryRepositoryPrimitive;
import lab2.PrimitiveRepositorySupplier;
import org.openjdk.jmh.annotations.*;

import java.util.stream.LongStream;

/**
 * Created by Sebi on 31-Oct-17.
 */
@State(Scope.Benchmark)
public class PrimitiveRepositoryState {
    @Param
    private PrimitiveRepositorySupplier repositorySupplier;

    public InMemoryRepositoryPrimitive repository;

    @Setup
    public void setUp(SizeState sizeState){
        System.out.println("Repo setup");
        repository = repositorySupplier.get();

        LongStream.rangeClosed(1, sizeState.size)
                .forEach(repository::add);

        System.out.println(repository.size());
        System.out.println("----------------");
    }

    @TearDown
    public void tearDown(){
        repository.clear();
        System.gc();
    }
}
