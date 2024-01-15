package com.tplmaps.android.sdk.samples;


import android.os.Bundle;

import android.text.PrecomputedText;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.tplmaps.android.R;


import com.tplmaps.sdk.places.LngLat;
import com.tplmaps.sdk.places.OnSearchResult;
import com.tplmaps.sdk.places.Params;
import com.tplmaps.sdk.places.Place;
import com.tplmaps.sdk.places.SearchManager;
import com.tplmaps.sdk.utils.StringUtils;
import com.tplmaps3d.sdk.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ActivityAdminArea extends AppCompatActivity implements OnSearchResult {

    SearchManager searchManager;
    ImageView ivSearch, ivCancel;
    EditText etSearch;
    TextView tv_detail;

    private static final String TAG = ActivityAdminArea.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request_admin_area);

        // Initialize SearchManager
        searchManager = new SearchManager(this);

        //searchManager.setListener(this);

        setViews();

        searchManager.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (searchManager != null) {
            searchManager.onDestroy();
            searchManager = null;
        }
    }

    private void setViews() {

        // Get search field
        etSearch = findViewById(R.id.etSearch);
        tv_detail = findViewById(R.id.tv_detail);

        LngLat islamabad = new LngLat(33.717864, 73.071648);

        // Get and set Search button
        EditText etSearch = findViewById(R.id.etSearch);
        ivSearch = findViewById(R.id.ivSearch);
        ivSearch.setOnClickListener(view -> {
            // Request for query after initializing SearchManager
            // put your query string with location to get your nearer results first
            Double lat = Double.valueOf(etSearch.getText().toString().split(",")[0]);
            Double lng = Double.valueOf(etSearch.getText().toString().split(",")[1]);

            searchManager.requestNearbyAdminArea(Params.builder()
                    .location(new LngLat(lat,lng))
                    .build(), this);
        });
        etSearch.setOnEditorActionListener(
                (v, actionId, event) -> {
                    // Identifier of the action. This will be either the identifier you supplied,
                    // or EditorInfo.IME_NULL if being called due to the enter key being pressed.
                    if (actionId == EditorInfo.IME_ACTION_SEARCH
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || event.getAction() == KeyEvent.ACTION_DOWN
                            && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

//                        Double lat = Double.valueOf(etSearch.getText().toString().split(",")[0]);
//                        Double lng = Double.valueOf(etSearch.getText().toString().split(",")[1]);

//                        searchManager.requestAreaDetail(Params.builder()
//                                        .query(etSearch.getText().toString())
//                               // .location(new LngLat(lat,lng))
//                                .build(), this);
                        return true;
                    }
                    // Return true if you have consumed the action, else false.
                    return false;
                });
        // Get and set Cancel button
        ivCancel = findViewById(R.id.ivCancel);
        ivCancel.setOnClickListener(view -> {
            // Cancel all pending requests
            etSearch.getText().clear();
            searchManager.cancelPendingRequests();
            clearList();
        });
    }

    ArrayList<String> strResults;
    ArrayAdapter<String> adapter;

    void populateListView(ArrayList<Place> results) {
        if (results == null)
            return;

//
//        tv_detail.setText("DIVISION: "+results.get(0).getDivision()+
//                "\nADDRESS: "+results.get(0).getAddressNearby()+
//                "\nPROVINCE: "+results.get(0).getProvinceNearby()+
//                "\nCITY: "+results.get(0).getCity()+
//                "\nSTREET: "+results.get(0).getStreet()+
//                "\nDISTRICT: "+results.get(0).getDistrict()+
//                "\nTEHSIL: "+results.get(0).getTehsil()+
//                "\nSECTOR: "+results.get(0).getSector()
//
//
//        );

        strResults = new ArrayList<>();
        for (Place place : results) {
            strResults.add("ADD: " + place.getAddressNearby()
                    + "\n" + "Tehsil: " +place.getTehsil() + "\n" + "District: " +place.getDistrict() );
        }

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, strResults);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Place place = results.get(i);
            String strLocation = place.getAddressNearby()
                    + "\n" + place.getY() + "," + place.getX();
            Log.i(StringUtils.TAG, strLocation);
            /*CommonUtils.showToast(ActivitySearch.this, strLocation,
                    Toast.LENGTH_SHORT, true);*/
        });
    }

    void clearList() {
        if (strResults != null)
            strResults.clear();
        if (adapter != null && strResults != null) {
            strResults.size();
        }
        if (adapter != null)
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
