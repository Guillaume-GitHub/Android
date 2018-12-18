package com.example.guillaume.navigationpatterns.Controllers.Activities;

import android.support.design.widget.TabLayout;
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
        this.configureViewPagerAndTabs();
    }

    private void configureViewPagerAndTabs() {
        //Get ViewPager from Layout
        ViewPager viewPager = findViewById(R.id.main_viewpager);

        // Set PageAdapter
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()) {
        });

        // Get TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.activity_main_tabs);
        //Glue TabLayout and ViewPager together
        tabLayout.setupWithViewPager(viewPager);
        //Design : Tabs are same width
        tabLayout.setTabMode(tabLayout.MODE_FIXED);
    }
}
