package com.example.classmere.classmere;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.classmere.classmere.Utilities.NetworkUtils;

import java.io.IOException;

/**
 * Created by poj on 3/20/18.
 */

public class CourseSearchLoader extends AsyncTaskLoader<String> {
    private static final String TAG = "CourseSearchLoader: ";

    private String mCourseResultsJSON;
    private String mCourseSearchURL;

    CourseSearchLoader(Context context, String url) {
        super(context);
        mCourseSearchURL = url;
    }

    @Override
    protected void onStartLoading() {
        if(mCourseSearchURL != null) {
            if(mCourseResultsJSON != null) {
                Log.d(TAG, "Loader returning cached results. ");
                deliverResult(mCourseResultsJSON);
            }
            else {
                Log.d(TAG, "forceLoad() called. ");
                forceLoad();
            }
        }
    }

    @Override
    public String loadInBackground() {
        if(mCourseSearchURL != null) {
            Log.d(TAG, "Loading results from Classmere with URL: " + mCourseSearchURL);
            String courseResults = null;
            try {
                courseResults = NetworkUtils.doHTTPGet(mCourseSearchURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return courseResults;
        }
        else {
            Log.d(TAG, "null has been returned ");
            return null;
        }
    }

    @Override
    public void deliverResult(String data) {
        mCourseResultsJSON = data;
        super.deliverResult(data);
    }
}
