package com.example.classmere.classmere;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.classmere.classmere.Utilities.ClassmereUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by poj on 3/15/18.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseItemViewHolder> {

    private ClassmereUtils.CourseItem mCourseItem;
    private ArrayList<ClassmereUtils.CourseItem> mCourseResultsList;

    private static final String TAG = "CourseAdapter: ";

    private OnCourseItemClickListener mCourseItemClickListener;
    private Context mContext;

    public interface OnCourseItemClickListener {
        void onCourseItemClick(ClassmereUtils.CourseItem courseItem);
    }

    public CourseAdapter(OnCourseItemClickListener clickListener) {
        mCourseItemClickListener = clickListener;
    }

    public void updateCourseItems(ArrayList<ClassmereUtils.CourseItem> courseItems) {

        notifyDataSetChanged();
    }


    @Override
    public CourseItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.search_result_item, parent, false);
        return new CourseItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseItemViewHolder holder, int position) {
        holder.bind(mCourseResultsList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mCourseItem != null) {
            return 1;
        }
        else {
            return 0;
        }
    }

    class CourseItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mCourseResultTV;

        public CourseItemViewHolder(View itemView) {
            super(itemView);

            mCourseResultTV = (TextView)itemView.findViewById(R.id.tv_search_result);

            itemView.setOnClickListener(this);
        }

        public void bind(ClassmereUtils.CourseItem courseItem) {
            mCourseResultTV.setText(courseItem.className);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
