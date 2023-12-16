package edu.umb.cs681.hw10;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();

        Directory root = new Directory(null,"root", 0, time);

        AtomicBoolean done = new AtomicBoolean(false);

        Thread[] threads = new Thread[17];

        for(int i=0;i < 17;i++){
            threads[i] = new Thread(() -> {
              while (!done.get()) {
                  AtomicReference<SingletonFilesystem> singletonFilesystem = SingletonFilesystem.getFileSystem();
                  Directory thread = new Directory(root, Thread.currentThread().getName(), 0, time);
                  root.appendChild(thread);
                  singletonFilesystem.get().appendRootDirectory(root);
                  int size = singletonFilesystem.get().getRootDirs().size();
                  System.out.println(Thread.currentThread().getName() + " Id : " + Thread.currentThread().getId() + "  Instance : " + singletonFilesystem + "  Size of filesystem accessed : " + size);
                  if(Thread.currentThread().isInterrupted()){
                      System.out.println("Thread interrupted" + Thread.currentThread().getName());
                      return;
                  }
                  try {
                      Thread.sleep(2000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                      Thread.currentThread().interrupt();
                  }
              }
            });
            threads[i].start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        done.set(true);

        for (Thread thread : threads){
            thread.interrupt();
        }

        for (Thread thread : threads){
            try{
                thread.join();
            }catch (InterruptedException e){
                System.out.println(e.toString());
                e.printStackTrace();
            }
        }

    }
}
