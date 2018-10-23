package com.bague.guillaume.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bague.guillaume.topquiz.R;
import com.bague.guillaume.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingTxt;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;
    

    public static final int GAME_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            //fetch the score from intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE,0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGreetingTxt = findViewById(R.id.activity_main_greeting_txt);
        mNameInput =  findViewById(R.id.activity_main_name_input);
        mPlayButton = findViewById(R.id.activity_main_play_btn);

        mUser = new User();

        mPlayButton.setEnabled(false);

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPlayButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mUser.setFirstName(mNameInput.getText().toString());
                      Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                      startActivityForResult(gameActivityIntent,GAME_ACTIVITY_REQUEST_CODE);
                    }
                });
            }
        });



    }
}
