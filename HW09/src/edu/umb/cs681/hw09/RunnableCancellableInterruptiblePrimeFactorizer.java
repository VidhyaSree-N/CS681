package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer{
     public RunnableCancellableInterruptiblePrimeFactorizer(long dividend ,long from,long to){
          super(dividend,from,to);
     }
     private boolean done = false;
     private final ReentrantLock reentrantLock = new ReentrantLock();

     public void setDone(){
          reentrantLock.lock();
          try {
               done = true;
          } finally {
               reentrantLock.unlock();
          }
     }

     public void generatePrimeFactors(){
          long divisor = from;
          while (dividend != 1 && divisor != 0){
               reentrantLock.lock();
               try {
                    if(done)
                         break;
                    if(divisor > 2 && isEven(divisor)){
                         divisor++;
                         continue;
                    }
                    if (dividend % divisor == 0){
                         factors.add(divisor);
                         dividend /= divisor;
                    }
                    else {
                         if(divisor == 2)
                              divisor++;
                         else
                              divisor += 2;
                    }
               }finally {
                    reentrantLock.unlock();
               }
               try {
                    Thread.sleep(3333);
               }catch (InterruptedException e){
                    System.out.println(e.toString());
                    continue;
               }
          }
     }

     public static void main(String[] args){
          RunnableCancellableInterruptiblePrimeFactorizer runnableCancellableInterruptiblePrimeFactorizer =
                  new RunnableCancellableInterruptiblePrimeFactorizer(25,2,500);
          Thread thread = new Thread(runnableCancellableInterruptiblePrimeFactorizer);
          thread.start();
          try {
               Thread.sleep(5555);
          }catch (InterruptedException e){
               System.out.println(e.toString());
          }
          runnableCancellableInterruptiblePrimeFactorizer.setDone();
          thread.interrupt();
          try {
               thread.join();
          }catch (InterruptedException e){
               System.out.println(e.toString());
          }
          System.out.println("Factors : " + runnableCancellableInterruptiblePrimeFactorizer.getPrimeFactors().size());
     }
}