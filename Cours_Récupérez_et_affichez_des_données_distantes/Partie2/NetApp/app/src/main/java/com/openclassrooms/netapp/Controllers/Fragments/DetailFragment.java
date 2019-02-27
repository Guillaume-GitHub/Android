package com.openclassrooms.netapp.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.netapp.Models.GithubUserInfos;
import com.openclassrooms.netapp.R;
import com.openclassrooms.netapp.Utils.GithubStreams;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    @BindView(R.id.fragment_detail_follower) TextView followerTextView;
    @BindView(R.id.fragment_detail_imageView) ImageView imageView;
    @BindView(R.id.fragment_detail_name) TextView nameTextView;
    @BindView(R.id.fragment_detail_following) TextView followingTextView;
    @BindView(R.id.fragment_detail_repos) TextView reposTextView;

    private Glide glide;
    private DetailFragment detailFragment = this;

    private String username = "jakewharton";

    private Disposable disposable;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("TAG", "onCreateView:" );
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this,view);

        this.executeHttpRequest(MainFragment.BUNDLE_USERNAME_VALUE);
        return view;
    }

    private void executeHttpRequest(String username) {
        Log.e("TAG", "executeHttpRequest: " );
        this.disposable = GithubStreams.streamFetchUserInfos(username)
                .subscribeWith(new DisposableObserver<GithubUserInfos>() {


            @Override
            public void onNext(GithubUserInfos userInfos) {

                Log.e("TAG", "onNext");
                updateUIWithUserInfo(userInfos);

            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "onComplete: ");
            }
        });

    }

    private void updateUIWithUserInfo(GithubUserInfos userInfos){
        glide.with(this.detailFragment).load(userInfos.getAvatarUrl()).apply(RequestOptions.circleCropTransform()).into(this.imageView);
        this.nameTextView.setText(userInfos.getName());
        this.followingTextView.setText(String.valueOf(userInfos.getFollowing()));
        this.followerTextView.setText(String.valueOf(userInfos.getFollowers()));
        this.reposTextView.setText(String.valueOf(userInfos.getPublicRepos()));

    }

}
