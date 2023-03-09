package com.tplmaps.android.sdk.samples;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tplmaps.android.R;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.sdk.utils.CommonUtils;

public class ActivityMaps extends AppCompatActivity implements MapView.OnMapReadyCallback {

    //private static final String TAG = ActivityMaps.class.getSimpleName();
    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Getting MapView resource from layout
        mMapView = findViewById(R.id.map);

        /* MapView is initialized here, call enable traffic function now (Traffic is disabled by default)
         * Calling function here means map will be loaded with the traffic update (pre call),
         * you can also call function in onMapReady callback method means traffic update
         * will be applied when map will be on ready to render state.*/
        //mMapView.setTrafficEnabled(true);

        // Calling MapView's onCreate() lifecycle method
        mMapView.onCreate(savedInstanceState);
        // Loading map Asynchronously vie registering call
        mMapView.loadMapAsync(this);


    }

    @Override
    public void onMapReady(final MapController mapController) {
        CommonUtils.showToast(this, "Map Ready", Toast.LENGTH_SHORT, false);

        // TODO: Map loaded and ready, perform your map operations from here
        // Setting map max tilt value
        mapController.setMaxTilt(85);

        /* MapView is initialized here, call enable traffic function now (Traffic is disabled by default)
         * Calling function here means map will update traffic when it is loaded and ready (post call),
         * you can also call function in onMapReady callback method means traffic update
         * will be applied when map will be on ready to render state.*/
        //mMapView.setTrafficEnabled(true);
        mapController.getUiSettings().showZoomControls(true);
        mapController.getUiSettings().showMyLocationButton(true);


        mapController.setMyLocationEnabled(true , MapController.MyLocationArg.ZOOM_LOCATION_ON_FIRST_FIX);

        mapController.setOnMyLocationChangeListener(new MapController.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChanged(Location location) {

            }

            @Override
            public void onMyLocationFirstFix(Location location) {


            }
        });


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
