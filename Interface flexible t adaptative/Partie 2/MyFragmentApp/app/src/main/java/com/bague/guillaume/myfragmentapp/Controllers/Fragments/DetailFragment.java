package com.bague.guillaume.myfragmentapp.Controllers.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bague.guillaume.myfragmentapp.Controllers.Activities.DetailActivity;
import com.bague.guillaume.myfragmentapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

public class DetailFragment extends Fragment {

    //Declare TextView (with ButterKnife)
    @BindView(R.id.textView_Detail) TextView mTextView;

    // 1 - Declare a buttonTag tracking
    @State int mTag;

    // 2 - Create static variable to identify key in Bundle
    private static final String KEY_BUTTONTAG = "DetailFragment.KEY_BUTTONTAG";

    public DetailFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this,v);
        return v;
    }

    // check if a Tag were saved and update TextView
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Restore all @State annotation variable in Bundle
        Icepick.restoreInstanceState(this,savedInstanceState);
            updateTextView(mTag);
    }

    // save tag in bundle
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save All @State annotation variable in Bundle
        Icepick.saveInstanceState(this,outState);
    }

    public void updateTextView(int buttonTag) {
        //Save last tag
        mTag = buttonTag;

        switch (buttonTag){
            case 10:
                this.mTextView.setText("You're a very good programmer !");
                break;
            case 20:
                this.mTextView.setText("I do believe that Jon Snow is going to die in next season...");
                break;
            case 30:
                this.mTextView.setText("Maybe Game of Thrones next season will get back in 2040 ?");
                break;
            default:
                break;
        }
    }
}

