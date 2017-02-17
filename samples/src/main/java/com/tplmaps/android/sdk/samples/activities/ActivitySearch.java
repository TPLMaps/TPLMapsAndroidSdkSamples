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
import com.tplmaps3d.sdk.search.OnSearchResult;
import com.tplmaps3d.sdk.search.Params;
import com.tplmaps3d.sdk.search.Place;
import com.tplmaps3d.sdk.search.SearchManager;
import com.tplmaps3d.sdk.utils.CommonUtils;

import java.util.ArrayList;

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
                searchManager.request("http://175.107.196.76:8080/TestApp/webresources/generic/query",
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
    public void onSearchResultNotFound(Params params) {
        CommonUtils.showToast(this, "Results not found against query: " + params.query, 2000, true);
    }

    @Override
    public void onSearchRequestFailure(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onSearchRequestCancel(Params params) {
        Log.i(TAG, "Request cancelled against query: " + params.query);
    }

    @Override
    public void onSearchRequestSuspended(Exception e) {
        e.printStackTrace();
    }
}
