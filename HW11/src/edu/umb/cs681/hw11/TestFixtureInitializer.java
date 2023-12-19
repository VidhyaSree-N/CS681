package edu.umb.cs681.hw11;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

public class TestFixtureInitializer {

        public static AtomicReference<SingletonFilesystem> createFS() {
            AtomicReference<SingletonFilesystem> fs = SingletonFilesystem.getFileSystem();
            LocalDateTime time = LocalDateTime.now();
            Directory root1 = new Directory(null, "root", 0, time);
            Directory src = new Directory(root1, "src", 0, time);
            File file_a = new File(src, "a", 64, time);
            File file_b = new File(src, "b", 128, time);


            Directory root2 = new Directory(null, "root", 0, time);
            Directory lib = new Directory(root2, "lib", 0, time);
            File file_c = new File(lib, "c", 32, time);


            Directory root3 = new Directory(null, "root", 0, time);
            Directory test = new Directory(root3, "test", 0, time);
            Directory srctest = new Directory(test, "src", 0, time);
            File file_d = new File(srctest, "d", 1024, time);


            fs.get().appendRootDirectory(root1);
            fs.get().appendRootDirectory(root2);
            fs.get().appendRootDirectory(root3);

            root1.appendChild(src);
            root2.appendChild(lib);
            root3.appendChild(test);
            test.appendChild(srctest);
            src.appendChild(file_a);
            src.appendChild(file_b);
            lib.appendChild(file_c);
            srctest.appendChild(file_d);

            return fs;
        }
    }

