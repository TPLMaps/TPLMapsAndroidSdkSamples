package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapMode;
import com.tplmaps3d.MapView;

public class ActivityMapFeatures extends AppCompatActivity implements MapView.OnMapReadyCallback,
        CompoundButton.OnCheckedChangeListener {

    //private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapView mMapView;

    final boolean ENABLE_NIGHT_MODE_DEFAULT = false;
    final boolean ENABLE_BUILDINGS_DEFAULT = true;
    final boolean ENABLE_POIS_DEFAULT = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_features);

        // Initializing and getting MapView resource
        mMapView = (MapView) findViewById(R.id.map);

        CheckBox cbNightMode = ((CheckBox) findViewById(R.id.cb_night_mode));
        cbNightMode.setOnCheckedChangeListener(this);
        cbNightMode.setChecked(ENABLE_NIGHT_MODE_DEFAULT);
        // Setting Night Mode as default
        onCheckedChanged(cbNightMode, ENABLE_NIGHT_MODE_DEFAULT);

        CheckBox cbBuildings = ((CheckBox) findViewById(R.id.cb_buildings));
        cbBuildings.setOnCheckedChangeListener(this);
        cbBuildings.setChecked(ENABLE_BUILDINGS_DEFAULT);
        // Setting Buildings as default
        onCheckedChanged(cbBuildings, ENABLE_BUILDINGS_DEFAULT);

        CheckBox cbPOIs = ((CheckBox) findViewById(R.id.cb_pois));
        cbPOIs.setOnCheckedChangeListener(this);
        cbPOIs.setChecked(ENABLE_POIS_DEFAULT);
        // Setting POIs as default
        onCheckedChanged(cbPOIs, ENABLE_POIS_DEFAULT);

        // OR you can make settings for map defaults by calling these methods but before loading map
        /*mMapView.setMapMode(MapMode.DEFAULT);
        mMapView.setBuildingsEnabled(true);
        mMapView.setPOIsEnabled(true);*/

        // Loading map Asynchronously
        MapUtils.initAndLoadMaps(savedInstanceState, mMapView, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mMapView != null)
            mMapView.onDestroy();
    }

    private MapController mMapController;

    @Override
    public void onMapReady(final MapController mapController) {
        // TODO: Map loaded and ready, write your map tasks here
        mMapController = mapController;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

        switch (compoundButton.getId()) {

            case R.id.cb_night_mode:
                mMapView.setMapMode((checked) ? MapMode.NIGHT : MapMode.DEFAULT);
                break;

            case R.id.cb_buildings:
                mMapView.setBuildingsEnabled(checked);
                break;

            case R.id.cb_pois:
                mMapView.setPOIsEnabled(checked);
                break;
        }
    }
}
