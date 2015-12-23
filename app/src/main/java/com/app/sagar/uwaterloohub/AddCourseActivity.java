package com.app.sagar.uwaterloohub;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.app.sagar.uwaterloohub.Fragments.SubjectListFragment;

import java.util.Date;

/**
 * Created by SagarkumarDave on 12/22/2015.
 */
public class AddCourseActivity extends ActionBarActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Select Course");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new SubjectListFragment())
                    .commit();
        }
    }

}
