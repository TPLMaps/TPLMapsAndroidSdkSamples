package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;

public class ActivityUIControls extends AppCompatActivity implements MapView.OnMapReadyCallback,
        CompoundButton.OnCheckedChangeListener {

    private static final String TAG = ActivityUIControls.class.getSimpleName();

    private MapView mMapView;

    private MapController mMapController;

    private CheckBox cbMyLocationButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ui_controls);

        mMapView = (MapView) findViewById(R.id.map);
        MapUtils.initAndLoadMaps(savedInstanceState, mMapView, this);
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
    protected void onStart() {
        super.onStart();

        if (mMapView != null)
            mMapView.onStart();
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
    public void onMapReady(final MapController mapController) {

        mMapController = mapController;

        mMapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent("Permission Required", "Location permission is required for " +
                        "the application to show your precise and accurate location on map");

        // Setting controls here because functionality of these controls belongs to the MapView
        // And MapView should be ready to perform these actions on it
        CheckBox cbCompass = ((CheckBox) findViewById(R.id.cb_compass));
        cbCompass.setOnCheckedChangeListener(this);
        CheckBox cbZoomControls = ((CheckBox) findViewById(R.id.cb_zoom_controls));
        cbZoomControls.setOnCheckedChangeListener(this);
        CheckBox cbMyLocation = ((CheckBox) findViewById(R.id.cb_my_location));
        cbMyLocation.setOnCheckedChangeListener(this);
        cbMyLocationButton = ((CheckBox) findViewById(R.id.cb_my_location_button));
        cbMyLocationButton.setOnCheckedChangeListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mMapController != null)
            mMapController.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        switch (compoundButton.getId()) {

            case R.id.cb_compass:
                // Show compass
                if (mMapController != null)
                    mMapController.getUiSettings().showCompass(isChecked);

                break;

            case R.id.cb_zoom_controls:
                // Show zoom controls
                if (mMapController != null)
                    mMapController.getUiSettings().showZoomControls(isChecked);
                break;

            case R.id.cb_my_location:
                try {
                    if (mMapController != null) {
                        // Enable/Disable My Location
                        mMapController.setMyLocationEnabled(isChecked);

                        // Check/UnCheck My Location button's Checkbox
                        cbMyLocationButton.setChecked(isChecked);
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.cb_my_location_button:
                try {
                    // Show My Location Button
                    if (mMapController != null)
                        mMapController.getUiSettings().showMyLocationButton(isChecked);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
