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

public class DetailFragment extends BaseFragment {

    //Declare TextView (with ButterKnife)
    @BindView(R.id.textView_Detail) TextView mTextView;
    // Declare a buttonTag tracking
    @State int mTag;


    //------------
    //BASE METHODS
    //------------
    @Override
    protected BaseFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void configureDesign() {

    }

    @Override
    protected void updateDesign() {
        updateTextView(mTag);
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

