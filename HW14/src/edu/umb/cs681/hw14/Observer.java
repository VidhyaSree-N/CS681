package edu.umb.cs681.hw14;

public interface Observer<T> {
	public void update(Observable<T> sender, T event);
}
