package com.example.classmere.classmere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.classmere.classmere.Utilities.ClassmereUtils;

import java.util.ArrayList;

/**
 * Created by poj on 3/20/18.
 */

public class detailedCourseResultActivity extends AppCompatActivity implements CourseSectionAdapter.OnCourseSectionItemClickListener {

    private static final String TAG = "detailedCourseActivity: ";

    private TextView mTVCourseResultTitle;
    private TextView mTVCourseResultCredits;
    private TextView mTVCouresResultDescription;
    private TextView mTVCourseResultSections;

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
        mTVCourseResultSections = (TextView) findViewById(R.id.tv_course_sections);

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
        Log.d(TAG, "sectionItems size is: " + mCourseItem.sectionItems.size());
        for(int i=0; i<mCourseItem.sectionItems.size(); i++) {
            Log.d(TAG, "courseCrn is: " + mCourseItem.sectionItems.get(i).courseCrn);
        }
    }

    @Override
    public void onCourseSectionItemClick(ClassmereUtils.CourseItem.SectionItem sectionItem) {
        Intent detailedSectionResultIntent = new Intent(this, detailedSectionResultActivity.class);
        detailedSectionResultIntent.putExtra(ClassmereUtils.EXTRA_COURSE_RESULT, sectionItem);
        startActivity(detailedSectionResultIntent);
    }
}
