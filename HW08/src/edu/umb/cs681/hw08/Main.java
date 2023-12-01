package edu.umb.cs681.hw08;


public class Main {
    public static void main(String[] args) {
        Runnable fileSystem = () -> {
            SingletonFilesystem singletonFilesystem = SingletonFilesystem.getFileSystem();
            System.out.println("Thread : " + Thread.currentThread().getId() + "  Instance : " + singletonFilesystem);
        };

        Thread thread1 = new Thread(fileSystem);
        Thread thread2 = new Thread(fileSystem);
        Thread thread3 = new Thread(fileSystem);
        Thread thread4 = new Thread(fileSystem);
        Thread thread5 = new Thread(fileSystem);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
