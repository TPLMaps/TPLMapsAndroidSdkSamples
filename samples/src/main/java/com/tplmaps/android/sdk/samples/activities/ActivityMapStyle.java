package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;

import com.tplmaps.android.R;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;

public class ActivityMapStyle extends BaseMapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_style);

        // Getting MapView from layout
        MapView map = findViewById(R.id.map);
        // Setting custom map style before map ready (pre-call)
        //setMapStyle(map);
        // Loading MapView asynchronously via registering callback
        map.loadMapAsync(this);
    }

    @Override
    public void onMapReady(MapController mapController) {

        // TODO: Do you map tasks here

        // Setting custom map style after map ready (post-call)
        setMapStyle(findViewById(R.id.map));

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
}
