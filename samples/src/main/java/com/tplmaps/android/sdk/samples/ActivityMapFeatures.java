package com.tplmaps.android.sdk.samples;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tplmaps.android.R;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapMode;
import com.tplmaps3d.MapView;

public class ActivityMapFeatures extends AppCompatActivity implements MapView.OnMapReadyCallback,
        CompoundButton.OnCheckedChangeListener {

    //private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapView mMapView;
    private MapController mMapController;

    final boolean ENABLE_NIGHT_MODE_DEFAULT = false;
    final boolean ENABLE_BUILDINGS_DEFAULT = true;
    final boolean ENABLE_POIS_DEFAULT = true;
    final boolean ENABLE_TRAFFIC_DEFAULT = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_features);

        // Getting MapView resource from layout
        mMapView = findViewById(R.id.map);
        // Calling MapView's onCreate() lifecycle method
        mMapView.onCreate(savedInstanceState);
        // Loading map Asynchronously vie registering call
        mMapView.loadMapAsync(this);

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
        mMapController = mapController;

        // TODO: Do your map tasks here

        // Setting map max tilt value
        mapController.setMaxTilt(85);
        // Settings map location permission and setting related configuration
        mapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent(getString(R.string.dialog_reason_title),
                        getString(R.string.dialog_reason_message));
        // Loading Default Map UI Controls
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

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (mMapController != null)
//            mMapController.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (mMapController != null)
            mMapController.onActivityResult(requestCode, resultCode, data);*/
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
