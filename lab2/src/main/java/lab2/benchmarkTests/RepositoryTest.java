package lab2.benchmarkTests;

import lab2.ArrayListBasedRepository;
import lab2.HashSetBasedRepository;
import lab2.Order;
import lab2.TreeSetBasedRepository;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.HotspotMemoryProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sebi on 17-Oct-17.
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 20, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class RepositoryTest {


    @State(Scope.Thread)
    public static class RepositoryState {
        @Param({"1", "100", "10000"})
        public int nrOperations;

        public int initialSize = 10000;

        public ArrayListBasedRepository<Order> arrayListBasedRepository;
        public HashSetBasedRepository<Order> hashSetBasedRepository;
        public TreeSetBasedRepository<Order> treeSetBasedRepository;

        public Random random;

        public List<Order> queryList;

        @Setup(Level.Iteration)
        public void doSetup(){
            System.out.println("Iteration level executed");
            random = new Random();
            arrayListBasedRepository = new ArrayListBasedRepository<>();
            hashSetBasedRepository = new HashSetBasedRepository<>();
            treeSetBasedRepository = new TreeSetBasedRepository<>();

            // query list - contains the repo elements in a random order
            queryList = new ArrayList<>();
            // initialize each repo with a set of data
            for (int i = 1; i <= initialSize; i++){
                Order order = new Order(i, random.nextInt() % 100, random.nextInt() % 100);
                arrayListBasedRepository.add(order);
                hashSetBasedRepository.add(order);
                treeSetBasedRepository.add(order);

                queryList.add(order);
            }
            // shuffle the query list
            Collections.shuffle(queryList);
        }
    }

    @Benchmark
    public void arrayListAdd(RepositoryState state){
        for (int i = state.initialSize + 1; i < state.initialSize + 1 + state.nrOperations; i++){
            state.arrayListBasedRepository.add(new Order(i, state.random.nextInt() % 100, state.random.nextInt() % 100));
        }
    }

    @Benchmark
    public void hashSetAdd(RepositoryState state){
        for (int i = state.initialSize + 1; i < state.initialSize + 1 + state.nrOperations; i++){
            state.hashSetBasedRepository.add(new Order(i, state.random.nextInt() % 100, state.random.nextInt() % 100));
        }
    }

    @Benchmark
    public void treeSetAdd(RepositoryState state){
        for (int i = state.initialSize + 1; i < state.initialSize + 1 + state.nrOperations; i++){
            state.treeSetBasedRepository.add(new Order(i, state.random.nextInt() % 100, state.random.nextInt() % 100));
        }
    }

    @Benchmark
    public void arrayListContains(RepositoryState state, Blackhole blackhole){
        for (int i = 0; i < state.nrOperations; i++){
            boolean contains = state.arrayListBasedRepository.contains(state.queryList.get(i));
            blackhole.consume(contains);
        }
    }

    @Benchmark
    public void hashSetContains(RepositoryState state, Blackhole blackhole){
        for (int i = 0; i < state.nrOperations; i++){
            boolean contains = state.hashSetBasedRepository.contains(state.queryList.get(i));
            blackhole.consume(contains);
        }
    }

    @Benchmark
    public void treeSetContains(RepositoryState state, Blackhole blackhole){
        for (int i = 0; i < state.nrOperations; i++){
            boolean contains = state.treeSetBasedRepository.contains(state.queryList.get(i));
            blackhole.consume(contains);
        }
    }

    @Benchmark
    public void arrayListRemove(RepositoryState state){
        for (int i = 0; i < state.nrOperations; i++){
            state.arrayListBasedRepository.remove(state.queryList.get(i));
        }
    }

    @Benchmark
    public void hashSetRemove(RepositoryState state){
        for (int i = 0; i < state.nrOperations; i++){
            state.hashSetBasedRepository.remove(state.queryList.get(i));
        }
    }

    @Benchmark
    public void treeSetRemove(RepositoryState state){
        for (int i = 0; i < state.nrOperations; i++){
            state.treeSetBasedRepository.remove(state.queryList.get(i));
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(RepositoryTest.class.getSimpleName())
                .addProfiler(HotspotMemoryProfiler.class)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
