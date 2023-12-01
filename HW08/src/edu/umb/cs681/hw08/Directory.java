package edu.umb.cs681.hw08;

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
        return this.children;
    }

    public void appendChild(FSElement child){
        this.children.add(child);
        child.setParent(this);
    }

    public int countChildren(){
        return this.children.size();
    }

    public LinkedList<Directory> getSubDirectories() {
        for (FSElement fs : children) {
            if (fs.isDirectory()) {
                subDirectories.add((Directory) fs);
            }
        }
        return subDirectories;
    }

    public LinkedList<File> getFiles() {
        for (FSElement fsElement : children) {
            if (!fsElement.isDirectory() && !fsElement.isLink()) {
                files.add((File) fsElement);
            }
        }
        return files;
    }

    public LinkedList<Link> getLinks() {
        for (FSElement fsElement : children) {
            if (fsElement.isLink()) {
                Links.add((Link) fsElement);
            }
        }
        return Links;
    }

    public int getTotalSize() {
        totalSize=0;
        for (FSElement fsElement : children) {
            if (fsElement.isDirectory()) {
                totalSize = totalSize + ((Directory) fsElement).getTotalSize();
            } else {
                totalSize = totalSize + fsElement.getSize();
            }
        }
        return totalSize;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isLink() {
        return false;
    }

}
