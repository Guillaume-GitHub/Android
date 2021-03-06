package com.bague.guillaume.myfragmentapp.Controllers.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bague.guillaume.myfragmentapp.Controllers.Fragments.DetailFragment;
import com.bague.guillaume.myfragmentapp.R;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_BUTTON_TAG = DetailActivity.EXTRA_BUTTON_TAG;
    // Declare detail fragment
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Configure and show home fragment
        this.configureAndShowDetailFragment();
    }



    @Override
    protected void onResume() {
        super.onResume();
        // 3 - Call update method here because we are sure that DetailFragment is visible
        this.updateWithIntentTag();
    }

    //---------------
    //UPDATE TEXTVIEW
    //---------------
    private void updateWithIntentTag() {
        int tag = getIntent().getIntExtra(DetailActivity.EXTRA_BUTTON_TAG,0);
        detailFragment.updateTextView(tag);
    }


    // --------------
    // FRAGMENTS
    // --------------

    private void configureAndShowDetailFragment(){
        // Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detail);

        if (detailFragment == null) {
            // Create new main fragment
            detailFragment = new DetailFragment();
            // Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detail, detailFragment)
                    .commit();
        }
    }

}