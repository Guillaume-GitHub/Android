package com.openclassrooms.netapp.Views;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.netapp.Models.GithubUser;
import com.openclassrooms.netapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubUserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_main_item_title) TextView titleTextView;

    @BindView(R.id.fragment_main_item_image) ImageView imageView;

    @BindView(R.id.fragment_main_item_website)TextView urlTextView;
    public GithubUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithGithubUser(GithubUser githubUser, RequestManager glide){
        this.titleTextView.setText(githubUser.getLogin());
        // Update UI
        this.urlTextView.setText(githubUser.getHtmlUrl());
        glide.load(githubUser.getAvatarUrl()).apply(RequestOptions.circleCropTransform()).into(imageView);// to download image from url
    }

}
