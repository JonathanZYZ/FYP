package com.myapplicationdev.android.fyp.Scoreboards;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Models.ScoreBoard;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

import java.util.ArrayList;

public class ScoreboardActivity extends AppCompatActivity {


    ArrayList<ScoreBoard> al;
    ListView lvScoreBoardData;
    ArrayAdapter<ScoreBoard> aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);


        lvScoreBoardData = this.findViewById(R.id.lvScoreBoardData);


        al = new ArrayList<>();
        aa = new ScoreboardAdapter(this, android.R.layout.simple_list_item_1, al);
        lvScoreBoardData.setAdapter(aa);

        DBHelper dbh = new DBHelper(ScoreboardActivity.this);
        al.clear();
        al.addAll(dbh.getAllScoreBoard());
        dbh.close();
    }
}