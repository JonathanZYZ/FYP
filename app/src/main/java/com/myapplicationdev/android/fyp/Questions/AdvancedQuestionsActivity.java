package com.myapplicationdev.android.fyp.Questions;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Models.QuestionAdvanced;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

import java.util.ArrayList;

public class AdvancedQuestionsActivity extends AppCompatActivity {

    Button btnStart;
    TextView tvQuestionsNumber, tvScore,tvTimer;
    ImageView ivQuestion, ivChoice1, ivChoice2, ivDidYouKnow;
    QuestionAdvanced currentQuestion;
    ArrayList<QuestionAdvanced> al;
    DBHelper dbh = new DBHelper(AdvancedQuestionsActivity.this);
    SharedPreferences sharedPreferences;
    int questionCounter, questionCountTotal;
    int ans1, ans2, streak;
    int score, numOfAnsForQn1, numOfAnsForQn2;
    int scoreEasy,scoreInter,scoreAdv;
    boolean answered;
    MediaPlayer choiceSound, correctSound, wrongSound, finishSound, backgroundMusic, buttonSound;
    EditText editText;
    Vibrator v;
    CountDownTimer timer;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_advanced);


        btnStart = findViewById(R.id.btnStart_advanced);
        tvQuestionsNumber = findViewById(R.id.tvQuestionsNumber_Advanced);
        tvScore = findViewById(R.id.tvScore_Advanced);
        tvTimer = findViewById(R.id.tvTimerAdvanced);
        ivQuestion = findViewById(R.id.ivQuestions_Advanced);
        ivChoice1 = findViewById(R.id.ivChoiceAdvancedQn1);
        ivChoice2 = findViewById(R.id.ivChoiceAdvancedQn2);
        ivDidYouKnow = findViewById(R.id.ivDidYouKnow);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        choiceSound = MediaPlayer.create(AdvancedQuestionsActivity.this, R.raw.answer_click);
        correctSound = MediaPlayer.create(AdvancedQuestionsActivity.this, R.raw.correct_answer);
        wrongSound = MediaPlayer.create(AdvancedQuestionsActivity.this, R.raw.wrong_answer);
        finishSound = MediaPlayer.create(AdvancedQuestionsActivity.this, R.raw.end_game);
        backgroundMusic = MediaPlayer.create(AdvancedQuestionsActivity.this, R.raw.background_music);
        buttonSound = MediaPlayer.create(AdvancedQuestionsActivity.this, R.raw.button_click);

//  TODO: When the user clicks the "did you know?" icon on the screen, text messages are prepared for them.
        String didYouKnowIconMsgQ1 = "Tamoxifen is a popular drug used to treat or prevent breast cancer. It is a selective estrogen receptor modulator (SERM) (ER positive breast cancer).\n" +
                "\n" +
                "Tamoxifen's primary structure is a tertiary alcohol. The dehydration process is catalyzed by the starting material, which has been treated with sulfuric acid. The alcohol group is protonated, converting it to a suitable leaving group, and an E1 reaction follows to form Tamoxifen.";

        String didYouKnowIconMsgQ2 = "Atracurium is a non-depolarizing neuromuscular blocking drug of the benzylisoquinolinium class that is used in conjunction with general anaesthesia to provide skeletal muscle relaxation during surgery or mechanical ventilation.\n" +
                "\n" +
                "Atracurium is a newer muscle relaxant that has a built-in functional group that allows the drug to be rapidly metabolized or changed into an inactive form via the E2 reaction of the drug's quaternary ammonium group.\n" +
                "\n" +
                "In the presence of normal blood pH and temperature, spontaneous degradation occurs through in-situ metabolism. There are no nucleophiles involved.\n";

        String didYouKnowIconMsgQ3 = "Geranyl diphosphate is an intermediate in the HMG-CoA reductase pathway, which organisms use to produce farnesyl pyrophosphate and geranylgeranyl pyrophosphate.\n" +
                "\n" +
                "Geranyl Diphosphate can be biologically converted to Geraniol, a sweet rosy-smelling liquid.\n" +
                "\n" +
                "Geranyl Diphosphate is converted into Geraniol, which benefits the perfumery industry.\n";


        String didYouKnowIconMsgQ4 = "There is no hint for this question.";
        String didYouKnowIconMsgQ5 = "There is no hint for this question.";
        String didYouKnowIconMsgQ6 = "There is no hint for this question.";


        String didYouKnowIconMsgQ7 = "Nitrogen mustard was first synthesized in the 1930s with the intention of using it as a weapon during World War II.\n" +
                "Nitrogen mustard was discovered to be effective in the treatment of cancer after World War II.\n" +
                "Nitrogen mustard (now known as Mechlorethamine) is a chemotherapy agent used in cancer patients.\n" +
                "Mechanism: DNA is alkylated through a series of two SN2 reactions. This causes cross-linking between segments of DNA, resulting in the formation of DNA adducts.\n";


        String didYouKnowIconMsgQ8 = "Aminoglycoside antibiotics such as Gentamicin, Streptomycin, and Neomycin are broad-spectrum bactericidal antibiotics that have shown efficacy in multi-antibiotic resistant bacteria and are thought to have originated from a soil bacteria called Actinomycetales.\n" +
                "\n" +
                "They are used to treat severe infections of the abdomen, urinary tract, bacteraemia, and endocarditis.\n" +
                "\n" +
                "Aminoglycosides inhibit protein synthesis in bacteria by binding irreversibly to the 30s ribosomal subunit. This prevents aminoacyl-tRNA from being transferred to the peptidyl site, resulting in premature termination of the peptide chain.\n";


        String didYouKnowIconMsgQ9 = "Geraniol is a colorless liquid or pale yellow oil with a sweet rose scent that is frequently used in perfumery.\n" +
                "It is a naturally fragrant alcohol derived from the distillation of rose oil and various essential oils, such as rose oil or citronella oil.\n" +
                "The FDA has determined that the ingredient is safe for use in human and animal body care products.\n" +
                "Geraniol can be produced biologically by converting Geranyl Diphosphate to Geraniol.\n";


        String didYouKnowIconMsgQ10 = "Other than the image hint, there is no word hint for this question.";
        String didYouKnowIconMsgQ11 = "There is no hint for this question.";
        String didYouKnowIconMsgQ12 = "There is no hint for this question.";
        String didYouKnowIconMsgQ13 = "There is no hint for this question.";
        String didYouKnowIconMsgQ14 = "There is no hint for this question.";
        String didYouKnowIconMsgQ15 = "There is no hint for this question.";
        String didYouKnowIconMsgQ16 = "There is no hint for this question.";
        String didYouKnowIconMsgQ17 = "There is no hint for this question.";
        String didYouKnowIconMsgQ18 = "There is no hint for this question.";
        String didYouKnowIconMsgQ19 = "There is no hint for this question.";


        al = new ArrayList<>();
        al.add(new QuestionAdvanced("advanced", "1", didYouKnowIconMsgQ1, R.drawable.question1_advanced, 1,
                R.drawable.hidden_qn_carbocation, 4, R.drawable.question1_advanced_carbocation_correct,
                R.drawable.question1_advanced_carbocation1_incorrect, R.drawable.question1_advanced_carbocation2_incorrect,
                R.drawable.question1_advanced_carbocation3_incorrect, 1, 0, 0, 0, 0, 0, 0, 0));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "2", didYouKnowIconMsgQ2, R.drawable.question2_advanced, 1,
                R.drawable.hidden_qn_reaction, 4, R.drawable.question2_advanced_reaction1_incorrect,
                R.drawable.question2_advanced_reaction2_incorrect, R.drawable.question2_advanced_reaction_correct,
                R.drawable.question2_advanced_reaction3_incorrect, 3, 0, 0, 0, 0, 0, 0, 0));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "3", didYouKnowIconMsgQ3, R.drawable.question3_advanced, 2,
                R.drawable.hidden_qn_reaction, 4, R.drawable.question3_advanced_reaction1_incorrect,
                R.drawable.question3_advanced_reaction2_incorrect, R.drawable.question3_advanced_reaction_correct,
                R.drawable.question3_advanced_reaction3_incorrect, 3, R.drawable.hidden_qn_carbocation, 4, R.drawable.question3_advanced_carbocation_correct,
                R.drawable.question3_advanced_carbocation1_incorrect, R.drawable.question3_advanced_carbocation2_incorrect, R.drawable.question3_advanced_carbocation3_incorrect, 1));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "4", didYouKnowIconMsgQ4, R.drawable.question4_advanced, 1,
                R.drawable.hidden_qn_startingmaterial, 4, R.drawable.question4_advanced_startingmaterial1_incorrect,
                R.drawable.question4_advanced_startingmaterial2_incorrect, R.drawable.question4_advanced_startingmaterial3_incorrect,
                R.drawable.question4_advanced_startingmaterial_correct, 4, 0, 0, 0, 0, 0, 0, 0));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "5", didYouKnowIconMsgQ5, R.drawable.question5_advanced, 2,
                R.drawable.hidden_qn_reaction, 4, R.drawable.question5_advanced_reaction1_incorrect,
                R.drawable.question5_advanced_reaction2_incorrect, R.drawable.question5_advanced_reaction_correct,
                R.drawable.question5_advanced_reaction3_incorrect, 3, R.drawable.hidden_qn_product, 4, R.drawable.question5_advanced_product1_incorrect,
                R.drawable.question5_advanced_product2_incorrect, R.drawable.question5_advanced_product3_incorrect, R.drawable.question5_advanced_product_correct, 4));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "6", didYouKnowIconMsgQ6, R.drawable.question6_advanced, 2,
                R.drawable.hidden_qn_reaction, 4, R.drawable.question6_advanced_reaction1_incorrect,
                R.drawable.question6_advanced_reaction2_incorrect, R.drawable.question6_advanced_reaction3_incorrect,
                R.drawable.question6_advanced_reaction_correct, 4, R.drawable.hidden_qn_product, 4, R.drawable.question6_advanced_product_correct,
                R.drawable.question6_advanced_product1_incorrect, R.drawable.question6_advanced_product3_incorrect, R.drawable.question6_advanced_product2_incorrect, 1));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "7", didYouKnowIconMsgQ7, R.drawable.question7_advanced, 1,
                R.drawable.hidden_qn_reaction, 4, R.drawable.question7_advanced_reaction1_incorrect,
                R.drawable.question7_advanced_reaction_correct, R.drawable.question7_advanced_reaction2_incorrect,
                R.drawable.question7_advanced_reaction3_incorrect, 2, 0, 0, 0, 0, 0, 0, 0));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "8", didYouKnowIconMsgQ8, R.drawable.question8_advanced, 1,
                R.drawable.hidden_qn_reaction, 4, R.drawable.question8_advanced_reaction1_incorrect,
                R.drawable.question8_advanced_reaction2_incorrect, R.drawable.question8_advanced_reaction3_incorrect,
                R.drawable.question8_advanced_reaction_correct, 4, 0, 0, 0, 0, 0, 0, 0));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "9", didYouKnowIconMsgQ9, R.drawable.question9_advanced, 1,
                R.drawable.hidden_qn_carbocation, 4, R.drawable.question9_advanced_carbocation1_incorrect,
                R.drawable.question9_advanced_carbocation2_incorrect, R.drawable.question9_advanced_carbocation_correct,
                R.drawable.question9_advanced_carbocation3_incorrect, 3, 0, 0, 0, 0, 0, 0, 0));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "10", didYouKnowIconMsgQ10, R.drawable.question10_advanced, 1,
                R.drawable.hidden_qn_startingmaterial, 4, R.drawable.question10_advanced_startingmaterial_correct,
                R.drawable.question10_advanced_startingmaterial1_incorrect, R.drawable.question10_advanced_startingmaterial2_incorrect,
                R.drawable.question10_advanced_startingmaterial3_incorrect, 1, 0, 0, 0, 0, 0, 0, 0));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "11", didYouKnowIconMsgQ11, R.drawable.question11_advanced, 1,
                R.drawable.hidden_qn_startingmaterial, 4, R.drawable.question11_advanced_startingmaterial3_incorrect,
                R.drawable.question11_advanced_startingmaterial1_incorrect, R.drawable.question11_advanced_startingmaterial2_incorrect,
                R.drawable.question11_advanced_startingmaterial_correct, 4, 0, 0, 0, 0, 0, 0, 0));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "12", didYouKnowIconMsgQ12, R.drawable.question12_advanced, 1,
                R.drawable.hidden_qn_product, 4, R.drawable.question12_advanced_product1_incorrect,
                R.drawable.question12_advanced_product_correct, R.drawable.question12_advanced_product2_incorrect,
                R.drawable.question12_advanced_product3_incorrect, 2, 0, 0, 0, 0, 0, 0, 0));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "13", didYouKnowIconMsgQ13, R.drawable.question13_advanced, 1,
                R.drawable.hidden_qn_product, 4, R.drawable.question13_advanced_product1_incorrect,
                R.drawable.question13_advanced_product_correct, R.drawable.question13_advanced_product2_incorrect,
                R.drawable.question13_advanced_product3_incorrect, 2, 0, 0, 0, 0, 0, 0, 0));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "14", didYouKnowIconMsgQ14, R.drawable.question14_advanced, 1,
                R.drawable.hidden_qn_solvent, 4, R.drawable.question14_advanced_solvent1_incorrect,
                R.drawable.question14_advanced_solvent2_incorrect, R.drawable.question14_advanced_solvent3_incorrect,
                R.drawable.question14_advanced_solvent_correct, 4, 0, 0, 0, 0, 0, 0, 0));
        //<--- MAJORPRODUCT--->
        al.add(new QuestionAdvanced("advanced", "15", didYouKnowIconMsgQ15, R.drawable.question15_advanced, 2,
                R.drawable.hidden_qn_carbocation, 4, R.drawable.question15_advanced_carbocation1_incorrect,
                R.drawable.question15_advanced_carbocation2_incorrect, R.drawable.question15_advanced_carbocation3_incorrect,
                R.drawable.question15_advanced_carbocation_correct, 4, R.drawable.hidden_qn_majorproduct, 4, R.drawable.question15_advanced_majorproduct_correct,
                R.drawable.question15_advanced_majorproduct1_incorrect, R.drawable.question15_advanced_majorproduct2_incorrect, R.drawable.question15_advanced_majorproduct3_incorrect, 1));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "16", didYouKnowIconMsgQ16, R.drawable.question16_advanced, 2,
                R.drawable.hidden_qn_reaction, 4, R.drawable.question16_advanced_reaction1_incorrect,
                R.drawable.question16_advanced_reaction_correct, R.drawable.question16_advanced_reaction2_incorrect,
                R.drawable.question16_advanced_reaction3_incorrect, 2, R.drawable.hidden_qn_product, 4, R.drawable.question16_advanced_product_correct,
                R.drawable.question16_advanced_product1_incorrect, R.drawable.question16_advanced_product2_incorrect, R.drawable.question16_advanced_product3_incorrect, 1));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "17", didYouKnowIconMsgQ17, R.drawable.question17_advanced, 2,
                R.drawable.hidden_qn_reaction, 4, R.drawable.question17_advanced_reaction1_incorrect,
                R.drawable.question17_advanced_reaction_correct, R.drawable.question17_advanced_reaction2_incorrect,
                R.drawable.question17_advanced_reaction3_incorrect, 2, R.drawable.hidden_qn_product, 4, R.drawable.question17_advanced_product_correct,
                R.drawable.question17_advanced_product1_incorrect, R.drawable.question17_advanced_product2_incorrect, R.drawable.question17_advanced_product3_incorrect, 1));
        //<--- --->
        al.add(new QuestionAdvanced("advanced", "18", didYouKnowIconMsgQ18, R.drawable.question18_advanced, 2,
                R.drawable.hidden_qn_solvent, 2, R.drawable.question18_advanced_solvent_correct,
                R.drawable.question18_advanced_solvent_incorrect, 0,
                0, 1, R.drawable.hidden_qn_product, 4, R.drawable.question18_advanced_product_correct,
                R.drawable.question18_advanced_product1_incorrect, R.drawable.question18_advanced_product2_incorrect, R.drawable.question18_advanced_product3_incorrect, 1));
        //<--- MAJORPRODUCT--->
        al.add(new QuestionAdvanced("advanced", "19", didYouKnowIconMsgQ19, R.drawable.question19_advanced, 2,
                R.drawable.hidden_qn_reaction, 4, R.drawable.question19_advanced_reaction1_incorrect,
                R.drawable.question19_advanced_reaction_correct, R.drawable.question19_advanced_reaction2_incorrect,
                R.drawable.question19_advanced_reaction3_incorrect, 2, R.drawable.hidden_qn_majorproduct, 4, R.drawable.question19_advanced_product_correct,
                R.drawable.question19_advanced_product1_incorrect, R.drawable.question19_advanced_product2_incorrect, R.drawable.question19_advanced_product3_incorrect, 1));
        questionCountTotal = al.size();
        Intent i = getIntent();
        int currentScore = i.getIntExtra("scoreTotal", 0);
        scoreEasy = i.getIntExtra("scoreEasy",0);
        scoreInter = i.getIntExtra("scoreIntermediate",0);
        if (currentScore != 0) {
            score = currentScore;
        }
        showNextQuestion();
        tvQuestionsNumber.setText("Question: " + questionCounter + "/" + questionCountTotal);
        tvScore.setText("Score: " + score);
        btnStart.setOnClickListener(view -> {
            buttonSound.start();
            if (!answered) {

                checkAnswer();

            }
        });


    }


    @SuppressLint("SetTextI18n")
    public void checkAnswer() {
        answered = true;
        if (ans1 == 0 && ans2 == 0) {
            v.vibrate(200);
            answered = false;
            wrongSound.start();
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(AdvancedQuestionsActivity.this);
            myBuilder.setTitle("No Input!");
            myBuilder.setMessage("Click on the question mark to input your answer");
            myBuilder.setCancelable(false);
            myBuilder.setPositiveButton("Input Answer", (dialogInterface, i) -> dialogInterface.dismiss());

            AlertDialog myDialog = myBuilder.create();
            myDialog.show();
        } else if (currentQuestion.getQnCount() == 2 && ans2 == 0) {
            v.vibrate(200);
            answered = false;
            wrongSound.start();
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(AdvancedQuestionsActivity.this);
            myBuilder.setTitle("No Input for qns 2");
            myBuilder.setMessage("Click on the question mark to input your answer");
            myBuilder.setCancelable(false);
            myBuilder.setPositiveButton("Input Answer", (dialogInterface, i) -> dialogInterface.dismiss());

            AlertDialog myDialog = myBuilder.create();
            myDialog.show();
        } else if (currentQuestion.getQnCount() == 2 && ans1 == 0) {
            v.vibrate(200);
            answered = false;
            wrongSound.start();
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(AdvancedQuestionsActivity.this);
            myBuilder.setTitle("No Input for qns 1");
            myBuilder.setMessage("Click on the question mark to input your answer");
            myBuilder.setCancelable(false);
            myBuilder.setPositiveButton("Input Answer", (dialogInterface, i) -> dialogInterface.dismiss());

            AlertDialog myDialog = myBuilder.create();
            myDialog.show();
        } else {
            timer.cancel();
            if (questionCounter < questionCountTotal) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(AdvancedQuestionsActivity.this);
                myBuilder.setTitle("Sorry");
                if (currentQuestion.getQnCount() == 2) {
                    if (ans1 == currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2()) {
                        correctSound.start();
                        streak += 1;
                        score++;
                        scoreAdv++;
                        if (streak == 5) {
                            myBuilder.setTitle("Congratulations!!");
                            myBuilder.setMessage("You have answered 5 questions correctly in a row! Would you like to continue or check your results?");

                            final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                            myBuilder.setView(customLayout);
                            editText = customLayout.findViewById(R.id.et_text);
                            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                            myBuilder.setCancelable(false);
                            //myBuilder.setPositiveButton("Next", (dialogInterface, i) -> Intent intent = new Intent(IntermediateQuestionsActivity.this,AdvancedQuestionsActivity));
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> {
                                finishQuiz();
                            });

                            myBuilder.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    streak = 0;
                                    showNextQuestion();
                                }
                            });

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();

                        } else {
                            myBuilder.setTitle("Congratulations!!");
                            myBuilder.setMessage("You selected the correct answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();

                            tvScore.setText("Score: " + score);
                        }
                    } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2()) {
                        wrongSound.start();
                        v.vibrate(200);
                        streak = 0;
                        myBuilder.setMessage("You selected the wrong answer for Question 1!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 == currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum2()) {
                        wrongSound.start();
                        v.vibrate(200);
                        streak = 0;
                        myBuilder.setMessage("You selected the wrong answer for Question 2!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else {
                        wrongSound.start();
                        v.vibrate(200);
                        streak = 0;
                        myBuilder.setMessage("You selected the wrong answer for all Questions!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    }


                } else if (currentQuestion.getQnCount() == 1) {
                    if (currentQuestion.getQn1Image() != 0) {

                        if (ans1 == currentQuestion.getCorrectNum1()) {
                            correctSound.start();
                            streak += 1;
                            score++;
                            scoreAdv++;
                            if (streak == 5) {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You have answered 5 questions correctly in a row!  Would you like to continue or check your results?");

                                final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                                myBuilder.setView(customLayout);
                                editText = customLayout.findViewById(R.id.et_text);
                                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                                myBuilder.setCancelable(false);
                                //myBuilder.setPositiveButton("Next", (dialogInterface, i) -> Intent intent = new Intent(IntermediateQuestionsActivity.this,AdvancedQuestionsActivity));
                                myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> {
                                    finishQuiz();
                                });

                                myBuilder.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        streak = 0;
                                        showNextQuestion();
                                    }
                                });

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                            } else {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You selected the correct answer!");
                                myBuilder.setCancelable(false);
                                myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                                tvScore.setText("Score: " + score);
                            }
                        } else if (ans1 != currentQuestion.getCorrectNum1()) {
                            wrongSound.start();
                            v.vibrate(200);
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    }
                }


                // btnStart.setText("Next Question");
            } else {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(AdvancedQuestionsActivity.this);
                myBuilder.setTitle("Sorry");
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                myBuilder.setView(customLayout);
                editText = customLayout.findViewById(R.id.et_text);
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                if (currentQuestion.getQnCount() == 2) {
                    if (ans1 == currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2()) {
                        correctSound.start();
                        myBuilder.setTitle("Congratulations!!");
                        myBuilder.setMessage("You selected the correct answer!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();

                        score++;
                        tvScore.setText("Score: " + score);
                    } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2()) {
                        wrongSound.start();
                        v.vibrate(200);
                        myBuilder.setMessage("You selected the wrong answer for Question 1!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 == currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum2()) {
                        wrongSound.start();
                        v.vibrate(200);
                        myBuilder.setMessage("You selected the wrong answer for Question 2!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else {
                        wrongSound.start();
                        v.vibrate(200);
                        myBuilder.setMessage("You selected the wrong answer for all Questions!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    }


                } else if (currentQuestion.getQnCount() == 1) {
                    if (currentQuestion.getQn1Image() != 0) {
                        if (ans1 == currentQuestion.getCorrectNum1()) {
                            correctSound.start();
                            myBuilder.setTitle("Congratulations!!");
                            myBuilder.setMessage("You selected the correct answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();

                            score++;
                            tvScore.setText("Score: " + score);
                        } else if (ans1 != currentQuestion.getCorrectNum1()) {
                            wrongSound.start();
                            v.vibrate(200);
                            myBuilder.setMessage("You selected the wrong answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {
        ans1 = 0;
        ans2 = 0;
        ivChoice1.setEnabled(true);
        ivChoice2.setEnabled(true);
        if (questionCounter < questionCountTotal) {
            currentQuestion = al.get(questionCounter);

            ivQuestion.setImageResource(currentQuestion.getQnMainImage());
            if (currentQuestion.getQnCount() == 1) {
                ivChoice2.setImageResource(android.R.color.transparent);
                ivChoice2.setEnabled(false);
                if (currentQuestion.getQn1Image() != 0) {
                    ivChoice1.setImageResource(currentQuestion.getQn1Image());
                    numOfAnsForQn1 = currentQuestion.getNumOfQuestions1();
                }
            } else if (currentQuestion.getQnCount() == 2) {
                ivChoice1.setImageResource(currentQuestion.getQn1Image());
                numOfAnsForQn1 = currentQuestion.getNumOfQuestions1();
                ivChoice2.setImageResource(currentQuestion.getQn2Image());
                numOfAnsForQn2 = currentQuestion.getNumOfQuestions2();

            }

            ivChoice1.setOnClickListener(view -> {
                choiceSound.start();
                MyCustomAlertDialog(1, numOfAnsForQn1);
            });
            ivChoice2.setOnClickListener(view -> {
                choiceSound.start();
                MyCustomAlertDialog(2, numOfAnsForQn2);
            });
            ivDidYouKnow.setOnClickListener(view -> {
                v.vibrate(50);
                AlertDialog.Builder hintDialog = new AlertDialog.Builder(AdvancedQuestionsActivity.this);
                hintDialog.setTitle("Did You Know? #" + questionCounter);
                // hintDialog.setIcon()

                hintDialog.setMessage(currentQuestion.getDidYouKnowMsg());
                hintDialog.setCancelable(true);
                hintDialog.show();
            });
            timer = new CountDownTimer(30000, 1000) {

                public void onTick(long millisUntilFinished) {
                    tvTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    showNextQuestion();
                }
            }.start();
            questionCounter++;
            tvQuestionsNumber.setText("Advanced: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            btnStart.setText("Confirm");
        } else {
            finishQuiz();
        }
    }

    private void MyCustomAlertDialog(int qnNum, int numOfAns) {
        final Dialog MyDialog = new Dialog(AdvancedQuestionsActivity.this);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.custom_alert_dialog);

        ImageView ivUserChoice1 = MyDialog.findViewById(R.id.ivUserChoice1);
        ImageView ivUserChoice2 = MyDialog.findViewById(R.id.ivUserChoice2);
        ImageView ivUserChoice3 = MyDialog.findViewById(R.id.ivUserChoice3);
        ImageView ivUserChoice4 = MyDialog.findViewById(R.id.ivUserChoice4);

        if (qnNum == 1) {
            if (numOfAns == 2) {
                ivUserChoice3.setEnabled(false);
                ivUserChoice3.setImageResource(android.R.color.transparent);
                ivUserChoice4.setEnabled(false);
                ivUserChoice4.setImageResource(android.R.color.transparent);
                //
                ivUserChoice1.setEnabled(true);
                ivUserChoice1.setImageResource(currentQuestion.getAns1Image1());
                ivUserChoice2.setEnabled(true);
                ivUserChoice2.setImageResource(currentQuestion.getAns1Image2());
            } else if (numOfAns == 4) {
                ivUserChoice1.setEnabled(true);
                ivUserChoice1.setImageResource(currentQuestion.getAns1Image1());
                ivUserChoice2.setEnabled(true);
                ivUserChoice2.setImageResource(currentQuestion.getAns1Image2());
                ivUserChoice3.setEnabled(true);
                ivUserChoice3.setImageResource(currentQuestion.getAns1Image3());
                ivUserChoice4.setEnabled(true);
                ivUserChoice4.setImageResource(currentQuestion.getAns1Image4());
            }


        } else if (qnNum == 2) {
            if (numOfAns == 4) {
                ivUserChoice1.setEnabled(true);
                ivUserChoice1.setImageResource(currentQuestion.getAns2Image1());
                ivUserChoice2.setEnabled(true);
                ivUserChoice2.setImageResource(currentQuestion.getAns2Image2());
                ivUserChoice3.setEnabled(true);
                ivUserChoice3.setImageResource(currentQuestion.getAns2Image3());
                ivUserChoice4.setEnabled(true);
                ivUserChoice4.setImageResource(currentQuestion.getAns2Image4());
            }

        }

        ivUserChoice1.setOnClickListener(view -> {
            choiceSound.start();
            if (qnNum == 1) {
                if (numOfAns == 2) {
                    ans1 = 1;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image1());
                } else if (numOfAns == 4) {
                    ans1 = 1;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image1());
                }

            } else if (qnNum == 2) {
                if (numOfAns == 4) {
                    ans2 = 1;
                    ivChoice2.setImageResource(currentQuestion.getAns2Image1());
                }

            }
            MyDialog.cancel();
        });

        ivUserChoice2.setOnClickListener(view -> {
            choiceSound.start();
            if (qnNum == 1) {
                if (numOfAns == 2) {
                    ans1 = 2;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image2());
                } else if (numOfAns == 4) {
                    ans1 = 2;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image2());
                }

            } else if (qnNum == 2) {
                if (numOfAns == 4) {
                    ans2 = 2;
                    ivChoice2.setImageResource(currentQuestion.getAns2Image2());
                }

            }
            MyDialog.cancel();
        });

        ivUserChoice3.setOnClickListener(view -> {
            choiceSound.start();
            if (qnNum == 1) {
                if (numOfAns == 2) {
                    ans1 = 3;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image3());
                } else if (numOfAns == 4) {
                    ans1 = 3;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image3());
                }

            } else if (qnNum == 2) {
                if (numOfAns == 4) {
                    ans2 = 3;
                    ivChoice2.setImageResource(currentQuestion.getAns2Image3());
                }

            }
            MyDialog.cancel();
        });

        ivUserChoice4.setOnClickListener(view -> {
            choiceSound.start();
            if (qnNum == 1) {

                ans1 = 4;
                ivChoice1.setImageResource(currentQuestion.getAns1Image4());


            } else if (qnNum == 2) {

                ans2 = 4;
                ivChoice2.setImageResource(currentQuestion.getAns2Image4());


            }
            MyDialog.cancel();

        });

        MyDialog.show();

    }

    private void finishQuiz() {
        if (dbh.getAllScoreBoard().isEmpty()) {
            finishSound.start();
            Intent i = new Intent(AdvancedQuestionsActivity.this, ResultActivity.class);
            i.putExtra("scoreTotal", score);
            i.putExtra("scoreIntermediate",scoreInter);
            i.putExtra("scoreEasy",scoreEasy);
            i.putExtra("scoreAdv",scoreAdv);
            i.putExtra("difficulty", currentQuestion.getMode());
            i.putExtra("username", editText.getText().toString());
            startActivity(i);
            finish();
        } else {
            ArrayList<String> namesInScoreboard = dbh.getNameInScoreBoard();
            for (int x = 0; x < namesInScoreboard.size(); x++) {
                if (editText.getText().toString().equalsIgnoreCase(namesInScoreboard.get(x))) {
                    wrongSound.start();
                    Toast.makeText(AdvancedQuestionsActivity.this, "The username is already in use. Please use a different username.", Toast.LENGTH_SHORT).show();
                } else {
                    finishSound.start();
                    Intent i = new Intent(AdvancedQuestionsActivity.this, ResultActivity.class);
                    i.putExtra("scoreTotal", score);
                    i.putExtra("scoreIntermediate",scoreInter);
                    i.putExtra("scoreEasy",scoreEasy);
                    i.putExtra("scoreAdv",scoreAdv);
                    i.putExtra("difficulty", currentQuestion.getMode());
                    i.putExtra("username", editText.getText().toString());
                    startActivity(i);
                    finish();

                }
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }
}




