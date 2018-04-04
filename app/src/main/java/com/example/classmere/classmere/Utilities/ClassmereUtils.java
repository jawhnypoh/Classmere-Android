package com.example.classmere.classmere.Utilities;

import android.net.ParseException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by poj on 3/15/18.
 */

public class ClassmereUtils {

    private static final String TAG = "ClassmereUtils: ";

    public static final String EXTRA_COURSE_RESULT = "ClassmereUtils.CourseResult";


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
    }

    public static class SectionItem implements Serializable {
        public static final String EXTRA_SECTION_ITEM = "com.example.classmere.classmere.utils.SectionItem.SearchResult";

        public String courseSection;
        public String buildingCode;
        public String roomNumber;
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

                ArrayList<SectionItem> sectionResults = new ArrayList<SectionItem>();
                JSONArray sectionResultsJSON = courseResultsObj.getJSONArray("sections");
                for(int j=0; j<sectionResultsJSON.length(); j++) {
                    SectionItem sectionItem = new SectionItem();

                    JSONObject courseSectionObj = sectionResultsJSON.getJSONObject(j);
                    sectionItem.courseSection = (String) courseSectionObj.get("term");
                    Log.d(TAG, "courseSection = " + sectionItem.courseSection);
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
