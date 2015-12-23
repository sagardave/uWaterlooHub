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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.sagar.uwaterloohub.AddCourseActivity;
import com.app.sagar.uwaterloohub.InfoSessionReyclerViewAdapter;
import com.app.sagar.uwaterloohub.Models.InfoSession;
import com.app.sagar.uwaterloohub.Models.RecyclerViewRow;
import com.app.sagar.uwaterloohub.Models.Subject;
import com.app.sagar.uwaterloohub.MyCoursesRecyclerViewAdapter;
import com.app.sagar.uwaterloohub.Network.NetworkRequestBuilder;
import com.app.sagar.uwaterloohub.Network.NetworkRequestManager;
import com.app.sagar.uwaterloohub.R;
import com.app.sagar.uwaterloohub.SubjectListRecyclerViewAdapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SagarkumarDave on 12/23/2015.
 */
public class AllInfoSessionFragment extends Fragment {

    public static AllInfoSessionFragment getInstance(int position) {
        AllInfoSessionFragment myCoursesFragment = new AllInfoSessionFragment();
        return myCoursesFragment;
    }

    private InfoSessionReyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private String INFO_SESSION_URL = "https://api.uwaterloo.ca/v2/resources/infosessions.json?key=b68407aa91907ad3d3cd47c80282ae56";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.l_all_info_session_fragment, container, false);

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.myCoursesRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        NetworkRequestManager.addRequests(NetworkRequestBuilder.getRawJSONRequest(INFO_SESSION_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    mAdapter = new InfoSessionReyclerViewAdapter(getActivity(), getInfoSessions(response.getJSONArray("data")), getFragmentManager().beginTransaction());
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

    private List<InfoSession> getInfoSessions(JSONArray subjectsArr) throws JSONException {
        List<InfoSession> infoSessions = new ArrayList<>();

        for(int i = 0; i < subjectsArr.length(); i++){
            JSONObject subObj = subjectsArr.getJSONObject(i);
            Gson gson = new Gson();
            InfoSession infoSession;
            infoSession = gson.fromJson(String.valueOf(subObj), InfoSession.class);
            if(!infoSession.getEmployer().equals("No info sessions")){
                infoSessions.add(infoSession);
            }
        }

        return infoSessions;
    }

}
