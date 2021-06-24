package com.myapplicationdev.android.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

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

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxSound.isChecked()){
                    //store in database
                }
                if(checkBoxMusic.isChecked()){
                    //store in database
                }
            }
        });
    }
}