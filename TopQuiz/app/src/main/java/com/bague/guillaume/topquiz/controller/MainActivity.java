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
import android.widget.Toast;

import com.bague.guillaume.topquiz.R;
import com.bague.guillaume.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingTxt;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;
    private SharedPreferences mPreferences;

    public static final int GAME_ACTIVITY_REQUEST_CODE = 1;
    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    private String mFirstName;
    private int mScore;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            //fetch the score from intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE,0);

            mPreferences.edit()
                        .putInt(PREF_KEY_SCORE,score)
                        .apply();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("MainActivity : onRestart()");

        mFirstName = mPreferences.getString(PREF_KEY_FIRSTNAME,"");
        mScore = mPreferences.getInt(PREF_KEY_SCORE,-1);

        if(!mFirstName.isEmpty() && mScore != -1) {
            loadUserPreferences();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MainActivity : onCreate()");

        setContentView(R.layout.activity_main);

        mGreetingTxt = findViewById(R.id.activity_main_greeting_txt);
        mNameInput =  findViewById(R.id.activity_main_name_input);
        mPlayButton = findViewById(R.id.activity_main_play_btn);

        mPreferences = getPreferences(MODE_PRIVATE);//only this application can use it
        mFirstName = mPreferences.getString(PREF_KEY_FIRSTNAME,"");
        mScore = mPreferences.getInt(PREF_KEY_SCORE,-1);

            if(!mFirstName.isEmpty() && mScore != -1) {
                loadUserPreferences();
            }
                mUser = new User();
                mPlayButton.setEnabled(false);

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(mNameInput.length() < 0 )
                    mPlayButton.setEnabled(true);
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

                        mPreferences.edit()
                                    .putString(PREF_KEY_FIRSTNAME,mUser.getFirstName())
                                    .apply();

                      Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                      startActivityForResult(gameActivityIntent,GAME_ACTIVITY_REQUEST_CODE);
                    }
                });
            }
        });

}


    private void loadUserPreferences() {
        mGreetingTxt.setText("Welcome back " + mFirstName + "\r\n"
                + "Your last score was "+ mScore + ", can you doing better ?");
        mNameInput.setText(mFirstName);
        mPlayButton.setEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("MainActivity : onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("MainActivity : onStop()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("MainActivity : onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("MainActivity : onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivity : onDestroy()");
    }

}
