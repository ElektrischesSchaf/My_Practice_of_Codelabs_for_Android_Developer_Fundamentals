package com.example.android.getwebsourcecode;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;



public class FetchBook extends AsyncTask<String, Void, String> {

    private  WeakReference<TextView> mWebSource;

    /*  Book Search
    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthorText;

    FetchBook(TextView titleText, TextView authorText) {
        this.mTitleText = new WeakReference<>(titleText);
        this.mAuthorText = new WeakReference<>(authorText);
    }
    */
    FetchBook(TextView show_web_source) {
       // this.mTitleText = new WeakReference<>(titleText);
        this.mWebSource = new WeakReference<>(show_web_source);
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d("doInBackground",strings[0]);
        return NetworkUtils.getBookInfo(strings[0]);

    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);

        Log.d("onPost_s",s);
        mWebSource.get().setText(s);

        /* Book Search
        try
        {

            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            int i = 0;
            String title = null;
            String authors = null;
            while (i < itemsArray.length() &&
                    (authors == null && title == null)) {
                // Get the current item information.
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Move to the next item.
                i++;

            }

            if (title != null && authors != null) {
                mTitleText.get().setText(title);
                mAuthorText.get().setText(authors);
            }
            else {
            mTitleText.get().setText(R.string.no_results);
            mAuthorText.get().setText("");
            }
        }
        catch (JSONException e)
        {
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
            mTitleText.get().setText(R.string.no_results);
            mAuthorText.get().setText("");
            e.printStackTrace();
        }
        */
    }
}

