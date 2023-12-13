package JavaConcurrency;

/**
 * Lesson 1: https://jenkov.com/tutorials/java-concurrency/creating-and-starting-threads.html
 * */
public class DictatorRunnable implements Runnable {
    private boolean isKilled;

    public DictatorRunnable() {
        this.isKilled = false;
    }

    @Override
    public void run() {
        System.out.println("Dictator is starting his reign of terror");
        while (isAlive()) {
            System.out.println("Dictator has conquered territory");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // The isAlive() method is called internally by the thread executing the DictatorRunnable's run() method
    private boolean isAlive() {
        synchronized (this) {
            return this.isKilled == false;
        }
    }

    // The doStop() is intended to be called from another thread than the thread executing the DictatorRunnable's run() method
    public void doStop() {
        synchronized (this) {
            this.isKilled = true;
            System.out.println("Dictator has been killed");
        }
    }
}
