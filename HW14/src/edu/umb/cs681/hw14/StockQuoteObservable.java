package edu.umb.cs681.hw14;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable<StockEvent>{

    private Map<String, Double> tandqMap;
    ReentrantLock lockTQ = new ReentrantLock();

    public StockQuoteObservable() {
        tandqMap = new HashMap<>();
    }

    public void changeQuote(String t, double q){
        lockTQ.lock();
        try {
            tandqMap.put(t,q);
        }finally {
            lockTQ.unlock();
        }
        notifyObservers(new StockEvent(t,q));
    }

    public Map<String, Double> getTickerQuoteMap() {
        return tandqMap;
    }
}
