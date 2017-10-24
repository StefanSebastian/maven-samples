package lab2.benchmarkTests;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by Sebi on 24-Oct-17.
 *
 *
 * Test runner class
 */
public class RunTests {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAdd.class.getSimpleName()+".*")
                .include(TestContains.class.getSimpleName()+".")
                .include(TestRemove.class.getSimpleName()+".")
//                .jvmArgs("-Xms3048m", "-Xmx3048m", "-XX:+UseG1GC")
//                .addProfiler(HotspotMemoryProfiler.class)
//                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
