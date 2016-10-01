package com.ballfish.yin.player2;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.SeekBar;

import com.ballfish.utility.FileInfo;

import java.util.ArrayList;

public class Player {
    public String title;

    public FileInfo file;
    public boolean isFileShow;

    public ArrayList<MusicInfo> list;
    public MusicInfo nowMusic;
    public boolean isPlay;
    public int nowMusicLength;

    private Context context;
    private MediaPlayer mediaPlayer;
    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }
    private AudioManager audioManager;

    private SeekBar runControlSeekBar;
    private SeekBar volControlSeekBar;
}
