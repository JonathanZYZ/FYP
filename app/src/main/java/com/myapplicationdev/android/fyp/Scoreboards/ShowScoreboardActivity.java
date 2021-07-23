package com.myapplicationdev.android.fyp.Scoreboards;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Models.ScoreBoard;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

import java.util.ArrayList;

public class ShowScoreboardActivity extends AppCompatActivity {

    TextView tvHeading;
    Button btnFilter, btnShowAll;
    ListView lvScoreBoardData;
    ArrayList<ScoreBoard> al, filterAL;
    ArrayList<String> spinnerAL;
    ScoreboardAdapter aa;
    ArrayAdapter spinnerAA;
    Spinner spinner;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_show);
        setContentView(R.layout.activity_show_scoreboard);

        tvHeading = findViewById(R.id.tvHeading);
        btnShowAll = findViewById(R.id.btnShowAll);
        btnFilter = findViewById(R.id.btnFilter);
        lvScoreBoardData = findViewById(R.id.lvScoreBoardData);
        spinner = findViewById(R.id.spinner);

        DBHelper dbh = new DBHelper(ShowScoreboardActivity.this);
        al = dbh.getAllScoreBoard();
        filterAL = al;
        aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, al);
        lvScoreBoardData.setAdapter(aa);

        spinnerAL = new ArrayList<>();

        for (ScoreBoard i : al) {
            spinnerAL.add(i.getUsername());
        }
        spinnerAA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerAL);
        spinnerAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAA);


        btnShowAll.setOnClickListener(v -> {


            al = dbh.getAllScoreBoard();

            if (!al.isEmpty()) {
                filterAL.clear();
                filterAL.addAll(al);
                aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, filterAL);
                lvScoreBoardData.setAdapter(aa);
            } else {
                Toast.makeText(ShowScoreboardActivity.this,
                        "Because you haven't played the game yet, there will be no data displayed in the scoreboard.",
                        Toast.LENGTH_SHORT).show();
            }


        });


        btnFilter.setOnClickListener(v -> {
            tvHeading.setText("THE TOP SCORER");
            al = dbh.getAllScoreBoard();
            filterAL.clear();
            int dbLength = al.size();


            if (!al.isEmpty()) {


                if (dbLength > 1) {
                    //finds the highest value
                    String topScore = al.get(0).getScore();
                    for (ScoreBoard i : al) {
                        if (Integer.parseInt(i.getScore()) > Integer.parseInt(topScore)) {
                            topScore = i.getScore();
                            filterAL.add(i);
                        }
                    }
                    aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, filterAL);
                    lvScoreBoardData.setAdapter(aa);
                    Toast.makeText(ShowScoreboardActivity.this,
                            "Here is the user with the highest score out of all users.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ShowScoreboardActivity.this,
                            "There is currently only one user's data in the database, which means there are no other users to compare scores with.",
                            Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(ShowScoreboardActivity.this,
                        "Because you haven't played the game yet, there will be no data displayed in the scoreboard.",
                        Toast.LENGTH_SHORT).show();
            }


        });

        lvScoreBoardData.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(ShowScoreboardActivity.this, ModifyScoreboardActivity.class);
            i.putExtra("Scoreboard", filterAL.get(position));
            startActivity(i);
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                al = dbh.getAllScoreBoard();
                filterAL.clear();


                String selected = spinnerAL.get(position);
                for (ScoreBoard i : al) {
                    if (i.getUsername().equalsIgnoreCase(selected))
                        filterAL.add(i);
                }
                aa = new ScoreboardAdapter(ShowScoreboardActivity.this, R.layout.scoreboard_row, filterAL);
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
        DBHelper dbh = new DBHelper(ShowScoreboardActivity.this);
        al = dbh.getAllScoreBoard();
        aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, al);
        lvScoreBoardData.setAdapter(aa);

        spinnerAL = new ArrayList<>();
        for (ScoreBoard i : al) {
            spinnerAL.add(i.getUsername());
        }
        spinnerAA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerAL);
        spinnerAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAA);
    }
}