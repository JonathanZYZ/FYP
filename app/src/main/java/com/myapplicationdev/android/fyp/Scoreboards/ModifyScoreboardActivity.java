package com.myapplicationdev.android.fyp.Scoreboards;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Models.ScoreBoard;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

public class ModifyScoreboardActivity extends AppCompatActivity {


    TextView tvID, tvScore, tvPlayedDate, tvPlayedMode;
    EditText etUsername;
    Button btnUpdate, btnDelete, btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.myapplicationdev.android.fyp.R.layout.activity_modify_scoreboard);


        Intent i = getIntent();
        ScoreBoard scoreBoard = (ScoreBoard) i.getSerializableExtra("Scoreboard");
        DBHelper dbh = new DBHelper(ModifyScoreboardActivity.this);


        tvID = findViewById(R.id.tvID);
        tvScore = findViewById(R.id.tvScore);
        tvPlayedDate = findViewById(R.id.tvPlayedDate);
        tvPlayedMode = findViewById(R.id.tvPlayedMode);
        etUsername = findViewById(R.id.etUsername);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        tvID.setText(String.valueOf(scoreBoard.getId()));
        tvScore.setText(scoreBoard.getScore());
        tvPlayedMode.setText(scoreBoard.getMode());
        tvPlayedDate.setText(scoreBoard.getDate());
        etUsername.setText(scoreBoard.getUsername());

        btnUpdate.setOnClickListener(v -> {

            int result = dbh.updateScoreBoard(new ScoreBoard(
                    //scoreBoard.getId(),
                    etUsername.getText().toString(),
                    scoreBoard.getScore(),
                    scoreBoard.getMode(),
                    scoreBoard.getDate()
            ));


            Toast.makeText(ModifyScoreboardActivity.this, "This username has been successfully updated by you.",
                    Toast.LENGTH_SHORT).show();
            this.finish();
        });

        btnDelete.setOnClickListener(v -> {
            int result = dbh.deleteScoreBoard(scoreBoard.getId());
            Toast.makeText(ModifyScoreboardActivity.this, "This data has been successfully deleted by you.",
                    Toast.LENGTH_SHORT).show();
            this.finish();

        });

        btnCancel.setOnClickListener(v -> ModifyScoreboardActivity.this.finish());
    }

}
