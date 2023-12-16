package edu.umb.cs681.hw10;

import java.time.LocalDateTime;

public class Link extends FSElement {
    private FSElement target;

    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime);
        this.target = target;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isLink() {
        return true;
    }

    public FSElement getTarget() {
        reentrantLock.lock();
        try {
            return this.target;
        }finally {
            reentrantLock.unlock();
        }
    }

    public void setTarget(FSElement target) {
        reentrantLock.lock();
        try {
            this.target = target;
        }finally {
            reentrantLock.unlock();
        }
    }
}
