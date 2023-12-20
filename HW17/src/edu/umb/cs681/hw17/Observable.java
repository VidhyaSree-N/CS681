package edu.umb.cs681.hw17;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<T> {
	private ConcurrentLinkedQueue<Observer<T>> observers = new ConcurrentLinkedQueue<>();
	ReentrantLock lockObs = new ReentrantLock();
	
	public void addObserver(Observer<T> o) {
		observers.add(o);
	}

	public void clearObservers() {
		observers.clear();
		
	}

	// No need for ConLinkQue as it directly provides queue when called
//	public List<Observer<T>> getObservers(){
//		return observers;
//	}
	
	public int countObservers() {
		return observers.size();
		
	}
	public void removeObserver(Observer<T> o) {
		observers.remove(o);
	}

	public void notifyObservers(T event) {
		observers.forEach( (observer)->{observer.update(this, event);} );
	}
	
}
