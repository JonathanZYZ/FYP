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
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

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

//  TODO: When the user clicks the "did you know?" icon on the screen, text messages are prepared for them.
        String didYouKnowIconMsgQ1 = "Tamoxifen's main skeleton is a tertiary alcohol. The starting material is treated with sulfuric acid, and acid catalyzed dehydration takes place. Alcohol group protonation makes it a good leaving group, and E1 reactions occur when a tertiary carbocation is formed prior to Tamoxifen formation (Z isomer).\n" +
                "\n" +
                "Tamoxifen is an estrogen receptor modulator (SERM).\n" +
                "\n" +
                "Tamoxifen is a widely used drug in the treatment of breast cancer (ER positive breast cancer).";

        String didYouKnowIconMsgQ2 = "Newer muscle relaxants, such as atracurium, have a built-in functional group that allows the drug to be rapidly metabolised or changed into an inactive form via the E2 reaction of the drug's quaternary ammonium group.\n" +
                "\n" +
                "In the presence of normal blood pH and temperature, spontaneous degradation occurs. There are no nucleophiles present.";
        String didYouKnowIconMsgQ3 = "The E1 reaction is involved in the biosynthesis of geraniol in roses.\n" +
                "A carbocation serves as the starting material.\n" +
                "\n" +
                "Geranyl diphosphate can be synthesized using a strong base and a protic solvent.\n" +
                "\n" +
                "I've lost a CH3, and I'm not sure where the OPP is coming from.";
        String didYouKnowIconMsgQ4 = "There is no hint for this question.";
        String didYouKnowIconMsgQ5 = "There is no hint for this question.";
        String didYouKnowIconMsgQ6 = "There is no hint for this question.";
        String didYouKnowIconMsgQ7 = "There is no hint for this question.";
        String didYouKnowIconMsgQ8 = "There is no hint for this question.";
        String didYouKnowIconMsgQ9 = "There is no hint for this question.";




    }
}