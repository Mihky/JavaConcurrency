package JavaConcurrency;

public class MyRunnable implements Runnable {
    public MyRunnable() {}

    @Override
    public void run() {
        System.out.println("MyRunnable running");
    }
}
