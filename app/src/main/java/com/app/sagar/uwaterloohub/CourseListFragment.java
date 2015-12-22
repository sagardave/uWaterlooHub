package com.app.sagar.uwaterloohub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.sagar.uwaterloohub.Models.Course;
import com.app.sagar.uwaterloohub.Models.Subject;
import com.app.sagar.uwaterloohub.Network.NetworkRequestBuilder;
import com.app.sagar.uwaterloohub.Network.NetworkRequestManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SagarkumarDave on 12/22/2015.
 */
public class CourseListFragment extends Fragment {
    private CourseListRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private String mSubject;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.l_subject_list_fragment, container, false);
        Bundle b = getArguments();
        if (b != null) {
            mSubject = b.getString("subject");
        }

        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(mSubject);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.muscleGroupsRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        NetworkRequestManager.addRequests(NetworkRequestBuilder.getRawJSONRequest(getCourseUrl(mSubject), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    mAdapter = new CourseListRecyclerViewAdapter(getActivity(), getCoursesBySubject(response.getJSONArray("data")), mSubject, getFragmentManager().beginTransaction());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mRecyclerView.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));

        return layout;
    }

    private List<Course> getCoursesBySubject(JSONArray courseArr) throws JSONException {

        List<Course> courseList = new ArrayList<>();

        for(int i = 0; i < courseArr.length(); i++){
            JSONObject courseObj = courseArr.getJSONObject(i);
            Gson gson = new Gson();
            Course course;
            course = gson.fromJson(String.valueOf(courseObj), Course.class);

            courseList.add(course);
        }

        return courseList;
    }

    private String getCourseUrl(String subject){
        return "https://api.uwaterloo.ca/v2/courses/" + subject + ".json?key=b68407aa91907ad3d3cd47c80282ae56";
    }

}
