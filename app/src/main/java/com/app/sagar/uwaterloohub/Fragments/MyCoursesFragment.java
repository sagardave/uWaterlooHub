package com.app.sagar.uwaterloohub.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.sagar.uwaterloohub.Adapters.RecyclerViewAdapter;
import com.app.sagar.uwaterloohub.AddCourseActivity;
import com.app.sagar.uwaterloohub.Models.RecyclerViewRow;
import com.app.sagar.uwaterloohub.MyCoursesRecyclerViewAdapter;
import com.app.sagar.uwaterloohub.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SagarkumarDave on 12/22/2015.
 */
public class MyCoursesFragment extends Fragment {

    public static MyCoursesFragment getInstance(int position){
        MyCoursesFragment myCoursesFragment = new MyCoursesFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myCoursesFragment.setArguments(args);
        return myCoursesFragment;
    }

    private MyCoursesRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.l_my_courses_fragment, container, false);
        Bundle bundle = getArguments();

        mAdapter = new MyCoursesRecyclerViewAdapter(getActivity(), getData());
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.myCoursesRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mFloatingButton = (FloatingActionButton) layout.findViewById(R.id.floatingAddLogButton);
        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddCourseActivity.class);

                startActivity(intent);
            }
        });

        return layout;
    }

    public static List<RecyclerViewRow> getData(){
        List<RecyclerViewRow> data = new ArrayList();
        int[] icons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        String[] titles = {"One", "Two", "Three", "Four"};

        for(int i = 0; i < titles.length * 3; i++){
            RecyclerViewRow current = new RecyclerViewRow();
            current.title = titles[i%3];
            current.iconId = icons[i%3];

            data.add(current);
        }

        return data;
    }
}
