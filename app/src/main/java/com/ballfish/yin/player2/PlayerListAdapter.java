package com.ballfish.yin.player2;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ballfish.utility.FileChooser;
import com.ballfish.utility.TimeManager;

import java.util.ArrayList;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {
    private Activity activity;

    private ArrayList<Player> players;

    public PlayerListAdapter(Activity activity, ArrayList<Player> players) {
        this.activity = activity;
        this.players = players;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView playerTitle;
        TextView musicTitle;
        TextView content;
        SeekBar runController;
        TextView nowTime;
        TextView maxTime;
        ImageButton downButton;
        ImageButton chooseContent;
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
            chooseContent = (ImageButton) itemView.findViewById(R.id.choose_content);
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
        Player player = players.get(position);

        holder.playerTitle.setText(player.title);
        holder.musicTitle.setText(player.getNowMusic().title);

        if (player.file == null || !player.isFileShow) {
            holder.content.setVisibility(View.GONE);
        } else {
            holder.content.setVisibility(View.VISIBLE);
            holder.content.setText(player.file.content);
        }

        holder.runController.setOnSeekBarChangeListener(new RunControlListener(player));

        holder.nowTime.setText(TimeManager.milliSecondsToTimer(player.getMediaPlayer().getCurrentPosition()));
        holder.maxTime.setText(TimeManager.milliSecondsToTimer(player.nowMusicLength));

        holder.downButton.setOnClickListener(new DownButtonClick(player, holder.content));

        holder.chooseContent.setOnClickListener(new ChooseContentClick(activity, position));

        holder.listButton.setOnClickListener(new ListButtonClick(activity, players, position));

        holder.lastButton.setOnClickListener(new LastButtonClick(player, player.getNowMusicPosition()));
        holder.playButton.setOnClickListener(new PlayButtonClick(player));
        holder.nextButton.setOnClickListener(new NextButtonClick(player, player.getNowMusicPosition()));

        holder.modeButton.setOnClickListener(new ModeButtonClick(player));

        holder.volButton.setOnClickListener(new volButtonClick(holder.volController));
        holder.volController.setMax(player.getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        holder.volController.setProgress(player.getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        holder.volController.setOnSeekBarChangeListener(new volControlListener(player));

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class RunControlListener implements SeekBar.OnSeekBarChangeListener {
        Player player;

        public RunControlListener(Player player) {
            this.player = player;
        }

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
    }

    class DownButtonClick implements View.OnClickListener {
        Player player;
        TextView content;

        public DownButtonClick(Player player, TextView content) {
            this.player = player;
            this.content = content;
        }

        @Override
        public void onClick(View v) {
            if (player.isFileShow) {
                player.isFileShow = false;

                ImageButton b = (ImageButton) v;
                b.setImageResource(android.R.drawable.arrow_down_float);

                content.setVisibility(View.GONE);
            } else {
                player.isFileShow = true;

                ImageButton b = (ImageButton) v;
                b.setImageResource(android.R.drawable.arrow_up_float);

                content.setVisibility(View.VISIBLE);
            }
        }
    }

    class ChooseContentClick implements View.OnClickListener{
        Activity activity;
        int playerId;
        public ChooseContentClick(Activity activity, int playerId) {
            this.activity = activity;
            this.playerId = playerId;
        }

        @Override
        public void onClick(View v) {
            FileChooser fileChooser = new FileChooser(activity);
            if (!fileChooser.showFileChooser("text/plain", activity.getString(R.string.choose_file), new Integer(playerId))) {
                Toast.makeText(activity, R.string.none_file, Toast.LENGTH_SHORT).show();
            }
        }
        // TODO:add onActivityResult in mainActivity
    }

    class ListButtonClick implements View.OnClickListener{
        Activity activity;
        ArrayList<Player> players;
        int playerId;

        public ListButtonClick(Activity activity, ArrayList<Player> players, int playerId) {
            this.activity = activity;
            this.players = players;
            this.playerId = playerId;
        }

        @Override
        public void onClick(View v) {
            try {
                Intent intent = new Intent(activity, PlayListActivity.class);
                intent.putExtra("players", players);
                intent.putExtra("playerId", playerId);
                activity.startActivity(intent);
            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(activity, "Play List Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LastButtonClick implements View.OnClickListener {
        Player player;
        int musicPosition;
        public LastButtonClick(Player player, int musicPosition) {
            this.player = player;
            this.musicPosition = musicPosition;
        }

        @Override
        public void onClick(View v) {
            player.pause();
            player.setNowMusic(musicPosition - 1);
            player.play();
        }
    }

    class PlayButtonClick implements View.OnClickListener {
        Player player;

        public PlayButtonClick(Player player) {
            this.player = player;
        }

        @Override
        public void onClick(View v) {
            ImageButton button = (ImageButton) v;
            if (player.isPlay) {
                player.pause();

                button.setImageResource(android.R.drawable.ic_media_pause);
            } else {
                player.play();

                button.setImageResource(android.R.drawable.ic_media_play);
            }

        }
    }

    class NextButtonClick implements View.OnClickListener {
        Player player;
        int musicPosition;
        public NextButtonClick(Player player, int musicPosition) {
            this.player = player;
            this.musicPosition = musicPosition;
        }

        @Override
        public void onClick(View v) {
            player.pause();
            player.setNowMusic(musicPosition + 1);
            player.play();
        }
    }

    class ModeButtonClick implements  View.OnClickListener {
        Player player;
        public ModeButtonClick(Player player) {
            this.player = player;
        }

        @Override
        public void onClick(View v) {
            // TODO: add mode implement
            switch (player.mode) {
                case Player.Mode.PLAY_ONCE_ONE:
                    break;
                case Player.Mode.PLAY_ONCE_ALL:
                    break;
                case Player.Mode.REPLAY_ONE:
                    break;
                case Player.Mode.REPLAY_ALL:
                    break;
                case Player.Mode.CURSOR_PLAY_ONCE:
                    break;
                case Player.Mode.CIRSOR_REPLAY:
                    break;
            }

        }
    }

    class volButtonClick implements View.OnClickListener{
        SeekBar volController;

        public volButtonClick(SeekBar volController) {
            this.volController = volController;
        }

        @Override
        public void onClick(View v) {
            if (volController.getVisibility() == View.GONE) {
                volController.setVisibility(View.VISIBLE);
            } else {
                volController.setVisibility(View.GONE);
            }
        }
    }

    class volControlListener implements SeekBar.OnSeekBarChangeListener{
        Player player;

        public volControlListener(Player player) {
            this.player = player;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            player.getAudioManager().setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
