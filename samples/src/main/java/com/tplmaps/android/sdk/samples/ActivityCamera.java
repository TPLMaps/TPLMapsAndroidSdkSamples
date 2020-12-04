package com.tplmaps.android.sdk.samples;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tplmaps.android.R;
import com.tplmaps3d.CameraPosition;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.sdk.model.Bounds;

public class ActivityCamera extends AppCompatActivity implements MapView.OnMapReadyCallback {
    private static final String TAG = ActivityCamera.class.getSimpleName();
    private MapView mMapView;
    private MapController mMapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Getting MapView resource from layout
        mMapView = findViewById(R.id.map);
        // Calling MapView's onCreate() lifecycle method
        mMapView.onCreate(savedInstanceState);
        // Loading map Asynchronously vie registering call
        mMapView.loadMapAsync(this);

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
        mMapController = mapController;
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

        // Settings map location permission and setting related configuration
        mapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialog(getString(R.string.dialog_reason_title),
                        getString(R.string.dialog_reason_message));
        // Loading Default Map UI Controls
        mapController.getUiSettings().showZoomControls(true);
        mapController.getUiSettings().showMyLocationButton(true);

        // Camera Change (ChangeStart, Change, ChangeEnd) listeners
        mapController.setOnCameraChangeStartedListener(cameraPostion ->
                Log.i(TAG, "Camera Change Started"));
        mapController.setOnCameraChangeListener(cameraPosition ->
                Log.i(TAG, "Camera Changing"));
        mapController.setOnCameraChangeEndListener(cameraPosition ->
                Log.i(TAG, "Camera Change End: " + cameraPosition.toString()));
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
        if (mMapController != null)
            mMapController.onActivityResult(requestCode, resultCode, data);
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
