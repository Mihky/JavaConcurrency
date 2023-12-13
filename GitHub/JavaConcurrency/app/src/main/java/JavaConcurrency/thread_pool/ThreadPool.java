package JavaConcurrency.thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Each thread in a thread pool will always be polling for tasks
 * */
public class ThreadPool<T> {
    private int threadCount;
    private int capacity;
    private BlockingQueue<T> taskQueue;
    private List<Thread> threads;
    private AtomicBoolean isRunning;

    public ThreadPool(int threadCount, int capacity) {
        this.threadCount = threadCount;
        this.capacity = capacity;
        this.taskQueue = new ArrayBlockingQueue(this.capacity);
        this.threads = new ArrayList<>();
        this.isRunning = new AtomicBoolean(true);

        for (int thread = 0; thread < this.threadCount; thread++) {
            this.threads.add(new Thread(new ThreadPoolRunnable(), "" + thread));
        }
    }

    public void enqueueTask(T task) {
        synchronized (this.taskQueue) {
            while (true) {
                if (this.taskQueue.remainingCapacity() > 0) {
                    this.taskQueue.offer(task);
                    break;
                }
            }
        }
    }

    public synchronized void start() {
        for (Thread thread : threads) {
            System.out.println("Starting up thread: " + thread.getName());
            thread.start();
        }
    }

    public synchronized void shutdown() {
        for (Thread thread : threads) {
            System.out.println("Killing thread: " + thread.getName());
            thread.stop();
        }
    }
}
