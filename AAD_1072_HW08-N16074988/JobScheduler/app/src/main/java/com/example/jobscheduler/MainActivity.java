package com.example.jobscheduler;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int JOB_ID = 0;
    private JobScheduler mScheduler;

   // private static final int JOB_ID_2 = 2;
    private JobScheduler mScheduler_2;

    // Switches for setting job options.
    private Switch mDeviceIdleSwitch;
    private Switch mDeviceChargingSwitch;

    // Override deadline seekbar.
    private SeekBar mSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDeviceIdleSwitch = findViewById(R.id.idleSwitch);
        mDeviceChargingSwitch = findViewById(R.id.chargingSwitch);
        mSeekBar = findViewById(R.id.seekBar);

        final TextView seekBarProgress = findViewById(R.id.seekBarProgress);

        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        mScheduler_2= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        // Updates the TextView with the value from the seekbar.
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i > 0) {
                    seekBarProgress.setText(getString(R.string.seconds, i));
                } else {
                    seekBarProgress.setText(R.string.not_set);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Call_JobService();


    }

    public void Call_JobService()
    {
        ComponentName serviceName = new ComponentName(getPackageName(), NotificationJobService.class.getName());

        Log.d("In","JobService()");
        Log.d("OnCreateNotification: ",NotificationJobService.class.getName());

        int selectedNetworkOption=JobInfo.NETWORK_TYPE_UNMETERED;

        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(selectedNetworkOption)
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true);
        // setPeriodic不能和setMinimumLatency、setOverrideDeadline這兩個同時呼叫 否則會報
        // builder.setOverrideDeadline(0);
        builder.setPeriodic(24 * 60 * 60 * 1000);

        JobInfo myJobInfo = builder.build();
        mScheduler.schedule(myJobInfo);

        Log.d("Interval ",Long.toString(myJobInfo.getIntervalMillis()));
        Log.d("Idle  ",Boolean.toString(myJobInfo.isRequireDeviceIdle()));
        Log.d("Charging ",Boolean.toString(myJobInfo.isRequireCharging()));
        Log.d("Network  ",Integer.toString(myJobInfo.getNetworkType()));
        Log.d("getService()",myJobInfo.getService().toString() );
    }

    public void Call_JobService_2()
    {
        ComponentName serviceName = new ComponentName(getPackageName(), NotificationJobService.class.getName());

        Log.d("In","JobService()");
        Log.d("OnCreateNotification: ",NotificationJobService.class.getName());

        int selectedNetworkOption=JobInfo.NETWORK_TYPE_UNMETERED;

        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(selectedNetworkOption)
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true);
        builder.setOverrideDeadline(0);


        JobInfo myJobInfo = builder.build();
        mScheduler.schedule(myJobInfo);
        Log.d("Interval ",Long.toString(myJobInfo.getIntervalMillis()));
        Log.d("Idle  ",Boolean.toString(myJobInfo.isRequireDeviceIdle()));
        Log.d("Charging ",Boolean.toString(myJobInfo.isRequireCharging()));
        Log.d("Network  ",Integer.toString(myJobInfo.getNetworkType()));
        Log.d("getService()",myJobInfo.getService().toString() );
    }



    // Start Inner class
    // must use static otherwise  has no zero argument constructor
    //        at android.app.ActivityThread.handleCreateService(ActivityThread.java:3389)
    static public class NotificationJobService extends JobService {

        // Notification channel ID.
        private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
        // Notification manager.
        NotificationManager mNotifyManager;

        @Override
        public boolean onStartJob(JobParameters jobParameters) {

            Log.d("In: ","onStartJob");

            // Create the notification channel.
            createNotificationChannel();

            // Set up the notification content intent to launch the app when
            // clicked.
            PendingIntent contentPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder
                    (this, PRIMARY_CHANNEL_ID)
                    .setContentTitle(getString(R.string.job_service))
                    .setContentText(getString(R.string.job_running))
                    .setContentIntent(contentPendingIntent)
                    .setSmallIcon(R.drawable.ic_job_running)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setAutoCancel(true);

            mNotifyManager.notify(0, builder.build());

            return false;
        }


        @Override
        public boolean onStopJob(JobParameters jobParameters) {
            Log.d("In: ","onStopJob");
            return false;
        }

        public void createNotificationChannel() {

            Log.d("In: ","createNotificationChannel");

            // Create a notification manager object.
            mNotifyManager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            // Notification channels are only available in OREO and higher.
            // So, add a check on SDK version.
            if (android.os.Build.VERSION.SDK_INT >=
                    android.os.Build.VERSION_CODES.O) {

                // Create the NotificationChannel with all the parameters.
                NotificationChannel notificationChannel = new NotificationChannel
                        (PRIMARY_CHANNEL_ID, getString(R.string.job_service_notification),
                                NotificationManager.IMPORTANCE_HIGH);

                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setDescription(getString(R.string.notification_channel_description));

                mNotifyManager.createNotificationChannel(notificationChannel);
            }
        }

    }
    // End of inner class



    public void scheduleJob(View view) {
        Log.d("In :","scheduleJob");

        RadioGroup networkOptions = findViewById(R.id.networkOptions);

        int selectedNetworkID = networkOptions.getCheckedRadioButtonId();

        int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;

        int seekBarInteger = mSeekBar.getProgress();
        boolean seekBarSet = seekBarInteger > 0;

        switch (selectedNetworkID) {
            case R.id.noNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.anyNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.wifiNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }

        ComponentName serviceName = new ComponentName(getPackageName(), NotificationJobService.class.getName());
        Log.d("service name: ",NotificationJobService.class.getName());

        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(selectedNetworkOption)
                .setRequiresDeviceIdle(mDeviceIdleSwitch.isChecked())
                .setRequiresCharging(mDeviceChargingSwitch.isChecked());

        if (seekBarSet) {
            builder.setOverrideDeadline(seekBarInteger * 1000);
        }
        boolean constraintSet = selectedNetworkOption
                != JobInfo.NETWORK_TYPE_NONE
                || mDeviceChargingSwitch.isChecked()
                || mDeviceIdleSwitch.isChecked()
                || seekBarSet;

        if (constraintSet) {
            JobInfo myJobInfo = builder.build();
            mScheduler.schedule(myJobInfo);
            Toast.makeText(this, R.string.job_scheduled, Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(this, R.string.no_constraint_toast,
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * onClick method for cancelling all existing jobs.
     */
    public void cancelJobs(View view) {

        if (mScheduler != null) {
            mScheduler.cancelAll();
            mScheduler = null;
            Toast.makeText(this, R.string.jobs_canceled, Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void downloadJobs(View view)
    {
        Log.d("In :","downloadJobs");
        /*

        ComponentName serviceName_2 = new ComponentName(getPackageName(), NotificationJobService.class.getName());

        Log.d("service name: ",NotificationJobService.class.getName());

        int selectedNetworkOption=JobInfo.NETWORK_TYPE_UNMETERED;

        JobInfo.Builder builder_2 = new JobInfo.Builder(JOB_ID, serviceName_2)
                .setRequiredNetworkType(selectedNetworkOption)
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true);
        builder_2.setOverrideDeadline(0);
        //builder_2.setPeriodic(24 * 60 * 60 * 1000);

        JobInfo myJobInfo_2 = builder_2.build();
        mScheduler.schedule(myJobInfo_2);
        */
        Call_JobService_2();
        Toast.makeText(this, R.string.job_scheduled, Toast.LENGTH_SHORT).show();

    }

}
