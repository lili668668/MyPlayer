package com.ballfish.yin.player2;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.SeekBar;

import com.ballfish.utility.FileInfo;

import java.util.ArrayList;

public class Player {
    public Player (String title, Context context) {
        this.title = title;
        this.context = context;
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public String title;

    public FileInfo file;
    public boolean isFileShow;

    public ArrayList<MusicInfo> list;
    public boolean isPlay;
    public int nowMusicLength;
    public int mode;

    private Context context;
    private MediaPlayer mediaPlayer;
    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }
    private AudioManager audioManager;
    public AudioManager getAudioManager() {
        return this.audioManager;
    }

    private MusicInfo nowMusic;
    public int getNowMusicPosition() {
        return list.indexOf(nowMusic);
    }
    public MusicInfo getNowMusic() {
        return getNowMusic();
    }
    public void setNowMusic(int position) {
        nowMusic = list.get(position);
    }

    public void play() {

    }

    public void pause() {

    }

    public static class Mode {
        public static final int PLAY_ONCE_ONE = 0;
        public static final int PLAY_ONCE_ALL = 1;
        public static final int REPLAY_ONE = 2;
        public static final int REPLAY_ALL = 3;
        public static final int CURSOR_PLAY_ONCE = 4;
        public static final int CIRSOR_REPLAY = 5;
    }
}
