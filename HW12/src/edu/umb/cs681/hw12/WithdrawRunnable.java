package edu.umb.cs681.hw12;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable {

    private BankAccount bankAccount;
    private AtomicBoolean done = new AtomicBoolean(false);
    private ReentrantLock lock = new ReentrantLock();

    public WithdrawRunnable(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setDone(){
        done.set(true);
    }

    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
            if(done.get()){
                System.out.println("Done flag set, Terminating withdraw thread.");
                break;
            }
            bankAccount.withdraw(100);
            Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
    }
}
