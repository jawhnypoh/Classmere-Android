package com.example.classmere.classmere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.classmere.classmere.Utilities.ClassmereUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by poj on 3/20/18.
 */

public class detailedCourseResultActivity extends AppCompatActivity implements CourseSectionAdapter.OnCourseSectionItemClickListener, OnMapReadyCallback {

    private static final String TAG = "detailedCourseActivity: ";

    private TextView mTVCourseResultTitle;
    private TextView mTVCourseResultCredits;
    private TextView mTVCouresResultDescription;

    private RecyclerView mSectionResultsRV;
    private CourseSectionAdapter mCourseSectionAdapter;

    private ClassmereUtils.CourseItem mCourseItem;

    private String searchQuery = "cs";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_result_detail);

        mTVCourseResultTitle = (TextView) findViewById(R.id.tv_course_title);
        mTVCourseResultCredits = (TextView) findViewById(R.id.tv_course_credits);
        mTVCouresResultDescription = (TextView) findViewById(R.id.tv_course_description);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(ClassmereUtils.EXTRA_COURSE_RESULT)) {
            mCourseItem = (ClassmereUtils.CourseItem) intent.getSerializableExtra(ClassmereUtils.EXTRA_COURSE_RESULT);
            mTVCourseResultTitle.setText(mCourseItem.className);
            mTVCouresResultDescription.setText(mCourseItem.description);
            mTVCourseResultCredits.setText(mCourseItem.credits + " Credits");
        }

        mSectionResultsRV = (RecyclerView)findViewById(R.id.rv_section_results);

        mSectionResultsRV.setLayoutManager(new LinearLayoutManager(this));
        mSectionResultsRV.setHasFixedSize(true);

        mCourseSectionAdapter = new CourseSectionAdapter(this);
        mSectionResultsRV.setAdapter(mCourseSectionAdapter);

        mCourseSectionAdapter.updateCourseSectionItems(mCourseItem.sectionItems);

        // Get SupportMapFragment and request notification when map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onCourseSectionItemClick(ClassmereUtils.CourseItem.SectionItem sectionItem) {
        Intent detailedSectionResultIntent = new Intent(this, detailedSectionResultActivity.class);
        detailedSectionResultIntent.putExtra(ClassmereUtils.EXTRA_SECTION_RESULT, sectionItem);
        startActivity(detailedSectionResultIntent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add marker for Oregon State University, move camera to that location
        LatLng OSU = new LatLng(44.563704, -123.279474);
        googleMap.addMarker(new MarkerOptions().position(OSU)
            .title("Oregon State University"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(OSU));
    }
}
