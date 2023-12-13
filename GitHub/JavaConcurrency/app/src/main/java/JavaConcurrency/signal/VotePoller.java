package JavaConcurrency.signal;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class VotePoller implements Runnable {
    private int voters;
    private AtomicInteger votesCandidateA;
    private AtomicInteger votesCandidateB;
    private Object monitorObject = new Object();

    public VotePoller(int voters) {
        this.voters = voters;
        this.votesCandidateA = new AtomicInteger(0);
        this.votesCandidateB = new AtomicInteger(0);
    }

    @Override
    public void run() {
        synchronized (this.monitorObject) {
            while (stillWaiting()) {
                try {
                    this.monitorObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(String.format("Voting has finished. Votes for candidateA: %d, Votes for candidateB: %d", this.votesCandidateA.get(), this.votesCandidateB.get()));
    }

    public void vote(int candidate) {
        try {
            Thread.sleep(100L * new Random().nextInt(10));
        } catch (Exception ex) {}

        synchronized (this.monitorObject) {
            if (candidate == 0) {
                this.votesCandidateA.getAndIncrement();
            } else {
                this.votesCandidateB.getAndIncrement();
            }
            this.monitorObject.notify();
        }
    }

    private boolean stillWaiting() {
        synchronized (this.monitorObject) {
            return (this.votesCandidateA.get() + this.votesCandidateB.get()) != voters;
        }
    }

    public synchronized int getVotesA() {
        return this.votesCandidateA.get();
    }

    public synchronized int getVotesB() {
        return this.votesCandidateB.get();
    }
}
