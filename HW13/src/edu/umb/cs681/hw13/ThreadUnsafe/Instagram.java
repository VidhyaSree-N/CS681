package edu.umb.cs681.hw13.ThreadUnsafe;

public interface Instagram {
    public void like(int count);
    public void dislike(int count);
    public int getCount();
}
