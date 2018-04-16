package com.example.classmere.classmere.Utilities;

import android.content.pm.PackageManager;
import android.net.ParseException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Created by poj on 3/15/18.
 */

public class ClassmereUtils {

    private static final String TAG = "ClassmereUtils: ";

    public static final String EXTRA_COURSE_RESULT = "ClassmereUtils.CourseResult";
    public static final String EXTRA_SECTION_RESULT = "ClassmereUtils.SectionResult";


    public final static String CLASSMERE_BASE_URL = "http://api.classmere.com/";


    /**** Classmere Course API Call: http://api.classmere.com/search/courses/SEARCH_STRING ****/

    public final static String CLASSMERE_COURSE_SEARCH_BASE = "search";
    public final static String CLASSMERE_COURSE_BASE = "courses";

    /**** Classmere Building API Call: http://api.classmere.com/Building/CODE ****/

    public final static String CLASSMERE_BUILDING_BASE = "buildings";


    public static class CourseItem implements Serializable {
        public static final String EXTRA_COURSE_ITEM = "com.example.classmere.classmere.utils.CourseItem.SearchResult";

        public String subjectCode;
        public int courseNumber;
        public String className;
        public String credits;
        public String description;

        public static class SectionItem implements Serializable {
            public static final String EXTRA_SECTION_ITEM = "com.example.classmere.classmere.utils.SectionItem.SearchResult";

            public String sectionName;
            public String sectionDescription;
            public String sectionCredits;
            public String courseTerm;
            public String courseSession;
            public int courseCrn;
            public String courseInstructor;
            public String sectionType;
            public int enrollmentCap;
            public int enrollmentCurr;
            public int enrollmentLeft;

            public String meetingDays;
            public String startTime;
            public String endTime;
            public String startDate;
            public String endDate;
            public String buildingCode;
            public String roomNumber;
        }

        public ArrayList<SectionItem> sectionItems = new ArrayList<SectionItem>();
    }

    public static String buildClassmereURL(String searchQuery) {
        Uri.Builder builder = Uri.parse(CLASSMERE_BASE_URL).buildUpon();

        builder.appendPath(CLASSMERE_COURSE_SEARCH_BASE);
        builder.appendPath(CLASSMERE_COURSE_BASE);
        if(!TextUtils.isEmpty(searchQuery)) {
            builder.appendPath(searchQuery);
        }

        return builder.build().toString();
    }

    public static String buildBuildingURL(String buildingQuery) {
        Uri.Builder builder = Uri.parse(CLASSMERE_BASE_URL).buildUpon();

        builder.appendPath(CLASSMERE_BUILDING_BASE);
        if(!TextUtils.isEmpty(buildingQuery)) {
            builder.appendPath(buildingQuery);
        }

        return builder.build().toString();
    }

    public static ArrayList<CourseItem> parseCourseJSON(String courseResultsJSON) {
        try {

            String season, year;

            JSONObject courseResultsObj;
            JSONArray courseResultsItems = new JSONArray(courseResultsJSON);

            ArrayList<CourseItem> courseResultsList = new ArrayList<CourseItem>();
            for(int i=0; i<courseResultsItems.length(); i++) {
                CourseItem courseItem = new CourseItem();

                courseResultsObj = courseResultsItems.getJSONObject(i);
                courseItem.subjectCode = (String) courseResultsObj.get("subjectCode");
                courseItem.courseNumber = (int) courseResultsObj.get("courseNumber");
                courseItem.className = (String) courseResultsObj.get("title");
                courseItem.credits = (String) courseResultsObj.get("credits");
                courseItem.description = (String) courseResultsObj.get("description");

                JSONArray sectionResultsJSON = courseResultsObj.getJSONArray("sections");
                for(int j=0; j<sectionResultsJSON.length(); j++) {
                    CourseItem.SectionItem sectionItem = new CourseItem.SectionItem();

                    JSONObject courseSectionObj = sectionResultsJSON.getJSONObject(j);
                    sectionItem.sectionName = (String) courseResultsObj.get("title");
                    sectionItem.sectionCredits = (String) courseResultsObj.get("credits");
                    sectionItem.sectionDescription = (String) courseResultsObj.get("description");
                    sectionItem.courseTerm = (String) courseSectionObj.get("term");

                    /* Converting term to Term 20-Year format */
                    season = sectionItem.courseTerm;
                    year = season.substring(season.length() - 2);

                    String parsedTerm = "";

                    if(season.contains("F")) {
                        parsedTerm += "Fall";
                    }
                    else if(season.contains("W")) {
                        parsedTerm += "Winter";
                    }
                    else if(season.contains("Sp")) {
                        parsedTerm += "Spring";
                    }
                    else if(season.contains("Su")) {
                        parsedTerm += "Summer";
                    }

                    if(year != null) {
                        parsedTerm += " 20" + year;
                    }
                    sectionItem.courseTerm = parsedTerm;

                    sectionItem.courseSession = (String) courseSectionObj.get("session");
                    sectionItem.courseCrn = (int) courseSectionObj.get("crn");
                    sectionItem.courseInstructor = (String) courseSectionObj.get("instructor");
                    sectionItem.startDate = (String) courseSectionObj.get("startDate");
                    sectionItem.endDate = (String) courseSectionObj.get("endDate");
                    sectionItem.sectionType = (String) courseSectionObj.get("type");
                    sectionItem.enrollmentCap = (int) courseSectionObj.get("enrollmentCapacity");
                    sectionItem.enrollmentCurr = (int) courseSectionObj.get("enrollmentCurrent");
                    sectionItem.enrollmentLeft = sectionItem.enrollmentCap - sectionItem.enrollmentCurr;

                    if(!courseSectionObj.isNull("meetingTimes")) {
                        JSONArray meetingResultsJSON = courseSectionObj.getJSONArray("meetingTimes");
                        for(int k=0; k<meetingResultsJSON.length(); k++) {

                            JSONObject courseMeetingObj = meetingResultsJSON.getJSONObject(0);
                            sectionItem.meetingDays = (String) courseMeetingObj.get("days");
                            sectionItem.buildingCode = (String) courseMeetingObj.get("buildingCode");
                            if(sectionItem.buildingCode.equals("TBA")) {
                                sectionItem.roomNumber = "";
                            }
                            else {
                                sectionItem.roomNumber = (String) courseMeetingObj.get("roomNumber").toString();

                                if(sectionItem.roomNumber.equals("null")) {
                                    sectionItem.roomNumber = "";
                                }
                            }

                            sectionItem.startTime = (String) courseMeetingObj.get("startTime");
                            sectionItem.endTime = (String) courseMeetingObj.get("endTime");

                            /* Converting startTime and endTime to hh:mm a format */
                            try {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                SimpleDateFormat timeOutput = new SimpleDateFormat("h:mm a");

                                Date newStartTime = sdf.parse(sectionItem.startTime);
                                Date newEndTime = sdf.parse(sectionItem.endTime);

                                sectionItem.startTime = timeOutput.format(newStartTime);
                                sectionItem.endTime = timeOutput.format(newEndTime);

                            } catch (java.text.ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    courseItem.sectionItems.add(sectionItem);
                }
                courseResultsList.add(courseItem);
            }

            return courseResultsList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
