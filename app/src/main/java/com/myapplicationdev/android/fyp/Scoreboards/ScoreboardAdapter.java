package com.myapplicationdev.android.fyp.Scoreboards;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.myapplicationdev.android.fyp.Model.ScoreBoard;
import java.util.ArrayList;


public class ScoreboardAdapter extends ArrayAdapter<ScoreBoard> {


    Context context;
    ArrayList<ScoreBoard> scoreBoards;
    int resource;
    TextView TextViewUserID, TextViewUsername, TextViewScore, TextViewDatePlayed;

    public ScoreboardAdapter(Context context, int resource, ArrayList<ScoreBoard> scoreBoards) {
        super(context, resource, scoreBoards);
        this.context = context;
        this.scoreBoards = scoreBoards;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.songrows, parent, false)
        return rowView;
    }


}


