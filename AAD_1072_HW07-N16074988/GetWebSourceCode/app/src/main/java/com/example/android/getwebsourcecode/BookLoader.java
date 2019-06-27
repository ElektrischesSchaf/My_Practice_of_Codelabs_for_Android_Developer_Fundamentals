package com.example.android.getwebsourcecode;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class BookLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    @Override
    protected void onStartLoading() {
        Log.d("Loader","onStartLoading");
        super.onStartLoading();
        forceLoad();
    }

    public BookLoader(Context context,  String queryString) {

        super(context);
        Log.d("Loader","BookLoader");
        mQueryString = queryString;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        Log.d("Loader","loadinBackRound");
        return NetworkUtils.getBookInfo(mQueryString);
    }


}
