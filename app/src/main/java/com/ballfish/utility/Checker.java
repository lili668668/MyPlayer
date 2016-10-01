package com.ballfish.utility;

import java.io.File;

public class Checker {
    public static boolean IsExist(String path) {
        File f = new File(path);
        return f.exists();
    }

    public static boolean IsExist(FileInfo fileInfo) {
        File f = new File(fileInfo.path);
        return f.exists();
    }

    public static boolean CanRead(String path) {
        File f = new File(path);
        return f.canRead();
    }

    public static boolean CanRead(FileInfo fileInfo) {
        File f = new File(fileInfo.path);
        return f.canRead();
    }

}
