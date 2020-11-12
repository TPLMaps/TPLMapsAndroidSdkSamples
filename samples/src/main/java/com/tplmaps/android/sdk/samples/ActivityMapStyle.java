package com.tplmaps.android.sdk.samples;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tplmaps.android.R;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;

public class ActivityMapStyle extends AppCompatActivity implements MapView.OnMapReadyCallback {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_style);

        // Getting MapView resource from layout
        mMapView = findViewById(R.id.map);
        // Calling MapView's onCreate() lifecycle method
        mMapView.onCreate(savedInstanceState);
        // Setting custom map style before map ready (pre-execution), map style will be
        // applied on first load/rendering
        //setMapStyle(mMapView);
        // Loading MapView asynchronously via registering callback
        mMapView.loadMapAsync(this);
    }

    @Override
    public void onMapReady(MapController mapController) {

        // TODO: Do you map tasks here

        // Setting custom map style after map ready (post-call)
        setMapStyle(mMapView);

        // Loading Default Map Controls
        mapController.setMaxTilt(85);
        mapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent("Permission Required",
                        "Location permission is required for the application to show your" +
                                " precise and accurate location on map");
        mapController.getUiSettings().showZoomControls(true);
        mapController.getUiSettings().showMyLocationButton(true);
    }

    private void setMapStyle(MapView map) {
        // Set style specified in a resource file
        map.setMapStyle(R.raw.sample_map_style1);
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
}
