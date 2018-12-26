package com.openclassrooms.netapp.Utils;

import com.openclassrooms.netapp.Models.GithubUser;

import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.Observable;

public class GithubStreams {
    public static Observable<List<GithubUser>> streamFetchUserFollowing(String username){
        GithubService  githubService = GithubService.retrofit.create(GithubService.class);

        return githubService.getFollowing(username)
                .subscribeOn(Schedulers.io())// Run automatically in a new thread
                .observeOn(AndroidSchedulers.mainThread())// Listen on main thread
                .timeout(10,TimeUnit.SECONDS);// Return a timeout after 10 sec
    }
}
