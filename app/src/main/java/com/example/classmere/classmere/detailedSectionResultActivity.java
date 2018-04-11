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
    private TextView mTVSectionResultDescription;
    private TextView mTVSectionResultInstructor;

    private ClassmereUtils.CourseItem.SectionItem mSectionItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_result_detail);

        mTVSectionResultTitle = (TextView) findViewById(R.id.tv_section_title);
        mTVSectionResultCredits = (TextView) findViewById(R.id.tv_section_credits);
        mTVSectionResultDescription = (TextView) findViewById(R.id.tv_section_description);
        mTVSectionResultInstructor = (TextView) findViewById(R.id.tv_section_instructor);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(ClassmereUtils.EXTRA_SECTION_RESULT)) {
            mSectionItem = (ClassmereUtils.CourseItem.SectionItem) intent.getSerializableExtra(ClassmereUtils.EXTRA_SECTION_RESULT);
            mTVSectionResultTitle.setText(mSectionItem.sectionName);
            mTVSectionResultCredits.setText(mSectionItem.sectionCredits + " Credits");
            mTVSectionResultDescription.setText(mSectionItem.sectionDescription);
            mTVSectionResultInstructor.setText("Instructor: " + mSectionItem.courseInstructor);
        }
        else {
            Log.d(TAG, "if statement conditions not met ");
        }
    }
}
