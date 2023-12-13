package JavaConcurrency;

public class BadCounter implements Counter {
    private int count;

    public BadCounter() {
        this.count = 0;
    }

    @Override
    public void incr() {
        this.count++;
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
