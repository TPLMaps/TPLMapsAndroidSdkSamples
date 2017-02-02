package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.sdk.model.TPLMarker;
import com.tplmaps3d.sdk.model.TPLMarkerOptions;

public class ActivityShapes extends AppCompatActivity implements MapView.OnMapReadyCallback {

    private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes);

        mMapView = (MapView) findViewById(R.id.map);

        MapUtils.initAndLoadMaps(savedInstanceState, mMapView, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mMapView != null)
            mMapView.onDestroy();
    }

    @Override
    public void onMapReady(final MapController mapController) {

        mapController.setPosition(new LngLat(73.093104, 33.730494));
        mapController.setZoom(15);

        final TPLMarker marker = addMarker(new LngLat(73.093104, 33.730494), "marker1");
        addMarker(new LngLat(73.092159, 33.728945), "marker2");

        mMapView.setOnMarkerClickListener(new MapView.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(TPLMarker tplMarker) {
                if(marker.equals(tplMarker)) {
                    Log.i(TAG, "Called: id = " + tplMarker.getId());
                    Log.i(TAG, "Called: title = " + tplMarker.getTitle());
                    Log.i(TAG, "Called: Snippet = " + tplMarker.getSnippet());
                }
                return false;
            }
        });

        /*String pointStyle = "{ style: 'points', interactive: true, color: 'white', size: [50px, 50px], order: 2000, collide: false }";
        Marker marker = mapController.addMarker();
        marker.setStyling(pointStyle);
        marker.setDrawable(R.drawable.marker_default);
        marker.setPoint(new LngLat(73.092159, 33.728945));
        marker.setRotation(Math.toRadians(50));*/
    }

    private TPLMarker addMarker(LngLat lngLat, String title) {

        return mMapView.addMarker(new TPLMarkerOptions()
                .position(lngLat)
                .title(title)
                .snippet("This is my spot!"));
    }
}
