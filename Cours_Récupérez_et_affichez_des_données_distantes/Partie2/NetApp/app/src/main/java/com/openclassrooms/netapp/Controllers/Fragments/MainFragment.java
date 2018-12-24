package com.openclassrooms.netapp.Controllers.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.netapp.Models.GithubUser;
import com.openclassrooms.netapp.R;
import com.openclassrooms.netapp.Utils.GithubCalls;
import com.openclassrooms.netapp.Utils.GithubService;
import com.openclassrooms.netapp.Utils.NetworkAsyncTask;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements NetworkAsyncTask.Listeners,GithubCalls.Callbacks {

    // FOR DESIGN
    @BindView(R.id.fragment_main_textview) TextView textView;

    public MainFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    // -----------------
    // ACTIONS
    // -----------------

    @OnClick(R.id.fragment_main_button)
    public void submit(View view) {
       // executeHttpRequest();
        executeHttpRequestWithRetrofit();
    }

    // -----------------
    // HTTP REQUEST
    // -----------------
    @Override
    public void onResponse(@Nullable List<GithubUser> users) {
        if (users != null) {
            this.updateUIWithListOfUsers(users);
        }
        else {
            this.updateUIWhenStoppingHttpRequest("Unknow user");
        }
    }

    @Override
    public void onFailure() {
        this.updateUIWhenStoppingHttpRequest("Sorry, an error occured !");
        
    }

    private void executeHttpRequestWithRetrofit(){
        this.updateUIWhenStartHttpRequest();
        GithubCalls.fetchUserFollowing(this,"jakeWharton");
    }

    //-------

    private void executeHttpRequest(){
        new NetworkAsyncTask(this).execute("https://api.github.com/users/JackeWharton/following");
    }

    @Override
    public void onPreExecute() {
        this.updateUIWhenStartHttpRequest();
    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onPostExecute(String json) {
        this.updateUIWhenStoppingHttpRequest(json);
    }

    // -----------------
    // Update UI
    // -----------------
    private void updateUIWhenStartHttpRequest(){
        this.textView.setText("Downloading ...");
    }

    private void updateUIWhenStoppingHttpRequest(String response){
        this.textView.setText(response);
    }

    private void updateUIWithListOfUsers(List<GithubUser> users) {

        StringBuilder stringBuilder = new StringBuilder();
        for (GithubUser user :users){
        stringBuilder.append("@" + user.getLogin() + ", ");
        }
        updateUIWhenStoppingHttpRequest(stringBuilder.toString());
    }
}
