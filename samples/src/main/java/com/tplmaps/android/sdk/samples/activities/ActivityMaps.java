package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.widget.Toast;

import com.tplmaps.android.R;
import com.tplmaps3d.MapController;
import com.tplmaps3d.sdk.utils.CommonUtils;

public class ActivityMaps extends BaseMapActivity {

    //private static final String TAG = ActivityMaps.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // BaseMapActivity's map initializtion method
        onMapCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(final MapController mapController) {

        CommonUtils.showToast(this, "Map Ready", Toast.LENGTH_SHORT, false);

        // TODO: Map loaded and ready, perform your map operations from here
        // Setting map max tilt value
        mapController.setMaxTilt(85);
    }
}
