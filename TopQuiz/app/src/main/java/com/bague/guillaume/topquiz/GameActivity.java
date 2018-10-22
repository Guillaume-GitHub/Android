package com.bague.guillaume.topquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView mQuestionTxt;
    private Button mAnswer1Btn;
    private Button mAnswer2Btn;
    private Button mAnswer3Btn;
    private Button mAnswer4Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionTxt = findViewById(R.id.GameActivity_Question_Txt);
        mAnswer1Btn = findViewById(R.id.GameActivity_Answser1_Btn);
        mAnswer2Btn = findViewById(R.id.GameActivity_Answser2_Btn);
        mAnswer3Btn = findViewById(R.id.GameActivity_Answser3_Btn);
        mAnswer4Btn = findViewById(R.id.GameActivity_Answser4_Btn);
    }
}
