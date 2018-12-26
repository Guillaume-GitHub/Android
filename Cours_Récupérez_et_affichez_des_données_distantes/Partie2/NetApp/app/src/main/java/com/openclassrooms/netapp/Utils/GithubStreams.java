package com.openclassrooms.netapp.Utils;

import com.openclassrooms.netapp.Models.GithubUser;
import com.openclassrooms.netapp.Models.GithubUserInfos;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.Observable;

public class GithubStreams {

    // Stream that will get Followers of an user
    public static Observable<List<GithubUser>> streamFetchUserFollowing(String username){

        GithubService githubService = GithubService.retrofit.create(GithubService.class);

        return githubService.getFollowing(username)
                .subscribeOn(Schedulers.io())// Run automatically in a new thread
                .observeOn(AndroidSchedulers.mainThread())// Listen on main thread
                .timeout(10,TimeUnit.SECONDS);// Return a timeout after 10 sec
    }

    // Stream that will get Followers User's information
    public static Observable<GithubUserInfos> streamFetchUserInfos(String username){

        GithubService githubService = GithubService.retrofit.create(GithubService.class);

        return githubService.getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10,TimeUnit.SECONDS);
    }


    public static Observable<GithubUserInfos> streamFetchUserFollowingAndFetchFirstUserInfos(String username){

        return streamFetchUserFollowing(username)
                .map(new Function<List<GithubUser>, GithubUser>() {
                            @Override
                            public GithubUser apply(List<GithubUser> githubUser) throws Exception {
                                return githubUser.get(0);
                            }
                        })

                .flatMap(new Function<GithubUser, Observable<GithubUserInfos>>() {
                    @Override
                    public Observable<GithubUserInfos> apply(GithubUser githubUser) throws Exception {
                        return streamFetchUserInfos(githubUser.getLogin());
                    }
                });
    }

}
