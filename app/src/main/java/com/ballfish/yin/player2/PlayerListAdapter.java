package com.ballfish.yin.player2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {
    private String[] playerTitles;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public PlayerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
