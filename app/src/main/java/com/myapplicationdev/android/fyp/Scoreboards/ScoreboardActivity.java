package com.myapplicationdev.android.fyp.Scoreboards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Models.ScoreBoard;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

import java.util.ArrayList;

public class ScoreboardActivity extends AppCompatActivity {

    Button btnShowAll, btnFilter;
    Spinner spinner;
    ArrayList<ScoreBoard> al, filterAl;
    ArrayList<String> spinnerAl;
    ListView lvScoreBoardData;
    ArrayAdapter<ScoreBoard> aa;
    ArrayAdapter spinnerAa;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        btnShowAll = findViewById(R.id.btnShowAll);
        btnFilter = findViewById(R.id.btnFilter);
        spinner = findViewById(R.id.spinner);
        lvScoreBoardData = findViewById(R.id.lvScoreBoardData);
        dbh = new DBHelper(ScoreboardActivity.this);
        al = new ArrayList<>();


        al = dbh.getAllScoreBoard();


        filterAl = al;

        aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, al);
        lvScoreBoardData.setAdapter(aa);

        spinnerAl = new ArrayList<>();

        for (ScoreBoard i : al) {
            spinnerAl.add(i.getUsername());
        }
        spinnerAa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerAl);
        spinnerAa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAa);

        btnShowAll.setOnClickListener(v -> {
            al = dbh.getAllScoreBoard();
            filterAl.clear();
            filterAl.addAll(al);
            aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, filterAl);
            lvScoreBoardData.setAdapter(aa);
        });

        btnFilter.setOnClickListener(v -> {
            al = dbh.getAllScoreBoard();
            filterAl.clear();
            //   Integer.parseInt("9")
            int curScore = 0;

            for (ScoreBoard i : al) {
// Integer.parseInt(i.getScore())
                if (Integer.parseInt(i.getScore()) > curScore) {
                    filterAl.add(i);
                }
            }
            aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, filterAl);
            lvScoreBoardData.setAdapter(aa);
        });

        lvScoreBoardData.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(ScoreboardActivity.this, ModifyScoreboardActivity.class);
            i.putExtra("scoreboard", filterAl.get(position));
            startActivity(i);
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                al = dbh.getAllScoreBoard();
                String selected = spinnerAl.get(position);
                spinnerAl.clear();
                for (ScoreBoard i : al) {
                    if (i.getUsername().equalsIgnoreCase(selected))
                        filterAl.add(i);
                }
                aa = new ScoreboardAdapter(ScoreboardActivity.this, R.layout.scoreboard_row, filterAl);
                lvScoreBoardData.setAdapter(aa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        lvScoreBoardData = findViewById(R.id.lvScoreBoardData);
        spinner = findViewById(R.id.spinner);
        DBHelper dbh = new DBHelper(ScoreboardActivity.this);
        al = dbh.getAllScoreBoard();
        aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, al);
        lvScoreBoardData.setAdapter(aa);

        spinnerAl = new ArrayList<>();
        for (ScoreBoard i : al) {
            spinnerAl.add(i.getUsername());
        }
        spinnerAa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerAl);
        spinnerAa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAa);
    }
}