package JavaConcurrency;

public class GoodCounter implements Counter {
    private int count;
    private int atomicCount;

    public GoodCounter() {
        this.count = 0;
    }

    @Override
    public void incr() {
        synchronized (this) {
            this.count++;
        }
    }

    @Override
    public int getCount() {
        synchronized (this) {
            return this.count;
        }
    }
}
