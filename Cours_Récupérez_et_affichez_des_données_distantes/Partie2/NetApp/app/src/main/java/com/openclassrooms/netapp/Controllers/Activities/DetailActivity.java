package com.openclassrooms.netapp.Controllers.Activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openclassrooms.netapp.Controllers.Fragments.DetailFragment;
import com.openclassrooms.netapp.Controllers.Fragments.MainFragment;
import com.openclassrooms.netapp.R;

public class DetailActivity extends AppCompatActivity {

    private DetailFragment detailFragment;
    public static String value;

    public DetailActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Show Fragment
        this.configureDetailFragment();
    }

    // Configuration fragment
    private void configureDetailFragment(){
        this.detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.activity_detail_frameLayout);

        if (this.detailFragment == null)
            this.detailFragment = new DetailFragment();

         getSupportFragmentManager().beginTransaction()
                            .add(R.id.activity_detail_frameLayout,this.detailFragment)
                            .commit();
    }

}
