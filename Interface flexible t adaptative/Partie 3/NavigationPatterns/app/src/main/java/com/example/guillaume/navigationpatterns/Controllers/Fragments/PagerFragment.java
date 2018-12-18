package com.example.guillaume.navigationpatterns.Controllers.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guillaume.navigationpatterns.R;

public class PagerFragment extends Fragment {

    // 1 - Create keys for our Bundle
    private static final String KEY_POSITION="position";
    private static final String KEY_COLOR="color";


    public PagerFragment() {
        // Required empty public constructor
    }

    public static PagerFragment newInstance(int position, int color) {
        // Create new fragment
        PagerFragment fragment = new PagerFragment();

        // Create Bundle and add it some date
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION,position);
        args.putInt(KEY_COLOR,color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_pager, container, false);

        // Get widget fo layout
        LinearLayout linearLayout = (LinearLayout) result.findViewById(R.id.fragment_page_rootview);
        TextView textView = (TextView) result.findViewById(R.id.fragment_page_title);

        // Get date from Bundle
        int position = getArguments().getInt(KEY_POSITION,0);
        int color = getArguments().getInt(KEY_COLOR,0);

        // Set values to widget (update)
        linearLayout.setBackgroundColor(color);
        textView.setText("Page n° " + position);

        return result;
    }
}
