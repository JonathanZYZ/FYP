package com.myapplicationdev.android.fyp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnRevision, btnHowToPlay, btnSettings, btnLogOut;
    MediaPlayer mediaPlayer;
    // Todo: Firebase objects
    FirebaseAuth myFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.Play);
        btnHowToPlay = findViewById(R.id.btnHowToPlay);
        btnRevision = findViewById(R.id.btnRevisionSection);
        btnSettings = findViewById(R.id.btnSettings);
        btnLogOut = findViewById(R.id.btnLogOut);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.mouse_click);
        myFirebaseAuth = FirebaseAuth.getInstance();
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

        btnLogOut.setOnClickListener(view -> {
            myFirebaseAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            mediaPlayer.start();
            Toast.makeText(MainActivity.this, "You successfully signed off...", Toast.LENGTH_SHORT).show();
        });

    }
}