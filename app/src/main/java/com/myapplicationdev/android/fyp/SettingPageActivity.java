package com.myapplicationdev.android.fyp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class SettingPageActivity extends AppCompatActivity {

    Button btnApply;
    CheckBox checkBoxSound, checkBoxMusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        btnApply = findViewById(R.id.btnApply);
        checkBoxSound = findViewById(R.id.checkBoxSound);
        checkBoxMusic = findViewById(R.id.checkBoxMusic);

        checkBoxMusic.setChecked(AudioData.getInstance().isEnabledMusic());
        checkBoxSound.setChecked(AudioData.getInstance().isEnabledSound());

        btnApply.setOnClickListener(v -> {

            AudioData.getInstance().setEnabledSound(checkBoxSound.isChecked());
            AudioData.getInstance().setEnabledMusic(checkBoxMusic.isChecked());

            Intent i = new Intent(SettingPageActivity.this, MainActivity.class);
            startActivity(i);
        });
    }
}