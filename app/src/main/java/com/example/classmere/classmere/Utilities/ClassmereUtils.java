package com.example.classmere.classmere.Utilities;

import android.net.ParseException;
import android.net.Uri;
import android.text.TextUtils;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by poj on 3/15/18.
 */

public class ClassmereUtils {

    private static final String TAG = "ClassmereUtils: ";


    public final static String CLASSMERE_BASE_URL = "http://api.classmere.com/";

    /**** Classmere Course API Call: http://api.classmere.com/Course/NUM ****/

    public final static String CLASSMERE_COURSE_BASE = "Courses";
    public final static String CLASSMERE_COURSE_CODE = "";
    public final static String CLASSMERE_COURSE_NUM = "";

    /**** Classmere Building API Call: http://api.classmere.com/Building/CODE ****/

    public final static String CLASSMERE_BUILDING_BASE = "buildings";
    public final static String CLASSMERE_BUILDING_CODE = "";

    public static class CourseItem implements Serializable {
        public static final String EXTRA_COURSE_ITEM = "com.example.classmere.classmere.utils.CourseItem.SearchResult";

        public String classCode;
        public String className;
        public String Description;
        public String buildingLocation;
    }

    public static String buildClassmereURL(String courseCode, String courseNum) {
        Uri.Builder builder = Uri.parse(CLASSMERE_BASE_URL).buildUpon();

        builder.appendQueryParameter(CLASSMERE_COURSE_CODE, courseCode);

        if(!TextUtils.isEmpty(courseNum)) {
            builder.appendQueryParameter(CLASSMERE_COURSE_NUM, courseNum);
        }

        return builder.build().toString();
    }

    public static ArrayList<CourseItem> parseCourseJSON(String courseJSON) {
        try {

            return courseItemsList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
