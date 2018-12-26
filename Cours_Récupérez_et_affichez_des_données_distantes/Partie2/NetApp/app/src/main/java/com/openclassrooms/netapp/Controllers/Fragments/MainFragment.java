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
import com.openclassrooms.netapp.Utils.GithubStreams;
import com.openclassrooms.netapp.Utils.NetworkAsyncTask;
import io.reactivex.Observable;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment{

    // 4 - Declare subscription
    private Disposable disposable;

    // FOR DESIGN
    @BindView(R.id.fragment_main_textview) TextView textView;

    public MainFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.destroyStream();
    }


    // -----------------
    // ACTIONS
    // -----------------

    @OnClick(R.id.fragment_main_button)
    public void submit(View view) {
        this.executeHttpRequestWithRetrofit();

    }

    // -----------------
    // Http RxJAVA
    // -----------------

    // 1 - Execute Stream
    private void executeHttpRequestWithRetrofit(){

        //1.1 update UI
        this.updateUIWhenStartHttpRequest();

        this.disposable = GithubStreams.streamFetchUserFollowing("jakewharton")
                .subscribeWith(new DisposableObserver<List<GithubUser>>(){

                    @Override
                    public void onNext(List<GithubUser> githubUsers) {
                        Log.e("TAG", "onNext");
                        // 1.3 - Update UI with list of users
                        updateUIWithListOfUsers(githubUsers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG","On Error"+Log.getStackTraceString(e));
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG","On Complete !!");
                    }
                });

    }


    private void destroyStream() {
        if(this.disposable != null && !this.disposable.isDisposed())
            this.disposable.dispose();
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
