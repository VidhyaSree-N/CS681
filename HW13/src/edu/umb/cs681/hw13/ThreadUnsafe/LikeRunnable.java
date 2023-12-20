package edu.umb.cs681.hw13.ThreadUnsafe;

import java.util.concurrent.atomic.AtomicBoolean;

public class LikeRunnable implements Runnable{

    private Instagram instagram;
    private AtomicBoolean done = new AtomicBoolean(false);

    public LikeRunnable(Instagram instagram){
        this.instagram = instagram;
    }

    @Override
    public void run() {
        while (true){
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
        }
    }

    public void setDone() {
        done.set(true);
    }
}
