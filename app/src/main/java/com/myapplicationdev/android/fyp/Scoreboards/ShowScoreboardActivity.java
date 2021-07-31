package com.myapplicationdev.android.fyp.Scoreboards;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.myapplicationdev.android.fyp.Models.ScoreBoard;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

import java.io.File;
import java.util.ArrayList;

public class ShowScoreboardActivity<SqliteToExcel> extends AppCompatActivity {

    TextView tvHeading;
    Button btnFilter, btnShowAll, btnExcel;
    ListView lvScoreBoardData;
    ArrayList<ScoreBoard> al, filterAL;
    ArrayList<String> spinnerUsernameAL, spinnerModeAL;
    ScoreboardAdapter aa;
    ArrayAdapter spinnerUsernameAA, spinnerModeAA;
    Spinner spUsername, spMode;
    String folderLocation = Environment.getExternalStorageDirectory().getPath() + "/Backup/";
    DBHelper dbh;
    File folder;
    SqliteToExcel sqliteToExcel;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_show);
        setContentView(R.layout.activity_show_scoreboard);

        // TODO: At the start of the ShowScoreboardActivity initialize() method,
        //  initialize all UI variables and other objects.
        tvHeading = findViewById(R.id.tvHeading);
        btnShowAll = findViewById(R.id.btnShowAll);
        btnFilter = findViewById(R.id.btnFilter);
        btnExcel = findViewById(R.id.btnExcel);
        lvScoreBoardData = findViewById(R.id.lvScoreBoardData);
        spUsername = findViewById(R.id.spUsername);
        spMode = findViewById(R.id.spMode);


        // TODO: setting of SQLite to Excel ...
        folder = new File(folderLocation);
        /*================================================================================== */


        btnShowAll.setOnClickListener(v -> {
            tvHeading.setText("ScoreBoard (Total Users)");
            al = dbh.getAllScoreBoard();
            if (!al.isEmpty()) {

                filterAL.clear();
                filterAL.addAll(al);
                aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, filterAL);
                lvScoreBoardData.setAdapter(aa);

                Toast.makeText(ShowScoreboardActivity.this,
                        "The total number of users who have played the game is shown above.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ShowScoreboardActivity.this,
                        "Because you haven't played the game yet, " +
                                "there will be no data displayed in the scoreboard.",
                        Toast.LENGTH_SHORT).show();
            }

            aa.notifyDataSetChanged();
        });


        btnFilter.setOnClickListener(view -> {

            tvHeading.setText("ScoreBoard (The Top Scorer)");
            al = dbh.getAllScoreBoard();
            filterAL.clear();
            int dbLength = al.size();


            if (!al.isEmpty()) {


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
                        "Because you haven't played the game yet," +
                                " there will be no data displayed in the scoreboard.",
                        Toast.LENGTH_SHORT).show();
            }

            aa.notifyDataSetChanged();
        });

        lvScoreBoardData.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(ShowScoreboardActivity.this, ModifyScoreboardActivity.class);
            i.putExtra("Scoreboard", filterAL.get(position));
            startActivity(i);
        });

        spUsername.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                al = dbh.getAllScoreBoard();
                filterAL.clear();


                String selected = spinnerUsernameAL.get(position);
                for (ScoreBoard i : al) {
                    if (i.getUsername().equalsIgnoreCase(selected)) {
                        filterAL.add(i);
                    }


                    String tvHeadingOutput = String.format("ScoreBoard (User: %s)", selected);
                    tvHeading.setText(tvHeadingOutput);
                }
                aa = new ScoreboardAdapter(ShowScoreboardActivity.this, R.layout.scoreboard_row, filterAL);
                lvScoreBoardData.setAdapter(aa);
                aa.notifyDataSetChanged();

                Toast.makeText(ShowScoreboardActivity.this,
                        "Here are the stats for user " + selected + " in the game.",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                aa.notifyDataSetChanged();
            }
        });

        spMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                al = dbh.getAllScoreBoard();
                filterAL.clear();


                String selected = spinnerModeAL.get(position);

                for (ScoreBoard i : al) {
                    if (i.getMode().equalsIgnoreCase(selected)) {
                        filterAL.add(i);
                    }

                    String tvHeadingOutput = String.format("ScoreBoard (%s level)", selected);
                    tvHeading.setText(tvHeadingOutput);

                }
                aa = new ScoreboardAdapter(ShowScoreboardActivity.this, R.layout.scoreboard_row, filterAL);
                lvScoreBoardData.setAdapter(aa);
                aa.notifyDataSetChanged();

                Toast.makeText(ShowScoreboardActivity.this,
                        "Here are all of the " + selected + " mode users.",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                aa.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // TODO: Sets the data for ListView
        dbh = new DBHelper(ShowScoreboardActivity.this);
        al = dbh.getAllScoreBoard();
        filterAL = al;
        aa = new ScoreboardAdapter(this, R.layout.scoreboard_row, al);
        lvScoreBoardData.setAdapter(aa);


        // TODO: Sets the SpinnerAdapter used to provide the data which backs spinnerUsernameAL.
        spinnerUsernameAL = new ArrayList<>();

        for (ScoreBoard i : al) {
            spinnerUsernameAL.add(i.getUsername());
        }
        spinnerUsernameAA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerUsernameAL);
        spinnerUsernameAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUsername.setAdapter(spinnerUsernameAA);

        // TODO: Sets the SpinnerAdapter used to provide the data which backs spinnerModeAA.
        spinnerModeAL = new ArrayList<>();
        spinnerModeAL.add("basic");
        spinnerModeAL.add("intermediate");
        spinnerModeAL.add("advanced");

        spinnerModeAA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerModeAL);
        spinnerModeAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMode.setAdapter(spinnerModeAA);

    }


}