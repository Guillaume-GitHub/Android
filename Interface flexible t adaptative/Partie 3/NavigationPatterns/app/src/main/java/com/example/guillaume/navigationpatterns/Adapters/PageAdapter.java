package com.example.guillaume.navigationpatterns.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guillaume.navigationpatterns.Controllers.Fragments.NewsPageFragment;
import com.example.guillaume.navigationpatterns.Controllers.Fragments.ParamPageFragment;
import com.example.guillaume.navigationpatterns.Controllers.Fragments.ProfilePageFragment;

public class PageAdapter extends FragmentPagerAdapter {

    // Default constructor
    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    // set number of pages
    @Override
    public int getCount() {
        return 3;
    }

    //page to return
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return NewsPageFragment.newInstance();

            case 1:
                return ParamPageFragment.newInstance();

            case 2:
                return ProfilePageFragment.newInstance();

            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "News";

            case 1:
                return "Params";

            case 2:
                return "Profile";

            default:
                return null;
        }
    }
}
