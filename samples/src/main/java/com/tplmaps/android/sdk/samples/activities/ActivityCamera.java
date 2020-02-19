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
import com.tplmaps3d.sdk.utils.MapViewUtils;

public class ActivityCamera extends BaseMapActivity implements MapView.MapReadyCallback {
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

            // Resetting tilt and rotation if made any, previously

            /*CameraPosition cameraPosition = mMapView.getMapController().getCameraPosition();
            cameraPosition.tilt = 0;
            cameraPosition.rotation = 0;
            MapViewUtils.setCameraPosition(mMapView.getMapController(), cameraPosition, null);*/

            // Zoom camera to bounds of Sector I-10, Islamabad with animation
            CameraPosition cameraPosition = mMapView.getMapController().getCameraPosition();
            cameraPosition.tilt = 0;
            cameraPosition.rotation = 0;
            mMapView.getMapController().setBounds(new Bounds(new LngLat(73.035070, 33.637313),
                    new LngLat(73.041247, 33.659408)), 200, 1000);
        });
    }

    @Override
    public void onMapReady(MapController mapController) {
        // TODO: Do your map tasks here

        // Setting map max tilt value
        mapController.setMaxTilt(85);

        // Applying animation to map camera
        CameraPosition camPosition = mMapView.getMapController().getCameraPosition();
        camPosition.latitude = 33.6934396;
        camPosition.longitude = 73.0684356;
        camPosition.zoom = 18.5f;
        camPosition.tilt = 1f;
        camPosition.rotation = 15.0f;
        MapViewUtils.setCameraPosition(mMapView.getMapController(), camPosition, null);

        // Loading Default Map Controls
        mapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent("Permission Required",
                        "Location permission is required for the application to show your" +
                                " precise and accurate location on mapController");
        mapController.getUiSettings().showZoomControls(true);
        mapController.getUiSettings().showMyLocationButton(true);

        // Camera Change (ChangeStart, Change, ChangeEnd) listeners
        mapController.getMapRenderer().setOnCameraChangeStartedListener(cameraPostion -> Log.i(TAG, "Camera Change Started"));
        mapController.getMapRenderer().setOnCameraChangeListener(cameraPosition -> Log.i(TAG, "Camera Changing"));
        mapController.getMapRenderer().setOnCameraChangeEndListener(cameraPosition -> {
            Log.i(TAG, "Camera Change End: " + cameraPosition.toString());
        });
    }
}
