package JavaConcurrency;

/**
 * Example of a bad counter class because of happens-before guarantees.
 * Volatile/Synchronized writes must come at the very end to guarantee atomicity.
 * This example shows that at the time of updating/write position2++, every write before then will be guaranteed to write to RAM.
 * Since position1++ is after the synchronized block, no guarantee that the write will be writing the position1 value in RAM.
 * */
public class HappensBeforeWriteRunnable1 implements Runnable {
    private int position1;
    private int position2;

    public HappensBeforeWriteRunnable1() {
        this.position1 = 0;
        this.position2 = 0;
    }

    @Override
    public void run() {
        for (int iteration = 0; iteration < 1_000_000; iteration++) {
            synchronized (this) {
                this.position2++;
            }
            this.position1++;
        }
    }

    public synchronized int getPosition1() {
        return this.position1;
    }

    public synchronized int getPosition2() {
        return this.position2;
    }
}
