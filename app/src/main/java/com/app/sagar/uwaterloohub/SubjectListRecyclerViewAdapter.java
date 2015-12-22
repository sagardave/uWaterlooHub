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

import com.app.sagar.uwaterloohub.Models.Subject;

import java.util.List;

/**
 * Created by SagarkumarDave on 12/22/2015.
 */
public class SubjectListRecyclerViewAdapter extends RecyclerView.Adapter<SubjectListRecyclerViewAdapter.MyViewHolder> {
    List<Subject> subjectData;
    private LayoutInflater inflater;
    Context context;
    private FragmentTransaction ft;
    public SubjectListRecyclerViewAdapter(FragmentActivity activity, List<Subject> subjectData, FragmentTransaction ft) {
        super();
        this.subjectData = subjectData;
        this.context = activity;
        inflater = LayoutInflater.from(context);
        this.ft = ft;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.l_subject_list_row, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Subject current = subjectData.get(position);
        holder.tv.setText(current.getSubject());
    }

    @Override
    public int getItemCount() {
        return subjectData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.name);
            tv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            CourseListFragment myFragment = new CourseListFragment();

            Bundle args = new Bundle();
            args.putString("subject", subjectData.get(getPosition()).getSubject());
            myFragment.setArguments(args);
            ft.addToBackStack(null);

            ft.replace(R.id.fragment, myFragment).commit();
        }
    }
}
