package com.ballfish.yin.player2;

public class MusicInfo {
    public String directory;
    public String path;
    public String title;

    public MusicInfo(String musicPath) {
        path = musicPath;

        String[] tmp = musicPath.split("/");
        int tmpLen = tmp.length;
        int cnt = 0;
        for (;cnt < tmpLen - 1;cnt++) {
            directory += tmp[cnt];
        }

        title = tmp[cnt].split("\\.")[0];

    }
}
