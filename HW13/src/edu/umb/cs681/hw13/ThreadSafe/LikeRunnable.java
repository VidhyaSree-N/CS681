package edu.umb.cs681.hw13.ThreadSafe;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class LikeRunnable implements Runnable{

    private Instagram instagram;
    private AtomicBoolean done = new AtomicBoolean(false);
    private ReentrantLock lock = new ReentrantLock();

    public LikeRunnable(Instagram instagram){
        this.instagram = instagram;
    }

    @Override
    public void run() {
        while (true){
            lock.lock();
            try{
                if(done.get()){
                    System.out.println("Termination set");
                    break;
                }
                instagram.like(1);
                Thread.sleep(4444);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
    }

    public void setDone() {
        done.set(true);
    }
}
