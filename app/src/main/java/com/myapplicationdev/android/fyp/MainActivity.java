package com.myapplicationdev.android.fyp;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Scoreboards.ScoreboardActivity;


public class MainActivity extends AppCompatActivity {
    Button btnStart, btnRevision, btnHowToPlay, btnSettings, btnLeaderBoard;
    MediaPlayer mediaPlayer,backgroundMusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.Play);
        btnHowToPlay = findViewById(R.id.btnHowToPlay);
        btnRevision = findViewById(R.id.btnRevisionSection);
        btnSettings = findViewById(R.id.btnSettings);
        btnLeaderBoard = findViewById(R.id.btnLeaderBoard);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.mouse_click);
        backgroundMusic = MediaPlayer.create(MainActivity.this,R.raw.background_music);

        btnStart.setOnClickListener(view -> {
            //    Log.d("test", "test");
            Intent i = new Intent(MainActivity.this, DifficultySectionActivity.class);
            startActivity(i);
            if(AudioData.getInstance().isEnabledSound()){
                Log.i("sound: ","play");
                mediaPlayer.start();
            }
        });

        btnHowToPlay.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, HowToPlayActivity.class);
            startActivity(i);
            if(AudioData.getInstance().isEnabledSound()){
                Log.i("sound: ","play");
                mediaPlayer.start();
            }
        });

        btnRevision.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, RevisionSectionActivity.class);
            startActivity(i);
            if(AudioData.getInstance().isEnabledSound()){
                Log.i("sound: ","play");
                mediaPlayer.start();
            }
        });

        btnSettings.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, SettingPageActivity.class);
            startActivity(i);
            if(AudioData.getInstance().isEnabledSound()){
                Log.i("sound: ","play");
                mediaPlayer.start();
            }
        });


        btnLeaderBoard.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, ScoreboardActivity.class);
            startActivity(i);
            if(AudioData.getInstance().isEnabledSound()){
                Log.i("sound: ","play");
                mediaPlayer.start();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("JSON Results: ",AudioData.getInstance().isEnabledMusic() + "");
        if(AudioData.getInstance().isEnabledMusic()){
            Log.i("JSON Results: ","play");
            backgroundMusic.start();
            backgroundMusic.setLooping(true);
        } else {
            Log.i("JSON Results: ","stop");
            backgroundMusic = new MediaPlayer();
            backgroundMusic.start();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backgroundMusic.stop();
       AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
       alertDialog.setTitle("Are you sure you want to exit?");
       alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               backgroundMusic.stop();
               finish();

           }
       });
       alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               dialogInterface.dismiss();
           }
       });
        AlertDialog myDialog = alertDialog.create();
        myDialog.show();
    }

}
