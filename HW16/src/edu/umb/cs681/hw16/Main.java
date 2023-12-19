package edu.umb.cs681.hw16;

import edu.umb.cs681.hw16.util.FileCrawlingVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        AtomicReference<SingletonFilesystem> singletonFilesystem = TestFixtureInitializer.createFS();

        ConcurrentLinkedQueue<File> sharedFiles = new ConcurrentLinkedQueue<>();

        AtomicBoolean done = new AtomicBoolean(false);

        ReentrantLock reentrantLock = new ReentrantLock();

        Directory root1 = singletonFilesystem.get().getRootDirs().get(0);
        Directory root2 = singletonFilesystem.get().getRootDirs().get(1);
        Directory root3 = singletonFilesystem.get().getRootDirs().get(2);

        ThreadLocal<FileCrawlingVisitor> threadLocalVisitor = ThreadLocal.withInitial(FileCrawlingVisitor::new);

        Thread t1 = new Thread(() -> {
           while (!done.get()) {
               root1.accept(threadLocalVisitor.get());
               sharedFiles.addAll(threadLocalVisitor.get().getFiles());
               if (Thread.currentThread().isInterrupted()){
                   return;
               }
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }

        });

        Thread t2 = new Thread(() -> {
            while (!done.get()) {
                root2.accept(threadLocalVisitor.get());
                sharedFiles.addAll(threadLocalVisitor.get().getFiles());
                if (Thread.currentThread().isInterrupted()){
                    return;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            while (!done.get()) {
                root3.accept(threadLocalVisitor.get());
                sharedFiles.addAll(threadLocalVisitor.get().getFiles());
                if (Thread.currentThread().isInterrupted()){
                    return;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        done.set(true);

        t1.interrupt();
        t2.interrupt();
        t3.interrupt();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        List<File> identifiedFiles = new ArrayList<>(sharedFiles);
        System.out.println("Identified Files:" + identifiedFiles.size());

        System.out.println("Printing unique files to check if every tree is crawled");
        List<File> uniqueFiles = new ArrayList<>();
        for (File file : identifiedFiles) {
            boolean isDuplicate = false;
            for (File uniqueFile : uniqueFiles) {
                if (uniqueFile.getName().equals(file.getName())) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                uniqueFiles.add(file);
            }
        }

        System.out.println("Unique Identified Files: " + uniqueFiles.size());
        for (File file : uniqueFiles) {
            System.out.println(file.getName());
        }
    }
}
