package com.tplmaps.android.sdk.samples;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.tplmaps.android.R;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;

public class ActivityUIControls extends AppCompatActivity implements MapView.OnMapReadyCallback,
        CompoundButton.OnCheckedChangeListener {

    //private static final String TAG = ActivityUIControls.class.getSimpleName();

    private MapController mMapController;
    private MapView mMapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_controls);

        // Getting MapView resource from layout
        mMapView = findViewById(R.id.map);
        // Calling MapView's onCreate() lifecycle method
        mMapView.onCreate(savedInstanceState);
        // Loading map Asynchronously vie registering call
        mMapView.loadMapAsync(this);
    }

    @Override
    public void onMapReady(final MapController mapController) {

        mMapController = mapController;

        mMapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent("Permission Required",
                        "Location permission is required for the application to show your" +
                                " precise and accurate location on map");

        // Setting controls here because functionality of these controls belongs to the MapView
        // And MapView should be ready to perform these actions on it
        CheckBox cbCompass = findViewById(R.id.cb_compass);
        cbCompass.setOnCheckedChangeListener(this);
        CheckBox cbZoomControls = findViewById(R.id.cb_zoom_controls);
        cbZoomControls.setOnCheckedChangeListener(this);
        CheckBox cbMyLocation = findViewById(R.id.cb_my_location);
        cbMyLocation.setOnCheckedChangeListener(this);
        CheckBox cbMyLocationButton = findViewById(R.id.cb_my_location_button);
        cbMyLocationButton.setOnCheckedChangeListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
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
                        mMapController.setMyLocationEnabled(isChecked,
                                MapController.MyLocationArg.ZOOM_LOCATION_UPDATES);
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
