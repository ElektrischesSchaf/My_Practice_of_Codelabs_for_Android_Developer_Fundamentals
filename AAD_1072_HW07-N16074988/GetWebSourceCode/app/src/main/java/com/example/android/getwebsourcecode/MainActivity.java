package com.example.android.getwebsourcecode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v4.app.LoaderManager;

/* AsyncTask
public class MainActivity extends AppCompatActivity
*/


public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String>

{


    private EditText mBookInput;

    /* Book search
    private TextView mTitleText;
    private TextView mAuthorText;
    */
    private  TextView mWebSource;

    public  boolean is_https_selected=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        final String[] http_or_https = {"http://", "https://"};
        ArrayAdapter<String> httpsList = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                http_or_https);
        spinner.setAdapter(httpsList);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "你選的是" + http_or_https[position], Toast.LENGTH_SHORT).show();
                if(position==1)
                {
                    is_https_selected=true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mBookInput = (EditText)findViewById(R.id.bookInput);

        /*
        mTitleText = (TextView)findViewById(R.id.titleText);
        mAuthorText = (TextView)findViewById(R.id.authorText);
           */
        mWebSource=findViewById(R.id.show_web_source);

    }

    public void searchBooks(View view)
    {
        String queryString = mBookInput.getText().toString();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null ) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager)  getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {

            if(is_https_selected==true) {
               // new FetchBook(mWebSource).execute("https:"+"/"+"/"+queryString);

                Bundle queryBundle = new Bundle();
                queryBundle.putString("queryString", "https:"+"/"+"/"+queryString);
                getSupportLoaderManager().restartLoader(0, queryBundle, this);

            }
            else
            {
                // new FetchBook(mWebSource).execute("http:"+"/"+"/"+queryString);

                Bundle queryBundle = new Bundle();
                queryBundle.putString("queryString", "http:"+"/"+"/"+queryString);
                getSupportLoaderManager().restartLoader(0, queryBundle, this);
            }
            // Books Search
            //  new FetchBook(mTitleText, mAuthorText).execute(queryString);
            //  mAuthorText.setText("");
            //  mTitleText.setText(R.string.loading);
        }
        else {
            if (queryString.length() == 0) {
                mWebSource.setText(R.string.no_search_term);

                //  mAuthorText.setText("");
                //  mTitleText.setText(R.string.no_search_term);

            } else {
                mWebSource.setText(R.string.no_network);

                //     mAuthorText.setText("");
                //    mTitleText.setText(R.string.no_network);


            }
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";

        if (args != null) {
            queryString = args.getString("queryString");
        }

        return new BookLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            Log.d("onLoadFinished", data);
            mWebSource.setText(data);
        }
        catch (Exception e) {
            mWebSource.setText(R.string.no_results);
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    //Used for AsyncTask
    /*
    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null ) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager)  getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {

            if(is_https_selected==true) {
                new FetchBook(mWebSource).execute("https:"+"/"+"/"+queryString);
            }
            else
            {
                new FetchBook(mWebSource).execute("http:"+"/"+"/"+queryString);
            }
          // Books Search
          //  new FetchBook(mTitleText, mAuthorText).execute(queryString);
          //  mAuthorText.setText("");
          //  mTitleText.setText(R.string.loading);
        }
        else {
            if (queryString.length() == 0) {
                mWebSource.setText(R.string.no_search_term);

              //  mAuthorText.setText("");
              //  mTitleText.setText(R.string.no_search_term);

            } else {
                mWebSource.setText(R.string.no_network);

           //     mAuthorText.setText("");
            //    mTitleText.setText(R.string.no_network);


            }
        }
    }
    */

}
