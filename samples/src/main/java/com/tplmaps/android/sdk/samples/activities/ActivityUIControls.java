package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.CommonUtils;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;

public class ActivityUIControls extends AppCompatActivity implements MapView.OnMapReadyCallback {

    //private static final String TAG = ActivityUIControls.class.getSimpleName();

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_controls);

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
        // Map loaded and ready, use this instantiated mapController param
        // TODO: Write you map tasks here
        CommonUtils.showToast(this, "Map Ready", Toast.LENGTH_SHORT);
    }
}
