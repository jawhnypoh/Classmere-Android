package com.example.classmere.classmere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.classmere.classmere.Utilities.ClassmereUtils;

/**
 * Created by jp on 4/3/18.
 */

public class detailedSectionResultActivity extends AppCompatActivity {

    private static final String TAG = "detailedSectionActivity: ";

    private TextView mTVSectionResultTitle;
    private TextView mTVSectionResultCredits;
    private TextView mTVSectionResultInstructor;

    private ClassmereUtils.CourseItem mCourseItem;
    private ClassmereUtils.CourseItem.SectionItem mSectionItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_result_detail);

        mTVSectionResultTitle = (TextView) findViewById(R.id.tv_section_title);
        mTVSectionResultCredits = (TextView) findViewById(R.id.tv_section_credits);
        mTVSectionResultInstructor = (TextView) findViewById(R.id.tv_section_instructor);

        Intent intent = getIntent();
//        if(intent != null && intent.hasExtra(ClassmereUtils.EXTRA_COURSE_RESULT)) {
//            mCourseItem = (ClassmereUtils.CourseItem) intent.getSerializableExtra(ClassmereUtils.EXTRA_COURSE_RESULT);
//            mTVSectionResultTitle.setText(mCourseItem.className);
//            mTVSectionResultCredits.setText(mCourseItem.credits + " Credits");
//        }

//        if(intent != null && intent.hasExtra(ClassmereUtils.EXTRA_SECTION_RESULT)) {
//            mSectionItem = (ClassmereUtils.CourseItem.SectionItem) intent.getSerializableExtra(ClassmereUtils.EXTRA_SECTION_RESULT);
//            mTVSectionResultInstructor.setText("Instructor: " + mSectionItem.courseInstructor);
//        }
        if(intent != null) {
            mSectionItem = (ClassmereUtils.CourseItem.SectionItem) intent.getSerializableExtra(ClassmereUtils.EXTRA_SECTION_RESULT);
            //mTVSectionResultInstructor.setText("Instructor: " + mSectionItem.courseInstructor);
            Log.d(TAG, "instructor: " + mSectionItem.courseInstructor);
        }
        else {
            Log.d(TAG, "if statement conditions not met ");
        }
    }
}
