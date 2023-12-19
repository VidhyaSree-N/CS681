package edu.umb.cs681.hw16;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {
    private LinkedList<FSElement> children = new LinkedList<>();
    private LinkedList<Directory> subDirectories = new LinkedList<>();
    private LinkedList<File> files = new LinkedList<>();
    private LinkedList<Link> Links = new LinkedList<>();
    private int totalSize;


    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, 0, creationTime);
    }

    public LinkedList<FSElement> getChildren() {
        reentrantLock.lock();
        try {
            return this.children;
        }finally {
            reentrantLock.unlock();
        }
    }

    public void appendChild(FSElement child){
        reentrantLock.lock();
        try {
            this.children.add(child);
            child.setParent(this);
        }finally {
            reentrantLock.unlock();
        }
    }

    public int countChildren(){
        reentrantLock.lock();
        try {
            return this.children.size();
        }finally {
            reentrantLock.unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories() {
        reentrantLock.lock();
        try {
            for (FSElement fs : children) {
                if (fs.isDirectory()) {
                    subDirectories.add((Directory) fs);
                }
            }
            return subDirectories;
        }finally {
            reentrantLock.unlock();
        }
    }

    public LinkedList<File> getFiles() {
        reentrantLock.lock();
        try {
            for (FSElement fsElement : children) {
                if (!fsElement.isDirectory() && !fsElement.isLink()) {
                    files.add((File) fsElement);
                }
            }
            return files;
        }finally {
            reentrantLock.unlock();
        }
    }

    public LinkedList<Link> getLinks() {
        reentrantLock.lock();
        try {
            for (FSElement fsElement : children) {
                if (fsElement.isLink()) {
                    Links.add((Link) fsElement);
                }
            }
            return Links;
        }finally {
            reentrantLock.unlock();
        }
    }

    public int getTotalSize() {
        reentrantLock.lock();
        try {
            totalSize = 0;
            for (FSElement fsElement : children) {
                if (fsElement.isDirectory()) {
                    totalSize = totalSize + ((Directory) fsElement).getTotalSize();
                } else {
                    totalSize = totalSize + fsElement.getSize();
                }
            }
            return totalSize;
        }finally {
            reentrantLock.unlock();
        }
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isLink() {
        return false;
    }

    @Override
    public void accept(FSVisitor v) {
        reentrantLock.lock();
        try {
            v.visit(this);
            for (FSElement e : children) {
                e.accept(v);
            }
        }finally {
            reentrantLock.unlock();
        }
    }

}
