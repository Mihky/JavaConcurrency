package JavaConcurrency.blocking_queue;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueueImpl {
    private List<Object> blockingQueue;
    private int capacity;

    public BlockingQueueImpl(int capacity) {
        this.blockingQueue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void queueTask(Object task) throws InterruptedException {
        while (this.blockingQueue.size() == this.capacity) {
            wait();
        }

        this.blockingQueue.add(task);

        if (this.blockingQueue.size() == 1) {
            notifyAll();
        }
    }

    public synchronized Object pullTask() throws InterruptedException {
        while (this.blockingQueue.size() == 0) {
            wait();
        }

        Object task = this.blockingQueue.remove(0);

        if (this.blockingQueue.size() + 1 == this.capacity) {
            notifyAll();
        }
        return task;
    }
}
