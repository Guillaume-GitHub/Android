package com.example.guillaume.navigationpatterns.Controllers.Activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guillaume.navigationpatterns.Adapters.PageAdapter;
import com.example.guillaume.navigationpatterns.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureViewPager();
    }

    private void configureViewPager() {
        //Get ViewPager from Layout
        ViewPager viewPager = findViewById(R.id.main_viewpager);

        // Set PageAdapter
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.colorPagesViewPager)) {

        });


    }
}
