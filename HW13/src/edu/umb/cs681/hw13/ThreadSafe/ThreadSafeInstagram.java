package edu.umb.cs681.hw13.ThreadSafe;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeInstagram implements Instagram {
    private int totalLikes = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition noLikes = lock.newCondition();

    @Override
    public void like(int count) {
        lock.lock();
        try {
            System.out.println("Current likes: " + totalLikes + "  Thread id: " + Thread.currentThread().getId());
            totalLikes += count;
            System.out.println("New likes: " + totalLikes + "  Thread id: " + Thread.currentThread().getId());
            noLikes.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void dislike(int count) {
        lock.lock();
        try {
            while (totalLikes == 0){
                System.out.println("No likes to dislike");
                noLikes.await();
            }
            System.out.println("Current likes: " + totalLikes + "  Thread id: " + Thread.currentThread().getId());
            totalLikes -= count;
            System.out.println("New likes: " + totalLikes + "  Thread id: " + Thread.currentThread().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public int getCount() {
        return this.totalLikes;
    }
}
