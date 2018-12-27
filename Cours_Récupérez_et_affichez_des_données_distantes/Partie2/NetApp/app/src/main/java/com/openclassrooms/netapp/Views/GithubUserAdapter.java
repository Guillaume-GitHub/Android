package com.openclassrooms.netapp.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.openclassrooms.netapp.Models.GithubUser;
import com.openclassrooms.netapp.R;

import java.util.List;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserViewHolder> {

    //FOR DATA
    private List<GithubUser> githubUsers;
    private RequestManager mGlide;

    //Constructor
    public GithubUserAdapter(List<GithubUser> Users, RequestManager glide) {
        this.githubUsers = Users;
        this.mGlide = glide;
    }

    @Override
    public GithubUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Create ViewHolder and Inflate his XML
        Context context = (Context) parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item,parent,false);
        return new GithubUserViewHolder(view);
    }

    //Update View Holder with githubUsers
    @Override
    public void onBindViewHolder(GithubUserViewHolder viewHolder, int position) {
        viewHolder.updateWithGithubUser(this.githubUsers.get(position),this.mGlide);

    }

    //Return total of item in list
    @Override
    public int getItemCount() {
        return githubUsers.size();
    }
}
