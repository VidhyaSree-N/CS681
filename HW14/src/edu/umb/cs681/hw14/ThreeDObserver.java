package edu.umb.cs681.hw14;

public class ThreeDObserver implements Observer<StockEvent> {
    @Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        String ticker = event.ticker();
        double quote = event.quote();
        System.out.println("3DObserver : ticker = " + ticker + " and quote = "+ quote);
    }
}
