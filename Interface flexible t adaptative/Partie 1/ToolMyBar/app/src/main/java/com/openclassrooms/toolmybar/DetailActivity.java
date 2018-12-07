package com.openclassrooms.toolmybar;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.configureToolbar();

    }

    private void configureToolbar(){
        //get toolbar (serialize)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //set the toolbar
        setSupportActionBar(toolbar);
        //Get support actionBar corresponding to this Toolbar
        ActionBar actionBar = getSupportActionBar();
        //Enable the up button
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


}
