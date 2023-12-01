package edu.umb.cs681.hw08;

import java.time.LocalDateTime;

public abstract class FSElement {
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected Directory parent;

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public edu.umb.cs681.hw08.Directory getParent(){
        return this.parent;
    }
    public String getName() {
        return this.name;
    }
    public int getSize() {
        return this.size;
    }
    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        // For Directory and Link size is zero
        if(isDirectory() || isLink()){
            this.size = 0;
        }
        else{
            this.size = size;
        }
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public abstract boolean isDirectory();

    public abstract boolean isLink();


}
