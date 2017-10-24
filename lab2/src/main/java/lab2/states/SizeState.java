package lab2.states;

import lab2.Order;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * Created by Sebi on 24-Oct-17.
 */

@State(Scope.Benchmark)
public class SizeState {
    @Param({"1000", "10000"})
    public int size;

    public Supplier<Order> existingOrderSupplier = new Supplier<Order>() {
        private final Random random = new Random();

        @Override
        public Order get() {
            return new Order(random.nextInt(size));
        }
    };

    public Supplier<Order> newOrderSupplier = new Supplier<Order>() {
        private final AtomicInteger currentSize = new AtomicInteger(10000);

        @Override
        public Order get() {
            return new Order(currentSize.incrementAndGet());
        }
    };
}
