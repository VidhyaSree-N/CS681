package edu.umb.cs681.hw17;

public class LineChartObserver implements Observer<StockEvent>{

    @Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        String ticker = event.ticker();
        double quote = event.quote();
        System.out.println("LineChartObserver : ticker = " + ticker + " and quote = "+ quote);
    }

}
