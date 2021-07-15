package com.myapplicationdev.android.fyp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingPageActivity extends AppCompatActivity {

    Button btnApply;
    CheckBox checkBoxSound, checkBoxMusic;
    SharedPreferences sharedPreferences;
    int sound,music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        btnApply = findViewById(R.id.btnApply);
        checkBoxSound = findViewById(R.id.checkBoxSound);
        checkBoxMusic = findViewById(R.id.checkBoxMusic);
       sharedPreferences = getSharedPreferences("audio",Context.MODE_PRIVATE);
       if (sharedPreferences.contains("sound") && sharedPreferences.contains("music")){
           music = sharedPreferences.getInt("music",0);
           sound = sharedPreferences.getInt("sound",0);
           if (music == 0){
               checkBoxMusic.setChecked(false);
           }else {
               checkBoxMusic.setChecked(true);
           }
           if (sound == 0){
               checkBoxSound.setChecked(false);
           }else {
               checkBoxSound.setChecked(true);
           }
       }else{
           checkBoxMusic.setChecked(true);
           checkBoxSound.setChecked(true);
       }

//        editor.putInt("music",1);
//        editor.putInt("sound",1);
//        editor.commit();




        btnApply.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if(checkBoxMusic.isChecked()){
                music = 1;
            }else{
                music = 0;
            }
            if(checkBoxSound.isChecked()){
                sound = 1;
            }else{
                sound = 0;
            }
            editor.putInt("music",music);
            editor.putInt("sound",sound);
            editor.commit();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("sound",sound);
            returnIntent.putExtra("music",music);
            setResult(RESULT_OK,returnIntent);
            Toast.makeText(SettingPageActivity.this,"Changes applied",Toast.LENGTH_SHORT).show();
//            Toast
            finish();
//            Intent i = new Intent(SettingPageActivity.this, MainActivity.class);
//            startActivity(i);
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        sharedPreferences = getSharedPreferences("audio",Context.MODE_PRIVATE);
//        music = sharedPreferences.getInt("music",0);
//        sound = sharedPreferences.getInt("sound",0);
//        if (music == 0){
//            checkBoxMusic.setChecked(false);
//        }else {
//            checkBoxMusic.setChecked(true);
//        }
//        if (sound == 0){
//            checkBoxSound.setChecked(false);
//        }else {
//            checkBoxSound.setChecked(true);
//        }
//    }
}