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

public class ActivityCamera extends BaseMapActivity implements MapView.OnMapReadyCallback {
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
            mMapView.getMapController().animateCamera(new CameraPosition.Builder(mMapView.getMapController())
                    .tilt(0)
                    .rotation(0f)
                    .build(), 0);
            LngLat southwest = new LngLat(73.035070, 33.637313);
            LngLat northeast = new LngLat(73.041247, 33.659408);
            // Zoom camera to bounds of Sector I-10, Islamabad with animation
            mMapView.getMapController().setBounds(new Bounds(southwest, northeast), 300, 1000);
        });
    }

    @Override
    public void onMapReady(MapController mapController) {
        // TODO: Do your map tasks here

        // Setting map max tilt value
        mapController.setMaxTilt(85);

        // Applying animation to map camera
        mapController.animateCamera(new CameraPosition.Builder(mapController)
                .position(new LngLat(73.0684356, 33.6934396))
                .zoom(18.5f)
                .rotation(48.0f)
                .tilt(1f)
                .build(), 2000);

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
        mapController.setOnCameraChangeStartedListener(cameraPostion -> Log.i(TAG, "Camera Change Started"));
        mapController.setOnCameraChangeListener(cameraPosition -> Log.i(TAG, "Camera Changing"));
        mapController.setOnCameraChangeEndListener(cameraPosition -> {
            Log.i(TAG, "Camera Change End: " + cameraPosition.toString());
        });
    }
}
