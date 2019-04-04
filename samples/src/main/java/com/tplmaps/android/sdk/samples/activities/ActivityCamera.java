package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.CameraPosition;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.sdk.model.Bounds;

public class ActivityCamera extends AppCompatActivity implements MapView.OnMapReadyCallback,
        MapController.OnCameraChangeStartedListener, MapController.OnCameraChangeListener,
        MapController.OnCameraChangeEndListener {

    private MapView mMapView;
    private static final String TAG = ActivityCamera.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Initializing and getting MapView resource
        mMapView = (MapView) findViewById(R.id.map);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMapView.getMapController() == null)
                    return;

                // Zoom camera to bounds of Sector I-10, Islamabad with animation
                mMapView.getMapController().setBounds(
                        new Bounds(new LngLat(73.035070, 33.637313),
                                new LngLat(73.041247, 33.659408)), 200, 1000);

                // Zoom to area bound in a rect specified on screen
                /*mMapView.getMapController().setBoundsInWindow(
                        new Bounds(new LngLat(73.035070, 33.637313), new LngLat(73.041247, 33.659408)),
                        new Rect(50, 50, 720, 1118),
                        150, 2000);*/
            }
        });

        // Loading map Asynchronously
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
                .tilt(0.9F)
                .rotation(13.0F)
                .build(), 2000);

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
