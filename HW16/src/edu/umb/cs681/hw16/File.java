package edu.umb.cs681.hw16;

import java.time.LocalDateTime;

public class File extends FSElement {
    public File(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
    }

    @Override
    public boolean isDirectory() {
        return false;
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
        }finally {
            reentrantLock.unlock();
        }
    }
}
