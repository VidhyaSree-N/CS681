package edu.umb.cs681.hw13.ThreadUnsafe;


public class Main {
    public static void main(String[] args){
        ThreadUnsafeInstagram instagram = new ThreadUnsafeInstagram();

        System.out.println("Thread Unsafe ");
        for(int i = 0; i <3; i++){
            LikeRunnable likeRunnable = new LikeRunnable(instagram);
            DislikeRunnable dislikeRunnable = new DislikeRunnable(instagram);
            Thread like = new Thread(likeRunnable);
            Thread dislike = new Thread(dislikeRunnable);
            like.start();
            dislike.start();
            try {
                Thread.currentThread().sleep(5555);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            likeRunnable.setDone();
            dislikeRunnable.setDone();
            like.interrupt();
            dislike.interrupt();
            try {
                like.join();
                dislike.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed running " + i + " Thread");
        }
    }
}
