package com.app.sagar.uwaterloohub.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SagarkumarDave on 12/21/2015.
 */
public class Course {
    @SerializedName("course_id")
    private int course_id;

    @SerializedName("subject")
    private String subject;

    @SerializedName("catalog_number")
    private String catalog_number;

    @SerializedName("title")
    private String title;

    @SerializedName("units")
    private double units;

    @SerializedName("description")
    private String description;

    @SerializedName("academic_level")
    private String academic_level;

    public int getCourse_id() {
        return course_id;
    }

    public String getSubject() {
        return subject;
    }

    public String getCatalog_number() {
        return catalog_number;
    }

    public String getTitle() {
        return title;
    }

    public double getUnits() {
        return units;
    }

    public String getDescription() {
        return description;
    }

    public String getAcademic_level() {
        return academic_level;
    }
}
