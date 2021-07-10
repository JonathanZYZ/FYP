package com.myapplicationdev.android.fyp.Questions;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.MainActivity;
import com.myapplicationdev.android.fyp.Models.QuestionEasy;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

import java.util.ArrayList;

public class EasyQuestionsActivity extends AppCompatActivity {
    Button btnStart;
    TextView tvQuestionsNumber, tvScore;
    RadioGroup group;
    RadioButton rdReaction_Option1, rdReaction_Option2;
    ImageView ivQuestion, btnExit, ivChoiceBasicQn,ivHints;
    ArrayList<QuestionEasy> al;
    QuestionEasy currentQuestion;
    DBHelper dbh = new DBHelper(EasyQuestionsActivity.this);
    String mode;
    SharedPreferences sharedPreferences;
    EditText editText;
    int ans;
    int score = 0;
    int streak;
    int questionCounter, questionCountTotal;
    boolean answered;
    String[] hints;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        btnStart = findViewById(R.id.btnStart);
        tvQuestionsNumber = findViewById(R.id.tvQuestions_Number);
        tvScore = findViewById(R.id.tvPoint);
//        group = findViewById(R.id.radioGroupBasic);
//        rdReaction_Option1 = findViewById(R.id.radioButtonOption1);
//        rdReaction_Option2 = findViewById(R.id.radioButtonOption2);
        ivChoiceBasicQn = findViewById(R.id.ivChoiceBasicQn);
        ivQuestion = findViewById(R.id.ivQuestions);
        ivHints = findViewById(R.id.ivHints);
        btnExit = findViewById(R.id.btnExit);
        hints = new String[]{"Starting material has less steric hinderance.","Starting material has less steric hinderance.","Starting material with high steric hindrance inhibits nucleophilic attack.","Starting material with high steric hindrance inhibits nucleophilic attack.","Starting material with high steric hindrance inhibits nucleophilic attack.","Starting material has less\n" +
                " steric hinderance.\n","Starting material has less\n" +
                " steric hinderance.\n","Undergoes either SN1 or SN2 depending on the Nucleophile and Solvent. ","Undergoes either SN1 or SN2 depending on the Nucleophile and Solvent. "};
        al = new ArrayList<>();
        al.add(new QuestionEasy("basic", "1", R.drawable.question1_basic, R.drawable.question1_basic_incorrect, R.drawable.question1_basic_correct, 2));
        al.add(new QuestionEasy("basic", "2", R.drawable.question2_basic, R.drawable.question2_basic_incorrect, R.drawable.question2_basic_correct, 2));
        al.add(new QuestionEasy("basic", "3", R.drawable.question3_basic, R.drawable.question3_basic_correct, R.drawable.question3_basic_incorrect, 1));
        al.add(new QuestionEasy("basic", "4", R.drawable.question4_basic, R.drawable.question4_basic_correct, R.drawable.question4_basic_incorrect, 1));
        al.add(new QuestionEasy("basic", "5", R.drawable.question5_basic, R.drawable.question5_basic_correct, R.drawable.question5_basic_incorrect, 1));
        al.add(new QuestionEasy("basic", "6", R.drawable.question6_basic, R.drawable.question6_basic_incorrect, R.drawable.question6_basic_correct, 2));
        al.add(new QuestionEasy("basic", "7", R.drawable.question7_basic, R.drawable.question7_basic_incorrect, R.drawable.question7_basic_correct, 2));
        al.add(new QuestionEasy("basic", "8", R.drawable.question8_basic, R.drawable.question8_basic_incorrect, R.drawable.question8_basic_correct, 2));
        al.add(new QuestionEasy("basic", "9", R.drawable.question9_basic, R.drawable.question9_basic_correct, R.drawable.question9_basic_incorrect, 1));

        questionCountTotal = al.size();
        btnStart.setText("Confirm");

        showNextQuestion();

        btnStart.setOnClickListener(view -> {
            //TODO
//            Intent i = new Intent(QuestionsActivity.this, QuestionsAnswerActivity.class);
//            i.putExtra("question","Question 1");
//            startActivity(i);

            if (!answered) {
                checkAnswer();
//                if (rdReaction_Option1.isChecked() || rdReaction_Option2.isChecked()) {
//                    checkAnswer();
//                } else {
//                    Toast.makeText(QuestionsActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
//                }
            } else {
                showNextQuestion();
            }
        });

        btnExit.setOnClickListener(view -> {


            AlertDialog.Builder exitScreen = new AlertDialog.Builder(EasyQuestionsActivity.this);
//                exitScreen.setTitle("");
            exitScreen.setMessage("Are you sure you want to quit? \n Here is your final score: " + score);
            exitScreen.setCancelable(false);


            exitScreen.setPositiveButton("Yes", (dialogInterface, i) -> {

                Intent basicMode_to_main = new Intent(EasyQuestionsActivity.this, MainActivity.class);
//                        basicMode_to_main.putExtra("question","Question 1");
                startActivity(basicMode_to_main);
            });

            exitScreen.setNeutralButton("No", null);
            AlertDialog ShowDialogExit = exitScreen.create();
            ShowDialogExit.show();
        });
        ivHints.setOnClickListener(view -> {
            AlertDialog.Builder hintDialog = new AlertDialog.Builder(EasyQuestionsActivity.this);
            hintDialog.setTitle("Hint #" + questionCounter);
            hintDialog.setMessage(hints[questionCounter - 1]);
            hintDialog.setCancelable(true);
            hintDialog.show();
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer() {
        answered = true;

//        RadioButton rbSelected = findViewById(group.getCheckedRadioButtonId());
//        int answer_number = group.indexOfChild(rbSelected) + 1;
        if (questionCounter < questionCountTotal) {
            if (ans == 0) {
                answered = false;
                //wrongSound.start();
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EasyQuestionsActivity.this);
                myBuilder.setTitle("No Input!");
                myBuilder.setMessage("Click on the question mark to input your answer");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Input Answer", (dialogInterface, i) -> dialogInterface.dismiss());

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            } else {
                if (ans == currentQuestion.getAnswerNum()) {
                    streak += 1;
                    score++;
                    if (streak == 5) {
                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(EasyQuestionsActivity.this);
                        myBuilder.setTitle("Congratulations!");
                        myBuilder.setMessage("You have answered 5 questions correctly in a row! We would like to test you further by bringing you to the intermediate Level! GoodLuck!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Proceed to Intermediate Mode", (dialogInterface, i) -> {
                            Intent intent = new Intent(EasyQuestionsActivity.this, IntermediateQuestionsActivity.class);
                            intent.putExtra("score", score);
                            intent.putExtra("questionNum", questionCounter + 1);
                            startActivity(intent);
                        });

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else {
                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(EasyQuestionsActivity.this);
                        myBuilder.setTitle("Congratulations!");
                        myBuilder.setMessage("You selected the correct answer!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Next Question", (dialogInterface, i) -> showNextQuestion());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();


                        tvScore.setText("Score: " + score);
                    }


                } else {
                    streak = 0;
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(EasyQuestionsActivity.this);
                    myBuilder.setTitle("Sorry!");
                    myBuilder.setMessage("You selected the wrong answer!");
                    myBuilder.setCancelable(false);
                    myBuilder.setPositiveButton("Next Question", (dialogInterface, i) -> showNextQuestion());

                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();
                }
            }

            // There are 9 basic questions. If current question is not at question 9, set text "Next Questions" else set text "Finish Quiz".
        } else {
            if (ans == 0) {
                answered = false;
                //wrongSound.start();
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EasyQuestionsActivity.this);
                myBuilder.setTitle("No Input!");
                myBuilder.setMessage("Click on the question mark to input your answer");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Input Answer", (dialogInterface, i) -> dialogInterface.dismiss());

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            } else {
                if (ans == currentQuestion.getAnswerNum()) {
                    score++;

                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(EasyQuestionsActivity.this);
                    myBuilder.setTitle("Congratulations!");
                    myBuilder.setMessage("You selected the correct answer!");
                    myBuilder.setCancelable(false);
                    final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                    myBuilder.setView(customLayout);
                    editText = customLayout.findViewById(R.id.et_text);
                    myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();


                    tvScore.setText("Score: " + score);


                } else {
                    streak = 0;
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(EasyQuestionsActivity.this);
                    myBuilder.setTitle("Sorry!");
                    myBuilder.setMessage("You selected the wrong answer!");
                    myBuilder.setCancelable(false);
                    final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                    myBuilder.setView(customLayout);
                    editText = customLayout.findViewById(R.id.et_text);
                    myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();
                }
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {
        ans = 0;
//        group.clearCheck();
        if (questionCounter < questionCountTotal) {
            currentQuestion = al.get(questionCounter);

            ivQuestion.setImageResource(currentQuestion.getQuestionsBasic());
            ivChoiceBasicQn.setImageResource(R.drawable.hidden_qn_reaction);
//            rdReaction_Option1.setBackgroundResource(currentQuestion.getOption1Reaction());
//            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(100,100);
//            rdReaction_Option1.setLayoutParams(parms);

//            rdReaction_Option2.setBackgroundResource(currentQuestion.getOption2Reaction());

            ivChoiceBasicQn.setOnClickListener(view -> MyCustomAlertDialog());


//            ivQuestion.setImageResource();
            questionCounter++;
            tvQuestionsNumber.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            btnStart.setText("Confirm");
        } else {
            finishQuiz();
        }
    }


    private void MyCustomAlertDialog() {
        final Dialog MyDialog = new Dialog(EasyQuestionsActivity.this);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.custom_alert_dialog);

        ImageView ivUserChoice1 = MyDialog.findViewById(R.id.ivUserChoice1);
        ImageView ivUserChoice2 = MyDialog.findViewById(R.id.ivUserChoice2);
        ImageView ivUserChoice3 = MyDialog.findViewById(R.id.ivUserChoice3);
        ImageView ivUserChoice4 = MyDialog.findViewById(R.id.ivUserChoice4);

        ivUserChoice1.setEnabled(true);
        ivUserChoice1.setImageResource(currentQuestion.getOption1Reaction());
        ivUserChoice2.setEnabled(true);
        ivUserChoice2.setImageResource(currentQuestion.getOption2Reaction());
        ivUserChoice3.setImageResource(android.R.color.transparent);
        ivUserChoice3.setEnabled(false);
        ivUserChoice4.setImageResource(android.R.color.transparent);
        ivUserChoice4.setEnabled(false);

        ivUserChoice1.setOnClickListener(view -> {
            ans = 1;
            ivChoiceBasicQn.setImageResource(currentQuestion.getOption1Reaction());
            MyDialog.cancel();
        });

        ivUserChoice2.setOnClickListener(view -> {
            ans = 2;
            ivChoiceBasicQn.setImageResource(currentQuestion.getOption2Reaction());
            MyDialog.cancel();
        });
        MyDialog.show();


    }


    private void finishQuiz() {
//        finish();
        //  Intent finishBasicMode_to_leaderboard = new Intent(QuestionsActivity.this, ShowScoreboardActivity.class);

        Intent intent = new Intent(EasyQuestionsActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("difficulty", currentQuestion.getMode());
        intent.putExtra("username", editText.getText().toString());
        startActivity(intent);
        finish();

    }
}