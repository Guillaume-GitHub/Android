package com.bague.guillaume.myfragmentapp.Controllers.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bague.guillaume.myfragmentapp.Controllers.Fragments.DetailFragment;
import com.bague.guillaume.myfragmentapp.R;

public class DetailActivity extends AppCompatActivity{

    private DetailFragment  mDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.configureAndShowFragment();
    }


    //********
    //FRAGMENT
    //********

    private void configureAndShowFragment(){

        // A- Get FragmentManager (support) and Try to find an existing Fragment instance in FrameLayout container
        mDetailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayout_activity_detail);

        if (mDetailFragment == null){
            //B- Create new instance of DetailFragment
            mDetailFragment = new DetailFragment();
            //C- Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout_activity_detail, mDetailFragment)
                    .commit();
        }

    }
}
