package com.app.sagar.uwaterloohub.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SagarkumarDave on 12/22/2015.
 */
public class Subject {
    @SerializedName("subject")
    private String subject;

    @SerializedName("description")
    private String description;

    @SerializedName("unit")
    private String unit;

    @SerializedName("group")
    private String group;

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }

    public String getGroup() {
        return group;
    }
}
