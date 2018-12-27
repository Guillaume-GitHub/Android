package com.openclassrooms.netapp.Controllers.Fragments;


import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.openclassrooms.netapp.Models.GithubUser;
import com.openclassrooms.netapp.Models.GithubUserInfos;
import com.openclassrooms.netapp.R;
import com.openclassrooms.netapp.Utils.GithubStreams;
import com.openclassrooms.netapp.Views.GithubUserAdapter;

import java.util.ArrayList;
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

    // Declare recyclerView
    @BindView(R.id.fragment_main_recyclerView) RecyclerView recyclerView;

    // Declare swipeToRefresh
    @BindView(R.id.fragment_main_refresh_container) SwipeRefreshLayout swipeRefreshLayout;

    // 4 - Declare subscription
    private Disposable disposable;
    // Declare List of <GithubUser> and Adapter
    private List<GithubUser> githubUsers;
    private GithubUserAdapter adapter;



    public MainFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        // configure RecyclerView
        this.configureRecyclerView();
        // configure swipeToRefresh
        this.configureSwipeToRefresh();
        // execute Stream to get data
        this.executeHttpRequestWithRetrofit();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.destroyStream();
    }


    // ----------------------------
    // CONFIGURATION RECYCLERVIEW
    // ----------------------------
    private void configureRecyclerView(){
        // 1 Reset list of GithubUser
        this.githubUsers = new ArrayList<>();
        // 2 Create adapter and passing GithubUser List + glide
        this.adapter = new GithubUserAdapter(this.githubUsers, Glide.with(this));
        // 3 Attache adapter to RecyclerView
        this.recyclerView.setAdapter(this.adapter);
        // 4 Set layout Manager to position Item
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    // ----------------------------
    // CONFIGURATION SWIPETOREFRESH
    // ----------------------------
    private void configureSwipeToRefresh() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit();
            }
        });
    }


    // -----------------
    // Http RxJAVA
    // -----------------

    // 1 - Execute Stream
    private void executeHttpRequestWithRetrofit(){

        this.disposable = GithubStreams.streamFetchUserFollowing("jakewharton")
                .subscribeWith(new DisposableObserver<List<GithubUser>>(){

                    @Override
                    public void onNext(List<GithubUser> githubUsers) {
                        Log.e("TAG", "onNext");
                        // 1.3 - Update UI with list of users
                        updateUI(githubUsers);
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

    private void updateUI(List<GithubUser> users) {
        // Stop refreshing and clear actual list
        this.swipeRefreshLayout.setRefreshing(false);
        this.githubUsers.clear();
        this.githubUsers.addAll(users);
        this.adapter.notifyDataSetChanged();
        Log.e("TAG", "updateUI");
    }

    private void destroyStream() {
        if (this.disposable != null && !this.disposable.isDisposed())
            this.disposable.dispose();
    }

}
