package com.tplmaps.android.sdk.samples;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        // TODO: Do your map tasks here

        // Setting map max tilt value
        mapController.setMaxTilt(85);

        // Settings map location permission and setting related configuration
        mapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent(getString(R.string.dialog_reason_title),
                        getString(R.string.dialog_reason_message));

        // Setting controls here because functionality of these controls belongs to the MapView
        // And MapView should be ready to perform these actions on it
        ((CheckBox) findViewById(R.id.cb_compass)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb_zoom_controls)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb_my_location_updates)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb_my_location_button)).setOnCheckedChangeListener(this);

        mapController.setOnMyLocationChangeListener(new MapController.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChanged(Location location) {
                Log.d("App", "onMyLocationChanged " + location.toString());
            }

            @Override
            public void onMyLocationFirstFix(Location location) {
                Log.d("Location", "onMyLocationFirstFix " + location.toString());
            }

            /*@Override
            public void onMyLastLocationUpdate(Location location) {
                Log.d("Location", "onMyLastLocationUpdate " + location.toString());
            }*/
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mMapController != null)
            mMapController.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (mMapController != null)
            mMapController.onActivityResult(requestCode, resultCode, data);*/
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
            case R.id.cb_my_location_updates:
                try {
                    if (mMapController != null) {
                        // Enable/Disable My Location
                        mMapController.setMyLocationEnabled(isChecked,
                                MapController.MyLocationArg.ZOOM_LOCATION_ON_FIRST_FIX);
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
