package com.myapplicationdev.android.fyp;

import android.app.Activity;
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

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("sound", checkBoxSound.isChecked());
            editor.putBoolean("music", checkBoxMusic.isChecked());
            editor.commit();

            finish();
//            Intent i = new Intent(SettingPageActivity.this, MainActivity.class);
//            startActivity(i);
        });
    }
}