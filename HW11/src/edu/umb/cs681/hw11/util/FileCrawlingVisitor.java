package edu.umb.cs681.hw11.util;

import edu.umb.cs681.hw11.Directory;
import edu.umb.cs681.hw11.FSVisitor;
import edu.umb.cs681.hw11.File;
import edu.umb.cs681.hw11.Link;

import java.util.LinkedList;

public class FileCrawlingVisitor implements FSVisitor {

    private LinkedList<File> files = new LinkedList<>();

    @Override
    public void visit(Directory dir) {
        return;
    }

    @Override
    public void visit(File file) {
        files.add(file);
    }

    @Override
    public void visit(Link link) {
        return;
    }

    public LinkedList<File> getFiles() {
        return this.files;
    }

}
