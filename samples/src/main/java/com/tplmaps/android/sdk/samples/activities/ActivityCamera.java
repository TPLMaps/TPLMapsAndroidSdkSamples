package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.CameraPosition;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;

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
        // TODO: Do you map tasks here

        mapController.setOnCameraChangeStartedListener(this);
        mapController.setOnCameraChangeListener(this);
        mapController.setOnCameraChangeEndListener(this);

        performCameraFunctions(mapController);
    }

    public void performCameraFunctions(MapController mapController) {

        // Bounds I-10 in Rect Window
        /*mapController.setBoundsInWindow(
                        new Bounds(new LngLat(73.035070, 33.637313), new LngLat(73.041247, 33.659408)),
                        new Rect(50, 50, 720, 1118),
                        150, 2000);*/

        // Point Pakistan Monument
        //mapController.setLngLatToPointOnScreen(new LngLat(73.0684356, 33.6934396), new PointF(200, 200), 1000);


        // Animate Camera to Pakistan Monument
        mapController.animateCamera(CameraPosition.builder()
                .position(new LngLat(73.0684356, 33.6934396))
                .zoom(18.3f)
                .tilt(0.9F)
                .rotation((float) Math.toRadians(13.0F))
                .build(), 2000);

        // Get Map Bounds
        //CommonUtils.showToastShort(ActivityUIControls.this, mapController.getMapBounds().toString(), true);

        // Set Position or LngLat
        //mapController.setLngLat(new LngLat(73.168284, 45.793298));

        //CommonUtils.showToastShort(ActivityUIControls.this, mapController.getMapCameraPosition().toString(), true);
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
