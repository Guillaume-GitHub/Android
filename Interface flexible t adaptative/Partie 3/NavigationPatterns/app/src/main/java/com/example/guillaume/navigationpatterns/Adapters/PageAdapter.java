package com.example.guillaume.navigationpatterns.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guillaume.navigationpatterns.Controllers.Fragments.PagerFragment;

public class PageAdapter extends FragmentPagerAdapter {

    // Array of colors
    private int colors[];

    // Default constructor
    public PageAdapter(FragmentManager fm, int[] colors) {
        super(fm);
        this.colors = colors;
    }

    // set number of pages
    @Override
    public int getCount() {
        return 5;
    }

    //page to return
    @Override
    public Fragment getItem(int position) {
        return PagerFragment.newInstance(position,this.colors[position]);
    }
}
