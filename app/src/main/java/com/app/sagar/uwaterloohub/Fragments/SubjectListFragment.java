package com.app.sagar.uwaterloohub.Fragments;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.sagar.uwaterloohub.Models.Course;
import com.app.sagar.uwaterloohub.Models.Subject;
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
 * Created by SagarkumarDave on 12/22/2015.
 */
public class SubjectListFragment extends Fragment implements SearchView.OnQueryTextListener {
    private SubjectListRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private String SUBJECTS_URL = "https://api.uwaterloo.ca/v2/codes/subjects.json?key=b68407aa91907ad3d3cd47c80282ae56";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.l_subject_list_fragment, container, false);
        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("Select Subject");

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.muscleGroupsRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        NetworkRequestManager.addRequests(NetworkRequestBuilder.getRawJSONRequest(SUBJECTS_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    mAdapter = new SubjectListRecyclerViewAdapter(getActivity(), getSubjects(response.getJSONArray("data")), getFragmentManager().beginTransaction());
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

    private List<Subject> getSubjects(JSONArray subjectsArr) throws JSONException {
        List<Subject> subjectData = new ArrayList<>();

        for(int i = 0; i < subjectsArr.length(); i++){
            JSONObject subObj = subjectsArr.getJSONObject(i);
            Gson gson = new Gson();
            Subject subject;
            subject = gson.fromJson(String.valueOf(subObj), Subject.class);

            subjectData.add(subject);
        }

        return subjectData;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_search, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
