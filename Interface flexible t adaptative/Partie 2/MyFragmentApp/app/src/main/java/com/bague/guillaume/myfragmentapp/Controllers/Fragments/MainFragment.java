package com.bague.guillaume.myfragmentapp.Controllers.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bague.guillaume.myfragmentapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    //2 - Declare callback
    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout of fragment_main
        View result = inflater.inflate(R.layout.fragment_main,container);
        // set OnClickListener to button
        result.findViewById(R.id.fragment_main_Button).setOnClickListener(this);
        return result;
    }

    @Override
    public void onClick(View v) {
        //4 - Spread the click to the parent activity
        Log.d(getClass().getSimpleName(),"button clicked");
        if (mListener != null) {
            mListener.onFragmentInteraction(v);
        }
    }

    /////////////////////////
    ////FRAGMENT SUPPORT////
    ///////////////////////

    // 3 - create callback to parent activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    // 1 - Declare our interface that will be implemented by any container activity
    public interface OnFragmentInteractionListener{
        void onFragmentInteraction(View view);

    }
}