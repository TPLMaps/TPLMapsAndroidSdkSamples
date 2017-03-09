package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.constants.URLConstants;
import com.tplmaps3d.sdk.search.OnSearchResult;
import com.tplmaps3d.sdk.search.Params;
import com.tplmaps3d.sdk.search.Place;
import com.tplmaps3d.sdk.search.SearchManager;
import com.tplmaps3d.sdk.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ActivitySearch extends AppCompatActivity implements OnSearchResult {

    SearchManager searchManager;
    Button btnSearch;
    EditText etSearch;

    private static final String TAG = ActivitySearch.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        // Initialize SearchManager
        searchManager = new SearchManager(this);
        /*searchManager.config(SearchManager.Config.builder()
                .cancelPendingRequests(false)
                .build());*/
        searchManager.setListener(this);

        // Get EditText
        etSearch = (EditText) findViewById(R.id.etSearch);

        // Get Button and set listener
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Request for query after initializing SearchManager
                searchManager.request(URLConstants.URL_SEARCH,
                        Params.builder()
                                .query(etSearch.getText().toString())
                                .category("")
                                .macAddress("").build());
            }
        });
    }

    void populateListView(ArrayList<Place> results) {
        if (results == null)
            return;

        ArrayList<String> strResults = new ArrayList<>();
        for (Place place : results) {
            strResults.add(place.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, strResults);
        ((ListView) findViewById(R.id.listview)).setAdapter(adapter);
    }

    @Override
    public void onSearchResult(ArrayList<Place> results) {
        populateListView(results);
    }

    @Override
    public void onSearchResultNotFound(Params params, long requestTimeInMS) {
        CommonUtils.showToast(this, "Results not found against query: " + params.query +
                        " at " + getDateFormatFromMilliSeconds(requestTimeInMS),
                2000, true);
    }

    @Override
    public void onSearchRequestFailure(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onSearchRequestCancel(Params params, long requestTimeInMS) {
        Log.i(TAG, "Request cancelled against query: " + params.query +
                " at " + getDateFormatFromMilliSeconds(requestTimeInMS));
    }

    @Override
    public void onSearchRequestSuspended(String errorMessage, Params params, long requestTimeInMS) {
        Log.e(TAG, "Request Suspended: " + errorMessage);
    }


    private String getDateFormatFromMilliSeconds(long timeInMS) {
        final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
        return new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(new Date(timeInMS));
    }
}
