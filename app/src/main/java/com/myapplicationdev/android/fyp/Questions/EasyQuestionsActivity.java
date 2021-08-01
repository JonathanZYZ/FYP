package com.myapplicationdev.android.fyp.Questions;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.MainActivity;
import com.myapplicationdev.android.fyp.Models.QuestionEasy;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

import java.util.ArrayList;
import java.util.Random;

public class EasyQuestionsActivity extends AppCompatActivity {
    Button btnStart;
    TextView tvQuestionsNumber, tvScore, tvTimer;
    RadioGroup group;
    RadioButton rdReaction_Option1, rdReaction_Option2;
    ImageView ivQuestion, btnExit, ivChoiceBasicQn, ivHints;
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
    MediaPlayer choiceSound, correctSound, wrongSound, finishSound, backgroundMusic, buttonSound;
    Vibrator v;
    CountDownTimer timer;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        btnStart = findViewById(R.id.btnStart);
        tvQuestionsNumber = findViewById(R.id.tvQuestions_Number);
        tvScore = findViewById(R.id.tvPoint);
        tvTimer = findViewById(R.id.tvTimerEasy);
        ivChoiceBasicQn = findViewById(R.id.ivChoiceBasicQn);
        ivQuestion = findViewById(R.id.ivQuestions);
        ivHints = findViewById(R.id.ivHints);
        btnExit = findViewById(R.id.btnExit);

        choiceSound = MediaPlayer.create(EasyQuestionsActivity.this, R.raw.answer_click);
        correctSound = MediaPlayer.create(EasyQuestionsActivity.this, R.raw.correct_answer);
        wrongSound = MediaPlayer.create(EasyQuestionsActivity.this, R.raw.wrong_answer);
        finishSound = MediaPlayer.create(EasyQuestionsActivity.this, R.raw.end_game);
        backgroundMusic = MediaPlayer.create(EasyQuestionsActivity.this, R.raw.background_music);
        buttonSound = MediaPlayer.create(EasyQuestionsActivity.this, R.raw.button_click);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        hints = new String[]{"Starting material has less steric hinderance.", "Starting material has less steric hinderance.", "Starting material with high steric hindrance inhibits nucleophilic attack.", "Starting material with high steric hindrance inhibits nucleophilic attack.", "Starting material with high steric hindrance inhibits nucleophilic attack.", "Starting material has less\n" +
                " steric hinderance.\n", "Starting material has less\n" +
                " steric hinderance.\n", "Undergoes either SN1 or SN2 depending on the Nucleophile and Solvent. ", "Undergoes either SN1 or SN2 depending on the Nucleophile and Solvent. "};
        al = new ArrayList<>();
        al.add(new QuestionEasy("basic", "1", R.drawable.question1_basic, R.drawable.question1_basic1_incorrect, R.drawable.question1_basic2_incorrect, R.drawable.question1_basic_correct, R.drawable.question1_basic3_incorrect, 3));
        al.add(new QuestionEasy("basic", "2", R.drawable.question2_basic, R.drawable.question2_basic_incorrect, R.drawable.question2_basic2_incorrect, R.drawable.question2_basic3_incorrect, R.drawable.question2_basic_correct, 4));
        al.add(new QuestionEasy("basic", "3", R.drawable.question3_basic, R.drawable.question3_basic1_incorrect, R.drawable.question3_basic_correct, R.drawable.question3_basic2_incorrect, R.drawable.question3_basic3_incorrect, 2));
        al.add(new QuestionEasy("basic", "4", R.drawable.question4_basic, R.drawable.question4_basic1_incorrect, R.drawable.question4_basic_correct, R.drawable.question4_basic2_incorrect, R.drawable.question4_basic3_incorrect, 2));
        al.add(new QuestionEasy("basic", "5", R.drawable.question5_basic, R.drawable.question5_basic1_incorrect, R.drawable.question5_basic3_incorrect, R.drawable.question5_basic3_incorrect, R.drawable.question5_basic_correct, 4));
        al.add(new QuestionEasy("basic", "6", R.drawable.question6_basic, R.drawable.question6_basic1_incorrect, R.drawable.question6_basic2_incorrect, R.drawable.question6_basic3_incorrect, R.drawable.question6_basic_correct, 4));
        al.add(new QuestionEasy("basic", "7", R.drawable.question7_basic, R.drawable.question7_basic_correct, R.drawable.question7_basic1_incorrect, R.drawable.question7_basic2_incorrect, R.drawable.question7_basic3_incorrect, 1));
        al.add(new QuestionEasy("basic", "8", R.drawable.question8_basic, R.drawable.question8_basic_correct, R.drawable.question8_basic1_incorrect, R.drawable.question8_basic2_incorrect, R.drawable.question8_basic3_incorrect, 1));
        al.add(new QuestionEasy("basic", "9", R.drawable.question9_basic, R.drawable.question9_basic1_incorrect, R.drawable.question9_basic2_incorrect, R.drawable.question9_basic3_incorrect, R.drawable.question9_basic_correct, 4));
        al.add(new QuestionEasy("basic", "10", R.drawable.question10_basic, R.drawable.question10_basic_correct, R.drawable.question10_basic1_incorrect, R.drawable.question10_basic2_incorrect, R.drawable.question10_basic3_incorrect, 1));
        al.add(new QuestionEasy("basic", "11", R.drawable.question11_basic, R.drawable.question11_basic1_incorrect, R.drawable.question11_basic_correct, R.drawable.question11_basic2_incorrect, R.drawable.question11_basic3_incorrect, 2));
        al.add(new QuestionEasy("basic", "12", R.drawable.question12_basic, R.drawable.question12_basic1_incorrect, R.drawable.question12_basic2_incorrect, R.drawable.question12_basic_correct, R.drawable.question12_basic3_incorrect, 3));
        al.add(new QuestionEasy("basic", "13", R.drawable.question13_basic, R.drawable.question13_basic1_incorrect, R.drawable.question13_basic_correct, R.drawable.question13_basic2_incorrect, R.drawable.question13_basic3_incorrect, 2));
        al.add(new QuestionEasy("basic", "14", R.drawable.question14_basic, R.drawable.question14_basic1_incorrect, R.drawable.question14_basic2_incorrect, R.drawable.question14_basic_correct, R.drawable.question14_basic3_incorrect, 3));
        al.add(new QuestionEasy("basic", "15", R.drawable.question15_basic, R.drawable.question15_basic1_incorrect, R.drawable.question15_basic2_incorrect, R.drawable.question15_basic3_incorrect, R.drawable.question15_basic_correct, 4));
        al.add(new QuestionEasy("basic", "16", R.drawable.question16_basic, R.drawable.question16_basic_correct, R.drawable.question16_basic1_incorrect, R.drawable.question16_basic2_incorrect, R.drawable.question16_basic3_incorrect, 1));
        al.add(new QuestionEasy("basic", "17", R.drawable.question17_basic, R.drawable.question17_basic_correct, R.drawable.question17_basic1_incorrect, R.drawable.question17_basic2_incorrect, R.drawable.question17_basic3_incorrect, 1));
        al.add(new QuestionEasy("basic", "18", R.drawable.question18_basic, R.drawable.question18_basic1_incorrect, R.drawable.question18_basic2_incorrect, R.drawable.question18_basic_correct, R.drawable.question18_basic3_incorrect, 3));

        questionCountTotal = al.size();
        btnStart.setText("Confirm");

        showNextQuestion();

        btnStart.setOnClickListener(view -> {
            //TODO
//            Intent i = new Intent(QuestionsActivity.this, QuestionsAnswerActivity.class);
//            i.putExtra("question","Question 1");
//            startActivity(i);

            buttonSound.start();
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

            buttonSound.start();
            AlertDialog.Builder exitScreen = new AlertDialog.Builder(EasyQuestionsActivity.this);
//                exitScreen.setTitle("");
            exitScreen.setMessage("Are you sure you want to quit? \n Here is your final score: " + score);
            exitScreen.setCancelable(false);


            exitScreen.setPositiveButton("Yes", (dialogInterface, i) -> {

                Intent basicMode_to_main = new Intent(EasyQuestionsActivity.this, MainActivity.class);
//                        basicMode_to_main.putExtra("question","Question 1");
                basicMode_to_main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(basicMode_to_main);
            });

            exitScreen.setNeutralButton("No", null);
            AlertDialog ShowDialogExit = exitScreen.create();
            ShowDialogExit.show();
        });
        ivHints.setOnClickListener(view -> {
            v.vibrate(50);
            buttonSound.start();
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
                v.vibrate(200);
                wrongSound.start();
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
                    correctSound.start();
                    streak += 1;
                    score++;
                    if (streak == 5) {
                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(EasyQuestionsActivity.this);
                        myBuilder.setTitle("Congratulations!");
                        myBuilder.setMessage("You have answered 5 questions correctly in a row! We would like to test you further by bringing you to the intermediate Level! GoodLuck!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Proceed to Intermediate Mode", (dialogInterface, i) -> {
                            Intent intent = new Intent(EasyQuestionsActivity.this, IntermediateQuestionsActivity.class);
                            intent.putExtra("scoreEasy", score);
                            intent.putExtra("scoreTotal", score);
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
                    v.vibrate(200);
                    wrongSound.start();
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
                v.vibrate(200);
                wrongSound.start();
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
                    correctSound.start();
                    score++;

                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(EasyQuestionsActivity.this);
                    myBuilder.setTitle("Congratulations!");
                    myBuilder.setMessage("You selected the correct answer!");
                    myBuilder.setCancelable(false);
                    final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                    myBuilder.setView(customLayout);
                    editText = customLayout.findViewById(R.id.et_text);
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                    myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());


                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();


                    tvScore.setText("Score: " + score);


                } else {
                    v.vibrate(200);
                    wrongSound.start();
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(EasyQuestionsActivity.this);
                    myBuilder.setTitle("Sorry!");
                    myBuilder.setMessage("You selected the wrong answer!");
                    myBuilder.setCancelable(false);
                    final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                    myBuilder.setView(customLayout);
                    editText = customLayout.findViewById(R.id.et_text);
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
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
            Random rand = new Random();
            currentQuestion = al.get(rand.nextInt(al.size()));

            ivQuestion.setImageResource(currentQuestion.getQuestionsBasic());
            ivChoiceBasicQn.setImageResource(R.drawable.hidden_qn_reaction);
//            rdReaction_Option1.setBackgroundResource(currentQuestion.getOption1Reaction());
//            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(100,100);
//            rdReaction_Option1.setLayoutParams(parms);

//            rdReaction_Option2.setBackgroundResource(currentQuestion.getOption2Reaction());


            ivChoiceBasicQn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    choiceSound.start();
                    MyCustomAlertDialog();
                }
            });
            timer = new CountDownTimer(70000, 1000) {

                public void onTick(long millisUntilFinished) {
                    tvTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    showNextQuestion();
                }
            }.start();

//            ivQuestion.setImageResource();
            questionCounter++;
            tvQuestionsNumber.setText("Easy: " + questionCounter + "/" + questionCountTotal);
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
        ivUserChoice3.setEnabled(true);
        ivUserChoice3.setImageResource(currentQuestion.getOption3Reaction());
//        ivUserChoice4.setImageResource(android.R.color.transparent);
        ivUserChoice4.setEnabled(true);
        ivUserChoice4.setImageResource(currentQuestion.getOption4Reaction());

        ivUserChoice1.setOnClickListener(view -> {
            choiceSound.start();
            ans = 1;
            ivChoiceBasicQn.setImageResource(currentQuestion.getOption1Reaction());
            MyDialog.cancel();
        });

        ivUserChoice2.setOnClickListener(view -> {
            choiceSound.start();
            ans = 2;
            ivChoiceBasicQn.setImageResource(currentQuestion.getOption2Reaction());
            MyDialog.cancel();
        });

        ivUserChoice3.setOnClickListener(view -> {
            choiceSound.start();
            ans = 3;
            ivChoiceBasicQn.setImageResource(currentQuestion.getOption3Reaction());
            MyDialog.cancel();
        });

        ivUserChoice4.setOnClickListener(view -> {
            choiceSound.start();
            ans = 4;
            ivChoiceBasicQn.setImageResource(currentQuestion.getOption4Reaction());
            MyDialog.cancel();
        });
        MyDialog.show();


    }


    private void finishQuiz() {
//        finish();
        //  Intent finishBasicMode_to_leaderboard = new Intent(QuestionsActivity.this, ShowScoreboardActivity.class);
        if (dbh.getAllScoreBoard().isEmpty()) {
            finishSound.start();
            Intent i = new Intent(EasyQuestionsActivity.this, ResultActivity.class);
            i.putExtra("scoreEasy", score);
            i.putExtra("scoreTotal", score);
            i.putExtra("difficulty", currentQuestion.getMode());
            i.putExtra("username", editText.getText().toString());
            Log.i("Test", "check");
            startActivity(i);
            finish();
        } else {
            ArrayList<String> namesInScoreboard = dbh.getNameInScoreBoard();
            for (int x = 0; x < namesInScoreboard.size(); x++) {
                if (editText.getText().toString().equalsIgnoreCase(namesInScoreboard.get(x))) {
                    wrongSound.start();
                    answered = false;
                    score--;
                    Toast.makeText(EasyQuestionsActivity.this, "Name already exists. Use a different name.", Toast.LENGTH_SHORT).show();
                } else {
                    finishSound.start();
                    Intent i = new Intent(EasyQuestionsActivity.this, ResultActivity.class);
                    i.putExtra("scoreEasy", score);
                    i.putExtra("scoreTotal", score);
                    i.putExtra("difficulty", currentQuestion.getMode());
                    i.putExtra("username", editText.getText().toString());
                    Log.i("Test", "check");
                    startActivity(i);
                    finish();
                }
            }
        }

    }
}