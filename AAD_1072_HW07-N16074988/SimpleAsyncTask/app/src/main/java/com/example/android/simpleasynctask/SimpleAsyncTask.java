/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.simpleasynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * The SimpleAsyncTask class extends AsyncTask to perform a very simple
 * background task -- in this case, it just sleeps for a random amount of time.
 */

public class SimpleAsyncTask extends AsyncTask<Void,Integer, String> {

    // The TextView where we will show results
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    private Context mContext;
    // Constructor that provides a reference to the TextView from the MainActivity

    SimpleAsyncTask(TextView tv,ProgressBar pv)
    {
        mTextView = new WeakReference<>(tv);
        mProgressBar=new WeakReference<>(pv);
    }

    @Override
    protected void onPreExecute() {
        //執行前 設定可以在這邊設定
        super.onPreExecute();

    }

    /**
     * Runs on the background thread.
     *
     * @param voids No parameters in this use case.
     * @return Returns the string including the amount of time that
     * the background thread slept.
     */
    @Override
    protected String doInBackground(Void... voids) {


        // Generate a random number between 0 and 10.
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running.
        int s = n * 200;

        // Sleep for the random amount of time.

        for(int i=0;i<=s;i++)
        {
            try
            {
                Thread.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            Log.d("The progress in %  ",Float.toString(   ( (i+1)/(float) s)  *100   ));
            publishProgress(     (int)    (   ( (i+1)/(float) s)  *100   )       );
        }

        // Return a String result.
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer...values)
    {
        //super.onProgressUpdate(values);
        Log.d("Tag onProgressUpdate", "In onProgressUpdate");
        mProgressBar.get().setProgress(values[0]);
    }
    /**
     * Does something with the result on the UI thread; in this case
     * updates the TextView.
     */
    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
