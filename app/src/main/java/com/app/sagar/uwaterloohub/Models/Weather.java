package com.app.sagar.uwaterloohub.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SagarkumarDave on 12/23/2015.
 */
public class Weather {
    @SerializedName("temperature_current_c")
    private float current;

    public float getCurrent() {
        return current;
    }
}
