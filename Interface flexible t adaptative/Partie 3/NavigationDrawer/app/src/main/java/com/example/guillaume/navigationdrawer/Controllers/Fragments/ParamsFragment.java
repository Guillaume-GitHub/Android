package com.example.guillaume.navigationdrawer.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guillaume.navigationdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParamsFragment extends Fragment {


    public ParamsFragment() {
        // Required empty public constructor
    }

    public static ParamsFragment newInstance() {

        return (new ParamsFragment());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_params, container, false);
    }

}
