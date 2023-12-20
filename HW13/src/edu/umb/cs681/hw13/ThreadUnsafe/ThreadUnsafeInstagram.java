package edu.umb.cs681.hw13.ThreadUnsafe;

public class ThreadUnsafeInstagram implements Instagram {

    private int totalLikes = 0;

    @Override
    public void like(int count) {
        try {
            System.out.println("Current likes: " + totalLikes + "  Thread id: " + Thread.currentThread().getId());
            totalLikes += count;
            System.out.println("New likes: " + totalLikes + "  Thread id: " + Thread.currentThread().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dislike(int count) {
        try {
            System.out.println("Current likes: " + totalLikes + "  Thread id: " + Thread.currentThread().getId());
            totalLikes -= count;
            System.out.println("New likes: " + totalLikes + "  Thread id: " + Thread.currentThread().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return this.totalLikes;
    }
}
