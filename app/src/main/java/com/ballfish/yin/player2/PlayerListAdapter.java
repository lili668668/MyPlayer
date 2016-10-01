package com.ballfish.yin.player2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ballfish.utility.TimeManager;

import java.util.ArrayList;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {
    private static Context context;

    private ArrayList<Player> players;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView playerTitle;
        TextView musicTitle;
        TextView content;
        SeekBar runController;
        TextView nowTime;
        TextView maxTime;
        ImageButton downButton;
        ImageButton listButton;
        ImageButton lastButton;
        ImageButton playButton;
        ImageButton nextButton;
        ImageButton modeButton;
        ImageButton volButton;
        SeekBar volController;

        public ViewHolder(View itemView) {
            super(itemView);

            playerTitle = (TextView) itemView.findViewById(R.id.player_title);
            musicTitle = (TextView) itemView.findViewById(R.id.music_title);
            content = (TextView) itemView.findViewById(R.id.content);
            runController = (SeekBar) itemView.findViewById(R.id.run_controller);
            nowTime = (TextView) itemView.findViewById(R.id.now_time);
            maxTime = (TextView) itemView.findViewById(R.id.max_time);
            downButton = (ImageButton) itemView.findViewById(R.id.down_button);
            listButton = (ImageButton) itemView.findViewById(R.id.list_button);
            lastButton = (ImageButton) itemView.findViewById(R.id.last_button);
            playButton = (ImageButton) itemView.findViewById(R.id.play_button);
            nextButton = (ImageButton) itemView.findViewById(R.id.next_button);
            modeButton = (ImageButton) itemView.findViewById(R.id.mode_button);
            volButton = (ImageButton) itemView.findViewById(R.id.vol_button);
            volController = (SeekBar) itemView.findViewById(R.id.vol_controller);

        }
    }

    @Override
    public PlayerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Player player = players.get(position);

        holder.playerTitle.setText(player.title);
        holder.musicTitle.setText(player.nowMusic.title);

        if (player.file == null) {
            holder.content.setVisibility(View.GONE);
        } else {
            holder.content.setVisibility(View.VISIBLE);
            holder.content.setText(player.file.content);
        }

        holder.runController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int position = seekBar.getProgress();
                int where = player.nowMusicLength * position / seekBar.getMax();
                player.getMediaPlayer().seekTo(where);
            }
        });

        holder.nowTime.setText(TimeManager.milliSecondsToTimer(player.getMediaPlayer().getCurrentPosition()));
        holder.maxTime.setText(TimeManager.milliSecondsToTimer(player.nowMusicLength));

        holder.downButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (player.isPlay) {
                    player.isPlay = false;

                    ImageButton b = (ImageButton) v;
                    b.setImageResource(android.R.drawable.ic_media_pause);
                } else {
                    player.isPlay = true;

                    ImageButton b = (ImageButton) v;
                    b.setImageResource(android.R.drawable.ic_media_play);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
