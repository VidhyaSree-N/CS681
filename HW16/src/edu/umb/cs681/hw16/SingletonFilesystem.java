package edu.umb.cs681.hw16;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class SingletonFilesystem {
    private static AtomicReference<SingletonFilesystem> instance = new AtomicReference<>();
    private static ReentrantLock singletonreentrantLock = new ReentrantLock();
    private ReentrantLock reentrantLock = new ReentrantLock();
    private LinkedList<Directory> rootDirs;

    private SingletonFilesystem() {
        this.rootDirs = new LinkedList<>();
    }

    public static AtomicReference<SingletonFilesystem> getFileSystem() {
        singletonreentrantLock.lock();
        try {
            if (instance.get() == null) {
                instance.set(new SingletonFilesystem());
            }
        }finally {
            singletonreentrantLock.unlock();
        }
        return instance;
    }

    public LinkedList<Directory> getRootDirs() {
        reentrantLock.lock();
        try {
            return this.rootDirs;
        }finally {
            reentrantLock.unlock();
        }
    }

    public void appendRootDirectory(Directory root) {
        reentrantLock.lock();
        try {
            this.rootDirs.add(root);
        }finally {
            reentrantLock.unlock();
        }
    }
}
