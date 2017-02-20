package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;

public class ActivityMapGestures extends AppCompatActivity implements MapView.OnMapReadyCallback {

    //private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_gestures);

        // Initializing and getting MapView resource
        mMapView = (MapView) findViewById(R.id.map);

        // Loading map Asynchronously
        MapUtils.initAndLoadMaps(savedInstanceState, mMapView, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mMapView != null)
            mMapView.onDestroy();
    }

    @Override
    public void onMapReady(final MapController mapController) {

        // TODO: Map loaded and ready, write your tasks here

        // Set enable/disable double tap zoom gesture
        mapController.getUiSettings().setDoubleTapZoomInGestureEnabled(true);

        // Set enable/disable all gestures
        mapController.getUiSettings().setAllMapGesturesEnabled(false);
    }
}
