package com.myapplicationdev.android.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Questions_answer extends AppCompatActivity {

    ImageView ivOption1, ivOption2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_answer);

        ivOption1 = findViewById(R.id.ivOption1);
        ivOption2 = findViewById(R.id.ivOption2);

        ivOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                Intent i = new Intent(Questions_answer.this,Question_Correct.class);
                i.putExtra("mode","correct");
                startActivity(i);
            }
        });

        ivOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                Intent i = new Intent(Questions_answer.this,Question_Incorrect.class);
                i.putExtra("mode","Incorrect");
                startActivity(i);
            }
        });
    }
}