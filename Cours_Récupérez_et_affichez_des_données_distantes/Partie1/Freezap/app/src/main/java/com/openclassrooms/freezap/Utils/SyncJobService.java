package com.openclassrooms.freezap.Utils;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class SyncJobService extends JobService implements MyAsyncTask.Listeners {

    // 1- Declare an AsyncTask and the parameters of the job
    private MyAsyncTask jobTask;
    private JobParameters jobParameters;


    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        //2 - When job start , execute AsyncTask
        this.jobParameters = jobParameters;
        this.jobTask = new MyAsyncTask(this);
        this.jobTask.execute();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.e(this.getClass().getSimpleName(), "Job stopped");
        // we cancel the AsyncTask when job stopped, mainly to avoid Memory Leaks
        if(this.jobTask != null) jobTask.cancel(true);
        return false;
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onPostExecute(Long success) {
        // 3 - When background task ended, we set the job as terminated
        Log.e("TAG", "Task ended at : "+ success);
        jobFinished(jobParameters, false);
    }
}
