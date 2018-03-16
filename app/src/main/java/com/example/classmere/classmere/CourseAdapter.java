package com.example.classmere.classmere;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classmere.classmere.Utilities.ClassmereUtils;

/**
 * Created by poj on 3/15/18.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseItemViewHolder> {

    private ClassmereUtils.CourseItem mCourseItem;

    private static final String TAG = "CourseAdapter: ";

    private OnCourseItemClickListener mCourseItemClickListener;
    private Context mContext;

    public interface OnCourseItemClickListener {
        void onCourseItemClickListener();
    }

    public CourseAdapter(Context context, OnCourseItemClickListener clickListener) {
        mContext = context;
        mCourseItemClickListener = clickListener;
    }

    public void updateCourseItems() {

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

        public CourseItemViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        public void bind() {

        }

        @Override
        public void onClick(View v) {

        }
    }
}
