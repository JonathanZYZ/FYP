package com.myapplicationdev.android.fyp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnRevision, btnHowToPlay, btnSettings, btnLogOut;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.Play);
        btnHowToPlay = findViewById(R.id.btnHowToPlay);
        btnRevision = findViewById(R.id.btnRevisionSection);
        btnSettings = findViewById(R.id.btnSettings);
//        btnLogOut = findViewById(R.id.btnLogOut);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.mouse_click);

        btnStart.setOnClickListener(view -> {
        //    Log.d("test", "test");
            Intent i = new Intent(MainActivity.this, DifficultySectionActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnHowToPlay.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, HowToPlayActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnRevision.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, RevisionSectionActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });

        btnSettings.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, SettingPageActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });


    }
}