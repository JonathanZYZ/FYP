package com.myapplicationdev.android.fyp.Questions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.DifficultySectionActivity;
import com.myapplicationdev.android.fyp.MainActivity;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Scoreboards.ShowScoreboardActivity;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ResultActivity extends AppCompatActivity {


    EditText editTextDialogUserName;

    TextView textViewHeading, textViewMyScore, textViewHighestScore, textViewDialogScore, textViewDialogDatePlayed;
    Button btnPlayAgain, btnQuitGame, btnScoreboard;
    int myScore;
    String username;
    SharedPreferences sharedPreferences;
    DBHelper dbh = new DBHelper(ResultActivity.this);
    MediaPlayer mediaPlayer;
    String date, difficulty;


    @SuppressLint({"SetTextI18n", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewHeading = findViewById(R.id.textViewHeading);
        textViewMyScore = findViewById(R.id.textViewMyScore);
        textViewHighestScore = findViewById(R.id.textViewHighestScore);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnQuitGame = findViewById(R.id.btnQuitGame);
        btnScoreboard = findViewById(R.id.btnScoreBoard);

        // TODO: the total score obtained from all activities completed
        myScore = getIntent().getIntExtra("score", 0);
        String score = String.valueOf(myScore);
        username = getIntent().getStringExtra("username");
        difficulty = getIntent().getStringExtra("difficulty");

        // TODO: defining the time for the user's playing date
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        //@SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        TimeZone tz = TimeZone.getTimeZone("SGT");
//        sdf.setTimeZone(tz);
//        date = sdf.format(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        TimeZone tz = TimeZone.getTimeZone("Asia/Singapore");
        sdf.setTimeZone(tz);

        Date date = new java.util.Date();
        // Timestamp local = new Timestamp(date.getTime());
        String strDate = sdf.format(date);

        dbh = new DBHelper(ResultActivity.this);

        // TODO:  adding data into SQlite DB
        long inserted_id = dbh.insertScoreBoard(username, score, strDate);

        dbh.close();

        if (inserted_id != -1) {
            Toast.makeText(ResultActivity.this, "Insert successful",
                    Toast.LENGTH_SHORT).show();
        }

        textViewMyScore.setText("Your Score : " + myScore);

        textViewHeading.setText("Good Job!");
        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("highestScore", 0);


        if (myScore >= highestScore) {
            sharedPreferences.edit().putInt("highestScore", myScore).apply();
            textViewHighestScore.setText("Highest Score : " + myScore);
            textViewHeading.setText("Congratulations. The new high score. Do you want to get better scores?");
        } else {

            textViewHighestScore.setText("Highest Score : " + highestScore);
            if ((highestScore - myScore) > 10) {
                textViewHeading.setText(username);
            }
            if ((highestScore - myScore) > 3 && (highestScore - myScore) <= 10) {
                textViewHeading.setText(username);
            }
            if ((highestScore - myScore) <= 3) {
                textViewHeading.setText(username);
            }
        }


//        UserInputDialog();

        btnPlayAgain.setOnClickListener(v -> {
//            String difficulty = getIntent().getStringExtra("difficulty");
            Intent intent = new Intent();
            if (difficulty.equalsIgnoreCase("easy")) {
                intent = new Intent(ResultActivity.this, EasyQuestionsActivity.class);
            } else if (difficulty.equalsIgnoreCase("intermediate")) {
                intent = new Intent(ResultActivity.this, IntermediateQuestionsActivity.class);
            } else if (difficulty.equalsIgnoreCase("advanced")) {
                intent = new Intent(ResultActivity.this, AdvancedQuestionsActivity.class);
            }
            //Intent intent = new Intent(ResultActivity.this, DifficultySectionActivity.class);
            startActivity(intent);
            finish();


        });
        btnScoreboard.setOnClickListener(view -> {
            Intent intent = new Intent(ResultActivity.this, ShowScoreboardActivity.class);
            startActivity(intent);
            finish();
        });

        btnQuitGame.setOnClickListener(v -> {
            // TODO: after users quits the game after playing , they would lead to the DifficultySectionActivity Activity
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });


    }

    /* Initialize popup dialog view and ui controls in the popup dialog. */

//    public void UserInputDialog(){
//        Dialog MyDialog = new Dialog(this);
//        // Set title, icon, can not cancel properties.
//        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        // Set the inflated layout view object to the AlertDialog builder.
//        MyDialog.setContentView(R.layout.popup_input_name_dialog);
//
//
//        EditText dialogUserName = MyDialog.findViewById(R.id.dialogUserName);
//        TextView dialogScore = MyDialog.findViewById(R.id.dialogScore);
//        TextView dialogDatePlayed = MyDialog.findViewById(R.id.dialogDatePlayed);
//        Button cancelUserData = MyDialog.findViewById(R.id.buttonCancelUserData);
//        Button saveUserData = MyDialog.findViewById(R.id.buttonSaveUserData);


//        dialogScore.setText(myScore);
//        dialogDatePlayed.setText(date);
//
//        saveUserData.setOnClickListener(v1 -> {
//
//            // Getting data
//            String username = dialogUserName.getText().toString();
//            String score = dialogScore.getText().toString();
//            String playedDate = dialogDatePlayed.getText().toString();
//
//
//            // TODO:  adding data into SQlite DB
//            DBHelper dbh = new DBHelper(ResultActivity.this);
//
//
//            long inserted_id = dbh.insertScoreBoard(username, score, playedDate);
//
//            dbh.close();
//
//            if (inserted_id != -1) {
//                Toast.makeText(ResultActivity.this, "Insert successful",
//                        Toast.LENGTH_SHORT).show();
//            }
//
//        });
//        cancelUserData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MyDialog.dismiss();
//                Intent i = new Intent(ResultActivity.this, ShowScoreboardActivity.class);
//                startActivity(i);
//            }
//        });
//
//        MyDialog.show();
}
