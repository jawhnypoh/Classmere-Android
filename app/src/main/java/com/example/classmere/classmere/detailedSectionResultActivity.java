package com.example.classmere.classmere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.classmere.classmere.Utilities.ClassmereUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

/**
 * Created by jp on 4/3/18.
 */

public class detailedSectionResultActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "detailedSectionActivity: ";

    private TextView mTVSectionResultTitle;
    private TextView mTVSectionResultCredits;
    private TextView mTVSectionResultDescription;
    private TextView mTVSectionResultTerm;
    private TextView mTVSectionResultMeetingTime;
    private TextView mTVSectionResultInstructor;
    private TextView mTVSectionResultLocation;
    private TextView mTVSectionResultType;
    private TextView mTVSectionResultEnrollment;
    private TextView mTvSectionResultDates;
    private TextView mTVSectionResultCrn;

    private ClassmereUtils.CourseItem.SectionItem mSectionItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_result_detail);

        mTVSectionResultTitle = (TextView) findViewById(R.id.tv_section_title);
        mTVSectionResultCredits = (TextView) findViewById(R.id.tv_section_credits);
        mTVSectionResultDescription = (TextView) findViewById(R.id.tv_section_description);
        mTVSectionResultTerm = (TextView) findViewById(R.id.tv_section_term);
        mTVSectionResultInstructor = (TextView) findViewById(R.id.tv_section_instructor);
        mTVSectionResultMeetingTime = (TextView) findViewById(R.id.tv_section_meeting_time);
        mTVSectionResultLocation = (TextView) findViewById(R.id.tv_section_location);
        mTVSectionResultType = (TextView) findViewById(R.id.tv_section_type);
        mTVSectionResultEnrollment = (TextView) findViewById(R.id.tv_section_enrollment);
        mTvSectionResultDates = (TextView) findViewById(R.id.tv_section_dates);
        mTVSectionResultCrn = (TextView) findViewById(R.id.tv_section_crn);

        // Get SupportMapFragment and request notification when map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.section_map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(ClassmereUtils.EXTRA_SECTION_RESULT)) {
            mSectionItem = (ClassmereUtils.CourseItem.SectionItem) intent.getSerializableExtra(ClassmereUtils.EXTRA_SECTION_RESULT);
            mTVSectionResultTitle.setText(mSectionItem.sectionName);
            mTVSectionResultCredits.setText(mSectionItem.sectionCredits + " Credits");
            mTVSectionResultDescription.setText(mSectionItem.sectionDescription);
            mTVSectionResultTerm.setText(mSectionItem.courseTerm);
            mTVSectionResultInstructor.setText("Instructor: " + mSectionItem.courseInstructor);
            mTVSectionResultMeetingTime.setText("Meet: " + mSectionItem.meetingDays + "  " + mSectionItem.startTime + " - " + mSectionItem.endTime);
            if(mSectionItem.meetingDays == null || mSectionItem.startTime == null || mSectionItem.endTime == null) {
                mTVSectionResultMeetingTime.setText("No Meeting Times Specified ");
            }
            mTVSectionResultLocation.setText("Location: " + mSectionItem.buildingCode + " " + mSectionItem.roomNumber);
            if(mSectionItem.buildingCode == null) {
                mTVSectionResultLocation.setText("No Location Specified ");
            }
            else if (mSectionItem.roomNumber == null){
                mSectionItem.roomNumber = "";
            }
            mTVSectionResultType.setText("Type: " + mSectionItem.sectionType);
            mTVSectionResultEnrollment.setText("Enrollment: " + mSectionItem.enrollmentCurr + " student(s) enrolled, " + mSectionItem.enrollmentLeft + " spots left ");
            mTvSectionResultDates.setText("Dates: " + mSectionItem.startDate + " - " + mSectionItem.endDate);
            mTVSectionResultCrn.setText("CRN: " + mSectionItem.courseCrn);
        }
        else {
            Log.d(TAG, "if statement conditions not met ");
        }
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
