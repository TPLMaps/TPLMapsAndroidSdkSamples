package com.tplmaps.android.sdk.samples;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.card.MaterialCardView;
import com.tplmaps.android.R;
import com.tplmaps.sdk.places.LngLat;
import com.tplmaps.sdk.places.OnSearchResult;
import com.tplmaps.sdk.places.Params;
import com.tplmaps.sdk.places.Place;
import com.tplmaps.sdk.places.SearchManager;
import com.tplmaps.sdk.utils.StringUtils;
import com.tplmaps3d.CameraPosition;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.TouchInput;
import com.tplmaps3d.sdk.utils.CommonUtils;

import java.util.ArrayList;

public class ActivityLocateMeDemo extends AppCompatActivity implements MapView.OnMapReadyCallback , OnSearchResult {

    //private static final String TAG = ActivityMaps.class.getSimpleName();
    private MapView mMapView;
    private TextView tv_add;
    private TextView tv_full_add;
    SearchManager searchManager;
    EditText etSearch;
    LngLat searchLngLat;
    boolean isSearch = false;
    MapController mMapController;

    ListView listView;

    ImageButton ibZoomIn, ibZoomOut, ibCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_me_demo);


        // Getting MapView resource from layout
        mMapView = findViewById(R.id.map);
        tv_add = findViewById(R.id.tv_add);
        tv_full_add = findViewById(R.id.tv_full_add);
        listView= findViewById(R.id.listview);
        ibZoomIn=findViewById(R.id.ibZoomIn);
        ibZoomOut=findViewById(R.id.ibZoomOut);
        ibCurrentLocation=findViewById(R.id.ibLocateMe);

        // Get search field
        etSearch = findViewById(R.id.etSearch);



        // Initialize SearchManager
        searchManager = new SearchManager(this);

        /* MapView is initialized here, call enable traffic function now (Traffic is disabled by default)
         * Calling function here means map will be loaded with the traffic update (pre call),
         * you can also call function in onMapReady callback method means traffic update
         * will be applied when map will be on ready to render state.*/
        //mMapView.setTrafficEnabled(true);

        // Calling MapView's onCreate() lifecycle method
        mMapView.onCreate(savedInstanceState);
        // Loading map Asynchronously vie registering call
        mMapView.loadMapAsync(this);





        etSearch.setOnEditorActionListener(
                (v, actionId, event) -> {
                    // Identifier of the action. This will be either the identifier you supplied,
                    // or EditorInfo.IME_NULL if being called due to the enter key being pressed.
                    if (actionId == EditorInfo.IME_ACTION_SEARCH
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || event.getAction() == KeyEvent.ACTION_DOWN
                            && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {


                        if (!etSearch.getText().toString().isEmpty()) {
                            searchManager.requestOptimizeSearch(Params.builder()
                                    .query(etSearch.getText().toString())
                                    .location(searchLngLat)
                                    .build(), this);
                            isSearch = true;
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                        }


                        return true;
                    }
                    // Return true if you have consumed the action, else false.
                    return false;
                });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    if (listView.getVisibility()==View.VISIBLE){
                        listView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Map Zoom-In
        ibZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMapController.setZoomBy(mMapController.getMapCameraPosition().getZoom()+1);



            }
        });

        // Map Zoom-Out
        ibZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMapController.setZoomBy(mMapController.getMapCameraPosition().getZoom()-1);
            }
        });

        //Map Current Location
        ibCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CameraPosition cameraPosition = new CameraPosition(mMapController , new com.tplmaps3d.LngLat(mMapController.getMyLocation(mMapView).getLongitude() , mMapController.getMyLocation(mMapView).getLatitude()) , 14, 0 , 0 );
                mMapController.animateCamera(cameraPosition , 1000);
            }
        });

    }

    @Override
    public void onMapReady(final MapController mapController) {
        CommonUtils.showToast(this, "Map Ready", Toast.LENGTH_SHORT, false);


        mapController.removeCurrentLocationMarker();


        mMapController = mapController;
        // TODO: Map loaded and ready, perform your map operations from here
        // Setting map max tilt value
        mapController.setMaxTilt(85);

        /* MapView is initialized here, call enable traffic function now (Traffic is disabled by default)
         * Calling function here means map will update traffic when it is loaded and ready (post call),
         * you can also call function in onMapReady callback method means traffic update
         * will be applied when map will be on ready to render state.*/
        //mMapView.setTrafficEnabled(true);
        mapController.getUiSettings().showZoomControls(false);
        mapController.getUiSettings().showMyLocationButton(false);




        mapController.setMyLocationEnabled(true, MapController.MyLocationArg.ZOOM_LOCATION_ON_FIRST_FIX);


        mapController.setOnCameraChangeEndListener(new MapController.OnCameraChangeEndListener() {
            @Override
            public void onCameraChangeEnd(CameraPosition cameraPosition) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        searchLngLat = new LngLat(cameraPosition.getPosition().longitude, cameraPosition.getPosition().latitude);

                        tv_add.setText(String.format("%.4f", cameraPosition.getPosition().latitude) + " ; " + String.format("%.4f", cameraPosition.getPosition().longitude));

                        searchManager.requestReverse(Params.builder()
                                .location(new LngLat(cameraPosition.getPosition().latitude, cameraPosition.getPosition().longitude))
                                .build(), ActivityLocateMeDemo.this);


                    }
                });


            }
        });

//
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 3000);


    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mMapView != null)
            mMapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMapView != null)
            mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMapView != null)
            mMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMapView != null)
            mMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMapView != null)
            mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mMapView != null)
            mMapView.onLowMemory();
    }

    @Override
    public void onSearchResult(ArrayList<Place> results) {


        if (isSearch) {
            populateListView(results);
            isSearch = false;
        } else {
            tv_full_add.setText(results.get(0).getAddress());
        }


    }

    @Override
    public void onSearchResultNotFound(Params params, long requestTimeInMS) {

    }

    @Override
    public void onSearchRequestFailure(Exception e) {

    }

    @Override
    public void onSearchRequestCancel(Params params, long requestTimeInMS) {

    }

    @Override
    public void onSearchRequestSuspended(String errorMessage, Params params, long requestTimeInMS) {

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

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text1, strResults);

        listView.setVisibility(View.VISIBLE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {

            Place place = results.get(i);

            String strLocation = place.getName() + "\n" + place.getY() + "," + place.getX();

            listView.setVisibility(View.GONE);

            mMapController.setLngLatZoom(new com.tplmaps3d.LngLat(place.getX(), place.getY()), 15.0F);

            tv_add.setText(place.getY() + " ; " + place.getX());

            tv_full_add.setText(place.getAddress());

            clearList();

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

        etSearch.setText("");
    }
}