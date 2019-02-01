package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.constants.URLConstants;
import com.tplmaps.sdk.places.OnSearchResult;
import com.tplmaps.sdk.places.Params;
import com.tplmaps.sdk.places.Place;
import com.tplmaps.sdk.places.SearchManager;
import com.tplmaps3d.sdk.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ActivitySearch extends AppCompatActivity implements OnSearchResult {

    SearchManager searchManager;
    ImageView ivSearch, ivCancel;
    EditText etSearch;

    private static final String TAG = ActivitySearch.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        // Initialize SearchManager
        searchManager = new SearchManager(this);

        searchManager.setListener(this);

        setViews();

        searchManager.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        searchManager.onDestroy();
        searchManager = null;
    }

    private void setViews() {

        // Get search field
        etSearch = findViewById(R.id.etSearch);

        // Get and set Search button
        ivSearch = findViewById(R.id.ivSearch);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Request for query after initializing SearchManager
                searchManager.request(Params.builder()
                        .query(etSearch.getText().toString())
                        .build());
            }
        });

        // Get and set Cancel button
        ivCancel = findViewById(R.id.ivCancel);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cancel all pending requests
                etSearch.getText().clear();
                searchManager.cancelPendingRequests();
                clearList();
            }
        });
    }

    ArrayList<String> strResults;
    ArrayAdapter<String> adapter;
    void populateListView(ArrayList<Place> results) {
        if (results == null)
            return;

        strResults = new ArrayList<>();
        for (Place place : results) {
            strResults.add(place.getName());
        }

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, strResults);
        ((ListView) findViewById(R.id.listview)).setAdapter(adapter);
    }

    void clearList() {
        if(strResults != null)
            strResults.clear();
        if(adapter != null && strResults != null && strResults.size() > 0)
            adapter.clear();
        if(adapter != null)
            adapter.notifyDataSetChanged();
    }

    @Override
    public void onSearchResult(ArrayList<Place> results) {
        populateListView(results);
    }

    @Override
    public void onSearchResultNotFound(Params params, long requestTimeInMS) {
        CommonUtils.showToast(this, "Results not found against query: " + params.query +
                        " at " + getDateFormatFromMilliSeconds("dd-MM-yyyy HH:mm:ss", requestTimeInMS),
                2000, true);
    }

    @Override
    public void onSearchRequestFailure(Exception e) {
        //e.printStackTrace();
    }

    @Override
    public void onSearchRequestCancel(Params params, long requestTimeInMS) {
        Log.i(TAG, "Request cancelled against query: " + params.query +
                " at " + getDateFormatFromMilliSeconds("dd-MM-yyyy HH:mm:ss", requestTimeInMS));
    }

    @Override
    public void onSearchRequestSuspended(String errorMessage, Params params, long requestTimeInMS) {
        Log.e(TAG, "Request Suspended: " + errorMessage);
    }


    @SuppressWarnings("SameParameterValue")
    private String getDateFormatFromMilliSeconds(String format, long timeInMS) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(new Date(timeInMS));
    }
}
