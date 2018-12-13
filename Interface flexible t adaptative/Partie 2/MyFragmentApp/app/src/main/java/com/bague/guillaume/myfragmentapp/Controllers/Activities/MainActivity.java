package com.bague.guillaume.myfragmentapp.Controllers.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bague.guillaume.myfragmentapp.Controllers.Fragments.MainFragment;
import com.bague.guillaume.myfragmentapp.R;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener {

    //1 - Declare fragment
    private MainFragment mMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureAndShowFragment();
    }

    //*********
    //CALL BACK
    //*********
    @Override
    public void onFragmentInteraction(View view) {
        Log.d("Test", "onFragmentInteraction: click");
        startActivity(new Intent(this, DetailActivity.class));
    }

    //********
    //FRAGMENT
    //********

    private void configureAndShowFragment(){
        // A- Get FragmentManager (support) and Try to find an existing Fragment instance in FrameLayout container
        mMainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayout_activity_main);

        if (mMainFragment == null){
            // B- Create new mainFragment
            mMainFragment = new MainFragment();
            // C- Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout_activity_main,mMainFragment)
                    .commit();
        }
    }
}
