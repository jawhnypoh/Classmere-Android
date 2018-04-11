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

    private ArrayList<ClassmereUtils.CourseItem.SectionItem> mSectionsResultsList;

    private static final String TAG = "CourseSectionAdapter: ";

    private CourseSectionAdapter.OnCourseSectionItemClickListener mCourseSectionItemClickListener;
    private Context mContext;

    public interface OnCourseSectionItemClickListener {
        void onCourseSectionItemClick(ClassmereUtils.CourseItem.SectionItem sectionItem);
    }

    public CourseSectionAdapter(OnCourseSectionItemClickListener clickListener) {
        mCourseSectionItemClickListener = clickListener;
    }

    public void updateCourseSectionItems(ArrayList<ClassmereUtils.CourseItem.SectionItem> sectionItems) {
        mSectionsResultsList = sectionItems;
        notifyDataSetChanged();
    }

    @Override
    public CourseSectionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.section_result_item, parent, false);
        return new CourseSectionItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseSectionItemViewHolder holder, int position) {
        holder.bind(mSectionsResultsList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mSectionsResultsList != null) {
            return mSectionsResultsList.size();
        }
        else {
            return 0;
        }
    }

    class CourseSectionItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mCourseSectionResultTV;
        private TextView mSectionDays;
        private TextView mSectionTimes;
        private TextView mSectionInstructor;
        private TextView mSectionTypeTV;

        public CourseSectionItemViewHolder(View itemView) {
            super(itemView);

            mCourseSectionResultTV = (TextView)itemView.findViewById(R.id.tv_course_sections);
            mSectionDays = (TextView)itemView.findViewById(R.id.tv_section_days);
            mSectionTimes = (TextView)itemView.findViewById(R.id.tv_section_times);
            mSectionInstructor = (TextView)itemView.findViewById(R.id.tv_section_instructor);
            mSectionTypeTV = (TextView)itemView.findViewById(R.id.tv_section_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClassmereUtils.CourseItem.SectionItem sectionItem = mSectionsResultsList.get(getAdapterPosition());
                    mCourseSectionItemClickListener.onCourseSectionItemClick(sectionItem);
                }
            });
        }

        public void bind(ClassmereUtils.CourseItem.SectionItem sectionItem) {
            mCourseSectionResultTV.setText(sectionItem.courseTerm);
            mSectionDays.setText(sectionItem.meetingDays);
            mSectionTimes.setText(sectionItem.startTime + " - " + sectionItem.endTime);
            mSectionInstructor.setText(sectionItem.courseInstructor);
            mSectionTypeTV.setText(sectionItem.sectionType);
        }
    }
}

