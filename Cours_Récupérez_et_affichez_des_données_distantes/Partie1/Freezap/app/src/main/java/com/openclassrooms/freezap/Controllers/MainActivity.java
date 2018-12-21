package com.openclassrooms.freezap.Controllers;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.openclassrooms.freezap.R;
import com.openclassrooms.freezap.Utils.MyAlarmReceiver;
import com.openclassrooms.freezap.Utils.MyAsyncTask;
import com.openclassrooms.freezap.Utils.MyAsyncTaskLoader;
import com.openclassrooms.freezap.Utils.MyHandlerThread;
import com.openclassrooms.freezap.Utils.SyncJobService;
import com.openclassrooms.freezap.Utils.Utils;

public class MainActivity extends AppCompatActivity implements MyAsyncTask.Listeners, LoaderManager.LoaderCallbacks<Long> {

    private static int TASK_ID = 100;
    private static int JOBSCHEDULER_ID = 200;

    //FOR DESIGN
    private ProgressBar progressBar;

    //FOR DATA
    private MyHandlerThread handlerThread;

    // Create a intent to execute broadcast
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get progressbar from layout
        this.progressBar = findViewById(R.id.activity_main_progress_bar);

        //Configure Handler Thread
        this.configureHandlerThread();

        //Try to resume possible loading AsyncTask
        this.resumeAsyncTaskLoaderIfPossible();

        this.configureAlarmManager();
    }

    @Override
    protected void onDestroy() {
        handlerThread.quit(); // QUIT HANDLER THREAD (Free precious resources)
        super.onDestroy();
    }

    // ------------
    // ACTIONS
    // ------------

    public void onClickButton(View v){
        int buttonTag = Integer.valueOf(v.getTag().toString());
        switch (buttonTag){
            case 10: // CASE USER CLICKED ON BUTTON "EXECUTE ACTION IN MAIN THREAD"
                Utils.executeLongActionDuring7seconds();
                break;
            case 20: // CASE USER CLICKED ON BUTTON "EXECUTE ACTION IN BACKGROUND"
                this.startHandlerThread();
                break;
            case 30:
                this.startAlarm();
                break;
            case 40:
                this.stopAlarm();
                break;
            case 50:
                this.startJobScheduler();
                break;
            case 60: // CASE USER CLICKED ON BUTTON "EXECUTE ASYNCTASK"
                this.startAsyncTask();
                break;
            case 70: // CASE USER CLICKED ON BUTTON "EXECUTE ASYNCTASKLOADER"
                this.startAsyncTaskLoader();
                break;
        }
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    // Configuring the HandlerThread
    private void configureHandlerThread(){
        handlerThread = new MyHandlerThread("MyAwesomeHanderThread", this.progressBar);
    }

    //Configure AlarmManager
    private void configureAlarmManager(){
        Intent alarmIntent = new Intent(MainActivity.this,MyAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
    }
    // -------------------------------------------
    // SCHEDULE TASK (AlarmManager & JobScheduler)
    // -------------------------------------------

    // Create Alarm
    private void startAlarm(){
        AlarmManager myAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        myAlarm.setRepeating(AlarmManager.RTC_WAKEUP,0,AlarmManager.INTERVAL_FIFTEEN_MINUTES,pendingIntent);
        Toast.makeText(this, "Alarm Set !!!!", Toast.LENGTH_SHORT).show();
    }

    //stop Alarm
    private void stopAlarm() {
        AlarmManager myAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        myAlarm.cancel(pendingIntent);
        Toast.makeText(this, "Alarm canceled !!!!", Toast.LENGTH_SHORT).show();
    }

    // 2 - Start service (job) from the JobScheduler
    private void startJobScheduler(){
        // 2.1 Create a builder that will build an object JobInfo containing launching conditions and the service
        JobInfo job = new JobInfo.Builder(JOBSCHEDULER_ID, new ComponentName(this, SyncJobService.class))
                .setRequiresCharging(true) // The job will be executed if charging
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // The job will be executed if any network is available
                .setPeriodic(15*60*1000) // The job will be executed as periodic time
                .build();

        // 2.2 - Get the JobScheduler and schedule the previous job
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(job);
    }

    // 3 - Stop service (job) from the JobScheduler
    private void stopJobScheduler(){
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(JOBSCHEDULER_ID);
    }

    // -------------------------------------------
    // BACKGROUND TASK (HandlerThread & AsyncTask)
    // -------------------------------------------

    // EXECUTE HANDLER THREAD
    private void startHandlerThread(){
        handlerThread.startHandler();
    }

    // ------

    // EXECUTE ASYNC TASK
    private void startAsyncTask() {
        new MyAsyncTask(this).execute();
    }

    @Override
    public void onPreExecute() {
        this.updateUIBeforeTask();
    }

    @Override
    public void doInBackground() { }

    @Override
    public void onPostExecute(Long taskEnd) {
        this.updateUIAfterTask(taskEnd);
    }

    // ----

    private void startAsyncTaskLoader(){
        getSupportLoaderManager().restartLoader(TASK_ID, null, this);
    }

    private void resumeAsyncTaskLoaderIfPossible(){
        if (getSupportLoaderManager().getLoader(TASK_ID) != null && getSupportLoaderManager().getLoader(TASK_ID).isStarted()) {
            getSupportLoaderManager().initLoader(TASK_ID, null, this);
            this.updateUIBeforeTask();
        }
    }

    @Override
    public Loader<Long> onCreateLoader(int id, Bundle args) {
        Log.e("TAG", "On Create !");
        this.updateUIBeforeTask();
        return new MyAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Long> loader, Long data) {
        Log.e("TAG", "On Finished !");
        loader.stopLoading();
        updateUIAfterTask(data);
    }

    @Override
    public void onLoaderReset(Loader<Long> loader) { }

    // -----------------
    // UPDATE UI
    // -----------------

    public void updateUIBeforeTask(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void updateUIAfterTask(Long taskEnd){
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Task is finally finished at : "+taskEnd+" !", Toast.LENGTH_SHORT).show();
    }
}
