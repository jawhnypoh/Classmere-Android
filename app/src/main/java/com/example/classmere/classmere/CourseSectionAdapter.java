package com.example.classmere.classmere;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.classmere.classmere.Utilities.ClassmereUtils;

import java.util.ArrayList;

/**
 * Created by jp on 4/3/18.
 */

public class CourseSectionAdapter extends RecyclerView.Adapter<CourseSectionAdapter.CourseSectionItemViewHolder> {

    private ArrayList<ClassmereUtils.CourseItem> mCourseResultsList;

    private static final String TAG = "CourseSectionAdapter: ";

    private CourseSectionAdapter.OnCourseSectionItemClickListener mCourseSectionItemClickListener;
    private Context mContext;

    public interface OnCourseSectionItemClickListener {
        void onCourseSectionItemClick(ClassmereUtils.CourseItem courseItem);
    }

    public CourseSectionAdapter(OnCourseSectionItemClickListener clickListener) {
        mCourseSectionItemClickListener = clickListener;
    }

    public void updateCourseSectionItems(ArrayList<ClassmereUtils.CourseItem> courseItems) {
        mCourseResultsList = courseItems;
        notifyDataSetChanged();
    }

    @Override
    public CourseSectionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View itemView = inflator.inflate(R.layout.section_result_item, parent, false);
        return new CourseSectionItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseSectionItemViewHolder holder, int position) {
        holder.bind(mCourseResultsList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mCourseResultsList != null) {
            return mCourseResultsList.size();
        }
        else {
            return 0;
        }
    }

    class CourseSectionItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mCourseSectionResultTV;

        public CourseSectionItemViewHolder(View itemView) {
            super(itemView);

            mCourseSectionResultTV = (TextView)itemView.findViewById(R.id.tv_course_sections);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClassmereUtils.CourseItem courseItem = mCourseResultsList.get(getAdapterPosition());
                    mCourseSectionItemClickListener.onCourseSectionItemClick(courseItem);
                }
            });
        }

        public void bind(ClassmereUtils.CourseItem courseItem) {
            mCourseSectionResultTV.setText(courseItem.className);
        }
    }
}

