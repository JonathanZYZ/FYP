package com.myapplicationdev.android.fyp.Questions;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Model.QuestionIntermediate;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Util.DBHelper;

import java.util.ArrayList;

public class AdvancedQuestionsActivity extends AppCompatActivity {

    Button btnStart;
    TextView tvQuestionsNumber, tvScore;
    ImageView ivQuestion, ivChoice1, ivChoice2, ivChoice3, ivChoiceQn4;
    QuestionIntermediate currentQuestion;
    ArrayList<QuestionIntermediate> al;
    DBHelper dbh = new DBHelper(AdvancedQuestionsActivity.this);
    SharedPreferences sharedPreferences;
    int questionCounter, questionCountTotal;
    int ans1, ans2, ans3, ans4;
    int score, numOfAnsForQn1, numOfAnsForQn2;
    boolean answered;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_advanced);


        btnStart = findViewById(R.id.btnStart_intermediate);
        tvQuestionsNumber = findViewById(R.id.tvQuestionsNumber_Intermediate);
        tvScore = findViewById(R.id.tvScore_Intermediate);
        ivQuestion = findViewById(R.id.ivQuestions_Intermediate);
        ivChoice1 = findViewById(R.id.ivChoiceQn1);
        ivChoice2 = findViewById(R.id.ivChoiceQn2);
        ivChoice3 = findViewById(R.id.ivChoiceQn3);
        ivChoiceQn4 = findViewById(R.id.ivChoiceQn4);


    }
}