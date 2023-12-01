package edu.umb.cs681.hw08;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class SingletonFilesystem {
    private static SingletonFilesystem instance = null;
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private LinkedList<Directory> rootDirs;

    private SingletonFilesystem() {
        this.rootDirs = new LinkedList<>();
    }

    public static SingletonFilesystem getFileSystem() {
        reentrantLock.lock();
        try {
            if (instance == null) {
                instance = new SingletonFilesystem();
            }
            return instance;
        }finally {
            reentrantLock.unlock();
        }
    }

    public LinkedList<Directory> getRootDirs() {
        return this.rootDirs;
    }

    public void appendRootDirectory(Directory root) {
        this.rootDirs.add(root);
    }
}
