package JavaConcurrency.thread_pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPoolRunnable implements Runnable {
    private Thread thread;
    private BlockingQueue taskQueue;
    private AtomicBoolean isRunning;

    public ThreadPoolRunnable(BlockingQueue taskQueue) {
        this.taskQueue = taskQueue;
        this.isRunning = new AtomicBoolean(true);
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while(isRunning.get()){
            try{
                Runnable runnable = (Runnable) taskQueue.take();
                runnable.run();
            } catch(Exception e){
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void stop() {
        while (isRunning.get()) {
            this.isRunning = isRunning.getAndSet(false);
            this.thread.interrupt();
        }
    }
}
