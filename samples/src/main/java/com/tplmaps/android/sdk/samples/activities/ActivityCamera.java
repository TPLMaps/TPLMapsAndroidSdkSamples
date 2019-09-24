package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.tplmaps.android.R;
import com.tplmaps3d.CameraPosition;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.sdk.model.Bounds;

public class ActivityCamera extends BaseMapActivity implements MapView.OnMapReadyCallback,
        MapController.OnCameraChangeStartedListener, MapController.OnCameraChangeListener,
        MapController.OnCameraChangeEndListener {
    private static final String TAG = ActivityCamera.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        onMapCreate(savedInstanceState);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if (mMapView.getMapController() == null)
                return;

            // Zoom camera to bounds of Sector I-10, Islamabad with animation
            mMapView.getMapController().setBounds(
                    new Bounds(new LngLat(73.035070, 33.637313),
                            new LngLat(73.041247, 33.659408)), 200, 1000);
        });
    }

    @Override
    public void onMapReady(MapController mapController) {
        // TODO: Do your map tasks here

        mapController.setOnCameraChangeStartedListener(this);
        mapController.setOnCameraChangeListener(this);
        mapController.setOnCameraChangeEndListener(this);

        // Setting map max tilt value
        mapController.setMaxTilt(85);

        // Applying animation to map camera
        mapController.animateCamera(CameraPosition.builder(mapController)
                .position(new LngLat(73.0684356, 33.6934396))
                .zoom(18.3f)
                .rotation(13.0F)
                .build(), 2000);

        // Loading Default Map Controls
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
    public void onCameraChangeStarted(CameraPosition cameraPosition) {
        Log.i(TAG, "Camera Change Started");
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        Log.i(TAG, "Camera Changing");
    }

    @Override
    public void onCameraChangeEnd(CameraPosition cameraPosition) {
        Log.i(TAG, "Camera Change End");
    }
}
