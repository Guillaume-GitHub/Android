package com.openclassrooms.netapp.Views;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.netapp.Models.GithubUser;
import com.openclassrooms.netapp.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.fragment_main_item_title) TextView titleTextView;

    @BindView(R.id.fragment_main_item_image) ImageView imageView;

    @BindView(R.id.fragment_main_item_website)TextView urlTextView;

    @BindView(R.id.fragment_main_item_delete) ImageButton imageButton;

    // 2 - Declare a Weak Reference to our Callback
    private WeakReference<GithubUserAdapter.Listener> callbackWeakRef;

    public GithubUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithGithubUser(GithubUser githubUser, RequestManager glide, GithubUserAdapter.Listener callback){
        this.titleTextView.setText(githubUser.getLogin());
        // Update UI
        this.urlTextView.setText(githubUser.getHtmlUrl());
        glide.load(githubUser.getAvatarUrl()).apply(RequestOptions.circleCropTransform()).into(imageView);// to download image from url

        //3 - Implement Listener on ImageButton
        this.imageButton.setOnClickListener(this);

        //4 - Create a new weak Reference to our Listener
        this.callbackWeakRef = new WeakReference<GithubUserAdapter.Listener>(callback);
    }

    @Override
    public void onClick(View view) {
        // 5 - When a click happens, we fire our listener.
        GithubUserAdapter.Listener callback = callbackWeakRef.get();
        if (callback != null) callback.onClickDeleteButton(getAdapterPosition());
    }
}
