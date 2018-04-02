package com.example.classmere.classmere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.classmere.classmere.Utilities.ClassmereUtils;

/**
 * Created by poj on 3/20/18.
 */

public class detailedCourseResultActivity extends AppCompatActivity {
    private static final String TAG = "detailedCourseResultActivity: ";

    private TextView mTVCourseResultTitle;
    private TextView mTVCourseResultCredits;
    private TextView mTVCouresResultDescription;
    private TextView mTVCourseResultSections;

    private ClassmereUtils.CourseItem mCourseItem;

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
            mTVCourseResultSections.setText(mCourseItem.courseSection);
        }
    }
}
