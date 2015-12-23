package com.app.sagar.uwaterloohub.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SagarkumarDave on 12/21/2015.
 */
public class InfoSession {

    @SerializedName("employer")
    private String employer;

    @SerializedName("date")
    private String date;

    @SerializedName("start_time")
    private String start_time;

    @SerializedName("end_time")
    private String end_time;

    @SerializedName("location")
    private String location;

    @SerializedName("description")
    private String description;

    public String getEmployer() {
        return employer;
    }

    public String getDate() {
        return date;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
