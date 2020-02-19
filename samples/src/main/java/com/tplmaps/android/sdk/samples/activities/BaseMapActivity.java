package com.tplmaps.android.sdk.samples.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;

/**
 * Activity is taking care of {@link MapView}'s component initialization and loading
 * plus {@link MapView}'s lifecycle methods.
 */
@SuppressLint("Registered")
public class BaseMapActivity extends AppCompatActivity implements MapView.MapReadyCallback {

    protected MapView mMapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onMapCreate(@Nullable Bundle savedInstanceState) {
        // Getting MapView resource from layout
        mMapView = findViewById(R.id.map);
        // Calling MapView's onCreate() lifecycle method
        mMapView.onCreate(savedInstanceState);
        // Loading map Asynchronously vie registering call
        MapUtils.initAndLoadMaps(savedInstanceState, mMapView, this);
    }

    public MapView getMapView() {
        return mMapView;
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
    public void onMapReady(MapController mapController) {
    }
}
