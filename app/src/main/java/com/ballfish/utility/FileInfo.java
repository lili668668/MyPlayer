package com.ballfish.utility;

public class FileInfo {
    public String path;
    public String title;
    public String content;

    public FileInfo(String filePath) {
        this.path = filePath;

        String[] tmp = filePath.split("/");
        this.title = tmp[tmp.length-1].split("\\.")[0];

        content = Reader.Read(this);
    }
}
