package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer{

    private ReentrantLock reentrantLock = new ReentrantLock();
    private boolean done = false;
    public RunnableCancellablePrimeFactorizer(long dividend,long from ,long to){super(dividend,from,to);}

    public void setDone(){
        reentrantLock.lock();
        try{
            done = true;
        }
        finally {
            reentrantLock.unlock();
        }
    }

    public void generatePrimeFactors(){
        long divisor = from;
        while (dividend != 1 && divisor != 0){
            reentrantLock.lock();
            try{
                if(done)
                    break;
                if(divisor > 2 && isEven(divisor)){
                    divisor++;
                    continue;
                }
                if(dividend % divisor == 0){
                    factors.add(divisor);
                    dividend /= divisor;
                }
                else {
                    if (divisor == 2)
                        divisor++;
                    else {
                        divisor += 2;
                    }
                }
            }finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args){
        RunnableCancellablePrimeFactorizer runnableCancellablePrimeFactorizer = new RunnableCancellablePrimeFactorizer(30,5,4000);
        Thread thread = new Thread(runnableCancellablePrimeFactorizer);
        thread.start();
        runnableCancellablePrimeFactorizer.setDone();
        try{
            thread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Factors found : " + runnableCancellablePrimeFactorizer.getPrimeFactors().size());
    }
}
