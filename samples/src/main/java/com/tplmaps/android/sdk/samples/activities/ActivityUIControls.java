package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;

public class ActivityUIControls extends AppCompatActivity implements MapView.OnMapReadyCallback,
        CompoundButton.OnCheckedChangeListener {

    //private static final String TAG = ActivityUIControls.class.getSimpleName();

    private MapView mMapView;
    private MapController mMapController;

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

        if (mMapView != null)
            mMapView.onDestroy();
    }

    @Override
    public void onMapReady(final MapController mapController) {
        // Map loaded and ready, use this instantiated mapController param

        mMapController = mapController;

        ((CheckBox) findViewById(R.id.cb_zoom_controls)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb_compass)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb_my_location)).setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.cb_zoom_controls:
                if (mMapController != null)
                    mMapController.getUiSettings().showZoomControls(isChecked);
                break;
            case R.id.cb_compass:
                if (mMapController != null)
                    mMapController.getUiSettings().showCompass(isChecked);

                break;
        }
    }
}
