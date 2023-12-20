package edu.umb.cs681.hw13.ThreadUnsafe;

import java.util.concurrent.atomic.AtomicBoolean;

public class DislikeRunnable implements Runnable {
    private Instagram instagram;
    private AtomicBoolean done = new AtomicBoolean(false);

    public DislikeRunnable(Instagram instagram){
        this.instagram = instagram;
    }

    @Override
    public void run() {
        while (true){
            try {
                if(done.get()){
                    System.out.println("Termination set");
                    break;
                }
                instagram.dislike(1);
                Thread.sleep(3333);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDone() {
        done.set(true);
    }
}
