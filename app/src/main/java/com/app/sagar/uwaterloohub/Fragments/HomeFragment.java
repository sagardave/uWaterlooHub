package com.app.sagar.uwaterloohub.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.sagar.uwaterloohub.Models.Course;
import com.app.sagar.uwaterloohub.Network.NetworkRequestBuilder;
import com.app.sagar.uwaterloohub.Network.NetworkRequestManager;
import com.app.sagar.uwaterloohub.R;
import com.app.sagar.uwaterloohub.ui.SlidingTabLayout;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by SagarkumarDave on 12/21/2015.
 */
public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {
    private ViewPager mPager;
    private SlidingTabLayout mTabs;

    public static HomeFragment getInstance(int position){
        HomeFragment slidingTabsFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        slidingTabsFragment.setArguments(args);
        return slidingTabsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.l_home_fragment, container, false);
        mPager = (ViewPager) layout.findViewById(R.id.pager);
        NetworkRequestManager.init(getContext());
        mPager.setAdapter(new MyPagerAdapter((getChildFragmentManager())));
        mTabs = (SlidingTabLayout) layout.findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setViewPager(mPager);

        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getSeasonsResponse("https://api.uwaterloo.ca/v2/courses/MATH.json?key=b68407aa91907ad3d3cd47c80282ae56");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

        // Associate searchable configuration with the SearchView
        // SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        // searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
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

    class MyPagerAdapter extends FragmentPagerAdapter {
        String[] tabNames;
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabNames = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int i) {
            if(tabNames[i].equals("Courses")){
                return MyCoursesFragment.getInstance(i);
            }
            else if(i == 1){
                return SampleFragment.getInstance(i);
            }
            return SampleFragment.getInstance(i);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }
    }

    public void getSeasonsResponse(String url) {

        NetworkRequestManager.addRequests(NetworkRequestBuilder.getRawJSONRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Course course;
                try {
                    course = gson.fromJson(String.valueOf(response.getJSONArray("data").getJSONObject(0)), Course.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));
    }

}
