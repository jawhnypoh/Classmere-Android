package com.example.classmere.classmere;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.classmere.classmere.Utilities.NetworkUtils;

import java.io.IOException;

/**
 * Created by jp on 4/23/18.
 */

public class CourseSectionLoader extends AsyncTaskLoader<String> {
    private static final String TAG = "CourseSectionLoader";

    private String mBuildingResultJSON;
    private String mBuildingSearchURL;

    CourseSectionLoader(Context context, String url) {
        super(context);
        mBuildingSearchURL = url;
    }

    @Override
    protected void onStartLoading() {
        if(mBuildingSearchURL != null) {
            Log.d(TAG, "Loader returning cached results. ");
            deliverResult(mBuildingResultJSON);
        }
        else {
            Log.d(TAG, "forceLoad() called. ");
            forceLoad();
        }
    }

    @Override
    public String loadInBackground() {
        if(mBuildingSearchURL != null) {
            Log.d(TAG, "Loading building results from Classmere with URL: " + mBuildingSearchURL);
            String buildingResult = null;
            try {
                buildingResult = NetworkUtils.doHTTPGet(mBuildingSearchURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buildingResult;
        }
        else {
            Log.d(TAG, "null has been returned. ");
            return null;
        }
    }

    @Override
    public void deliverResult(String data) {
        mBuildingResultJSON = data;
        super.deliverResult(data);
    }
}
