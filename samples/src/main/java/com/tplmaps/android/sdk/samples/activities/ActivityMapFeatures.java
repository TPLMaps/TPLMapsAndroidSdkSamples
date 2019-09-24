package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.tplmaps.android.R;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapMode;

public class ActivityMapFeatures extends BaseMapActivity implements CompoundButton.OnCheckedChangeListener {

    //private static final String TAG = ActivityMaps.class.getSimpleName();

    final boolean ENABLE_NIGHT_MODE_DEFAULT = false;
    final boolean ENABLE_BUILDINGS_DEFAULT = true;
    final boolean ENABLE_POIS_DEFAULT = true;
    final boolean ENABLE_TRAFFIC_DEFAULT = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_features);

        onMapCreate(savedInstanceState);

        CheckBox cbNightMode = findViewById(R.id.cb_night_mode);
        cbNightMode.setOnCheckedChangeListener(this);
        cbNightMode.setChecked(ENABLE_NIGHT_MODE_DEFAULT);
        // Setting Night Mode as default
        onCheckedChanged(cbNightMode, ENABLE_NIGHT_MODE_DEFAULT);

        CheckBox cbBuildings = findViewById(R.id.cb_buildings);
        cbBuildings.setOnCheckedChangeListener(this);
        cbBuildings.setChecked(ENABLE_BUILDINGS_DEFAULT);
        // Setting Buildings as default
        onCheckedChanged(cbBuildings, ENABLE_BUILDINGS_DEFAULT);

        CheckBox cbPOIs = findViewById(R.id.cb_pois);
        cbPOIs.setOnCheckedChangeListener(this);
        cbPOIs.setChecked(ENABLE_POIS_DEFAULT);
        // Setting POIs as default
        onCheckedChanged(cbPOIs, ENABLE_POIS_DEFAULT);

        CheckBox cbTraffic = findViewById(R.id.cb_traffic);
        cbTraffic.setOnCheckedChangeListener(this);
        cbTraffic.setChecked(ENABLE_TRAFFIC_DEFAULT);
        // Setting POIs as default
        onCheckedChanged(cbTraffic, ENABLE_TRAFFIC_DEFAULT);

        // OR you can make settings for map defaults by calling these methods before call to load maps
        /*mMapView.setMapMode(MapMode.DEFAULT);
        mMapView.setBuildingsEnabled(true);
        mMapView.setPOIsEnabled(true);*/
    }

    @Override
    public void onMapReady(final MapController mapController) {
        // TODO: Map loaded and ready, write your map tasks here

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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

        switch (compoundButton.getId()) {

            case R.id.cb_night_mode:
                mMapView.setMapMode((checked) ? MapMode.NIGHT : MapMode.DEFAULT);
                break;

            case R.id.cb_buildings:
                mMapView.setBuildingsEnabled(checked);
                break;

            case R.id.cb_pois:
                mMapView.setPOIsEnabled(checked);
                break;

            case R.id.cb_traffic:
                mMapView.setTrafficEnabled(checked);
                break;
        }
    }
}
