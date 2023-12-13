package JavaConcurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class GoodCounter1 implements Counter {
    private AtomicInteger count;

    public GoodCounter1() {
        this.count = new AtomicInteger(0);
    }

    @Override
    public void incr() {
        this.count.getAndIncrement();
    }

    @Override
    public int getCount() {
        return this.count.get();
    }
}
