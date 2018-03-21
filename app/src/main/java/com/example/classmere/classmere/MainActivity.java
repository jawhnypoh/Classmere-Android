package com.example.classmere.classmere;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.classmere.classmere.Utilities.ClassmereUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CourseAdapter.OnCourseItemClickListener, LoaderManager.LoaderCallbacks<String> {

    public static final String TAG = "MainActivity: ";

    private static final String COURSE_SEARCH_KEY = "courseSearchURL";

    public int CLASSMERE_SEARCH_LOADER_ID = 0;

    private EditText mSearchBoxET;
    private ProgressBar mLoadingProgressBar;
    private TextView mLoadingErrorMessage;
    private RecyclerView mSearchResultsRV;
    private CourseAdapter mCourseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mLoadingErrorMessage = (TextView)findViewById(R.id.tv_loading_error);

        mSearchBoxET = (EditText)findViewById(R.id.et_search_box);
        mSearchResultsRV = (RecyclerView)findViewById(R.id.rv_search_results);

        mSearchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        mSearchResultsRV.setHasFixedSize(true);

        mCourseAdapter = new CourseAdapter(this);
        mSearchResultsRV.setAdapter(mCourseAdapter);

        Button searchButton = (Button) findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    doCourseSearch(searchQuery);
                }
            }
        });
    }

    private void doCourseSearch(String searchQuery) {
        Log.d(TAG, "searchButton clicked and called doCourseSearch()! ");

        String courseSearchURL = ClassmereUtils.buildClassmereURL(searchQuery);
        Bundle args = new Bundle();
        args.putString(COURSE_SEARCH_KEY, courseSearchURL);
        mLoadingProgressBar.setVisibility(View.VISIBLE);
        getSupportLoaderManager().restartLoader(CLASSMERE_SEARCH_LOADER_ID, args, this);

        Log.d(TAG, "courseSearchURL is: " + courseSearchURL);
    }

    @Override
    public void onCourseItemClick(ClassmereUtils.CourseItem courseItem) {
        Intent detailedCourseResultIntent = new Intent(this, detailedCourseResultActivity.class);
        detailedCourseResultIntent.putExtra(ClassmereUtils.EXTRA_COURSE_RESULT, courseItem);
        startActivity(detailedCourseResultIntent);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        String courseSearchURL = null;
        if(args != null) {
            courseSearchURL = args.getString(COURSE_SEARCH_KEY);
        }
        return new CourseSearchLoader(this, courseSearchURL);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        mLoadingProgressBar.setVisibility(View.INVISIBLE);

        Log.d(TAG, "Got results from loader. ");
        if(data != null) {
            mLoadingProgressBar.setVisibility(View.INVISIBLE);
            mSearchResultsRV.setVisibility(View.VISIBLE);
            ArrayList<ClassmereUtils.CourseItem> courseItems = ClassmereUtils.parseCourseJSON(data);
            mCourseAdapter.updateCourseItems(courseItems);
        }
        else {
            mSearchResultsRV.setVisibility(View.INVISIBLE);
            mLoadingErrorMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        // Nothing to do here
    }
}
