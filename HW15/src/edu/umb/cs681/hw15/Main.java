package edu.umb.cs681.hw15;

public class Main {
    public static void main(String[] args) {

    StockQuoteObservable stockQuote = new StockQuoteObservable();

    Thread[] threads = new Thread[15];
    for (int i = 0 ; i < 15 ; i++) {
        threads[i] = new Thread(() -> {
            stockQuote.changeQuote("QwerTy", 864.64);
            System.out.println(Thread.currentThread().getName() + "  "+ stockQuote.getTickerQuoteMap());
        });
        threads[i].start();
    }

    for (Thread thread : threads) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}}
