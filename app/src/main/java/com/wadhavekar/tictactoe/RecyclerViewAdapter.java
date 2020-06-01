package com.wadhavekar.tictactoe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private ArrayList<MyScore> myScores;


    public RecyclerViewAdapter(ArrayList<MyScore> myScores) {
        this.myScores = myScores;

    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textView.setText(myScores.get(0).getPlayerName());
        holder.tvScore.setText("         "+myScores.get(0).getScore());
        holder.textView1.setText(myScores.get(1).getPlayerName());
        holder.tv_score1.setText("         "+myScores.get(1).getScore());
    }

    @Override
    public int getItemCount() {
        return myScores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,tvScore,textView1,tv_score1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_playerRow);
            tvScore = itemView.findViewById(R.id.tv_player_score);
            textView1 = itemView.findViewById(R.id.tv_playerRow1);
            tv_score1 = itemView.findViewById(R.id.tv_player_score1);
        }
    }
}
