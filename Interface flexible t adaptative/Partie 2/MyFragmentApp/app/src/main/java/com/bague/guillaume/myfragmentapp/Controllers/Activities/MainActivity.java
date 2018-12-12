package com.bague.guillaume.myfragmentapp.Controllers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bague.guillaume.myfragmentapp.Controllers.Fragments.MainFragment;
import com.bague.guillaume.myfragmentapp.R;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(View view) {

        Log.d("Test", "onFragmentInteraction: click");
        startActivity(new Intent(this, DetailActivity.class));
    }
}
