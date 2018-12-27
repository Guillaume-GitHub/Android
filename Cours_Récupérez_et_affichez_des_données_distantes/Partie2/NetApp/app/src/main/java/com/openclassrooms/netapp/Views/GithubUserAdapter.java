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
    // 3 - Passing an instance of this callback to constructor
    public GithubUserAdapter(List<GithubUser> Users, RequestManager glide, Listener callback) {
        this.githubUsers = Users;
        this.mGlide = glide;
        this.callback = callback;
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
    // 4 - Passing an instance of callback through each ViewHolder
    @Override
    public void onBindViewHolder(GithubUserViewHolder viewHolder, int position) {
        viewHolder.updateWithGithubUser(this.githubUsers.get(position),this.mGlide,this.callback);

    }

    //Return total of item in list
    @Override
    public int getItemCount() {
        return githubUsers.size();
    }

    public GithubUser getUser(int userPosition){

        return this.githubUsers.get(userPosition);
    }
    //--------------------------
    // Create Callback interface
    //--------------------------

    //1
    public interface Listener {

       void  onClickDeleteButton(int position);
    }

    // 2 Declaring callback
    private final Listener callback;



}
