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
import io.reactivex.Observable;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements NetworkAsyncTask.Listeners,GithubCalls.Callbacks {

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
       // executeHttpRequest();
       // executeHttpRequestWithRetrofit();
        this.steamShowString();
    }

    // -----------------
    // Reactive X
    // -----------------

    // 1 - Create Observable
    private Observable<String> getObservable(){

      return Observable.just("Cool ");
    }

    // 2 - Create an Observer (or Subscriber)
    private DisposableObserver<String> getObserver(){
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String item) {
                textView.setText(item);
            }

            @Override
            public void onError(Throwable error) {
                Log.e("TAG", "onError ! ");
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "onComplete ! ");
            }
        };
    }

    // 3 - Create Stream
    private void steamShowString(){
        this.disposable = this.getObservable()
                .map(resultToUpperCase()) // Apply function
                .flatMap(addSecondObservable())// Adding Observable
                .subscribeWith(getObserver());
    }

    // 4 - Dispose subscription to skip MemoryLeaks
    private void destroyStream(){
        if(this.disposable != null && !this.disposable.isDisposed())
            this.disposable.dispose();
    }

    // Add to map in steam to uppercase Values
    private Function<String, String> resultToUpperCase() { // first param : Input type , second param : output type
        return new Function<String, String>() {
            @Override
            public String apply(String str) throws Exception {
                return str.toUpperCase();
            }
        };
    }

    // Add second observable to the first
    private Function<String, Observable<String>> addSecondObservable() {
        return new Function<String, Observable<String>>() {
            @Override
            public Observable<String> apply(String previousString) throws Exception {
                return Observable.just(previousString + " I love … something");
            }
        };
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
