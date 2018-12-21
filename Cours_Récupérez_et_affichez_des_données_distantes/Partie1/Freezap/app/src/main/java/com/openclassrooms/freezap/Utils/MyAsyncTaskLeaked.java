package com.openclassrooms.freezap.Utils;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class MyAsyncTaskLeaked extends AsyncTask<Void,Void,Long> {


    private ProgressBar mProgressBar;

    public MyAsyncTaskLeaked(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.mProgressBar.setVisibility(View.VISIBLE);
        Log.e(getClass().getSimpleName(), "Task is started !!");
    }

    @Override
    protected void onPostExecute(Long success) {
        super.onPostExecute(success);
        this.mProgressBar.setVisibility(View.GONE);
        Log.e(getClass().getSimpleName(), "Task is terminated !!");

    }

    // Doing task
    @Override
    protected Long doInBackground(Void... voids) {
        Log.e(getClass().getSimpleName(), "Async Task doing job for 7sec ..." );
        return Utils.executeLongActionDuring7seconds();
    }
}
