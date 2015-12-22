package com.app.sagar.uwaterloohub;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.sagar.uwaterloohub.Models.Course;

import java.util.List;

/**
 * Created by SagarkumarDave on 12/22/2015.
 */
public class CourseListRecyclerViewAdapter extends RecyclerView.Adapter<CourseListRecyclerViewAdapter.MyViewHolder> {
    List<Course> courseListData;
    private LayoutInflater inflater;
    Context context;
    private FragmentTransaction ft;
    String muscleGroup;

    public CourseListRecyclerViewAdapter(FragmentActivity activity, List<Course> courseListData, String muscleGroup, FragmentTransaction ft) {
        super();
        this.courseListData = courseListData;
        this.context = activity;
        inflater = LayoutInflater.from(context);
        this.ft = ft;
        this.muscleGroup = muscleGroup;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.l_course_list_row, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String current = courseListData.get(position).getTitle();
        holder.tv.setText(current);
    }

    @Override
    public int getItemCount() {
        return courseListData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.name);
            tv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
