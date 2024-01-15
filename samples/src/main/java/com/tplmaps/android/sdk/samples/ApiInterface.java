package com.tplmaps.android.sdk.samples;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("todos/1")
    Call<JsonObject> getEndpointData();
}
