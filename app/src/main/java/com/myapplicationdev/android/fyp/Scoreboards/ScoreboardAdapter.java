package com.myapplicationdev.android.fyp.Scoreboards;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.myapplicationdev.android.fyp.Models.ScoreBoard;
import com.myapplicationdev.android.fyp.R;

import java.util.ArrayList;


public class ScoreboardAdapter extends ArrayAdapter<ScoreBoard> {

    Context context;
    ArrayList<ScoreBoard> scoreBoards;
    int resource;
    TextView TextViewUserID, TextViewUsername, TextViewScore, TextViewMode, TextViewDatePlayed;

    public ScoreboardAdapter(Context context, int resource, ArrayList<ScoreBoard> scoreBoards) {
        super(context, resource, scoreBoards);
        this.context = context;
        this.scoreBoards = scoreBoards;
        this.resource = resource;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.scoreboard_row, parent, false);

        //Match the UI components with Java variables
        TextViewUserID = rowView.findViewById(R.id.TextViewUserID);
        TextViewUsername = rowView.findViewById(R.id.TextViewUsername);
        TextViewScore = rowView.findViewById(R.id.TextViewScore);
        TextViewMode = rowView.findViewById(R.id.TextViewMode);
        TextViewDatePlayed = rowView.findViewById(R.id.TextViewDatePlayed);

//        private int id;
//        private String username;
//        private String score;
//        private String date;

        ScoreBoard scoreBoard = scoreBoards.get(position);
        int id = scoreBoard.getId();
        String username = scoreBoard.getUsername();
        String score = scoreBoard.getScore();
        String mode = scoreBoard.getMode();
        String date = scoreBoard.getDate();

        TextViewUserID.setText(String.format("%d", id));
        TextViewUsername.setText(username);
        TextViewScore.setText(score);
        TextViewMode.setText(mode);
        TextViewDatePlayed.setText(date);

        return rowView;
    }


}


