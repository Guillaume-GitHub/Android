package com.bague.guillaume.topquiz.controller;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bague.guillaume.topquiz.R;
import com.bague.guillaume.topquiz.model.Question;
import com.bague.guillaume.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestionTxt;
    private Button mAnswer1Btn;
    private Button mAnswer2Btn;
    private Button mAnswer3Btn;
    private Button mAnswer4Btn;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;
    private  int mNumberOfQuestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionBank = this.generateQuestions();
        mScore = 0;
        mNumberOfQuestions = 4;

        // Wire widgets
        mQuestionTxt = (TextView) findViewById(R.id.GameActivity_Question_Txt);
        mAnswer1Btn = (Button) findViewById(R.id.GameActivity_Answser1_Btn);
        mAnswer2Btn = (Button) findViewById(R.id.GameActivity_Answser2_Btn);
        mAnswer3Btn = (Button) findViewById(R.id.GameActivity_Answser3_Btn);
        mAnswer4Btn = (Button) findViewById(R.id.GameActivity_Answser4_Btn);

        // Use the tag property to 'name' the buttons
        mAnswer1Btn.setTag(0);
        mAnswer2Btn.setTag(1);
        mAnswer3Btn.setTag(2);
        mAnswer4Btn.setTag(3);

        // define onClick method to all Btn
        mAnswer1Btn.setOnClickListener(this);
        mAnswer2Btn.setOnClickListener(this);
        mAnswer3Btn.setOnClickListener(this);
        mAnswer4Btn.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    private void displayQuestion(final Question question) {
        mQuestionTxt.setText(question.getQuestion());
        mAnswer1Btn.setText(question.getChoiceList().get(0));
        mAnswer2Btn.setText(question.getChoiceList().get(1));
        mAnswer3Btn.setText(question.getChoiceList().get(2));
        mAnswer4Btn.setText(question.getChoiceList().get(3));
    }

    private QuestionBank generateQuestions() {

        Question question1 = new Question("What is the last Androïd version name ?",
                Arrays.asList("Oreo", "Pie","Nougat", "Lollipop"),
                1);
        Question question2 = new Question("What is the creator of Linux Kernel ?",
                Arrays.asList("Linus Torvalds", "Steve Jobs", "Marc Zuckerberg", "Andy Rubin"),
                0);
        Question question3 = new Question("What is the smallest planet of our solar system ?",
                Arrays.asList("Jupiter", "Neptune", "Saturne", "Mercure"),
                3);
        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);
        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);
        Question question6 = new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);
        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);
        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);
        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);
        return new QuestionBank(Arrays.asList(
                question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));
    }


    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();
        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            //Correct
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
        }else{
            //Not correct
            Toast.makeText(this, "Not Correct", Toast.LENGTH_SHORT).show();
        }

        if (--mNumberOfQuestions == 0){
            endGame();
        }else {
            mCurrentQuestion = mQuestionBank.getQuestion();
            displayQuestion(mCurrentQuestion);
        }

    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Well Done !")
                .setMessage("Your score is " + mScore)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // end the current activity and return to the previous activity
                        finish();
                    }
                })
                .create()
                .show();

    }


}
