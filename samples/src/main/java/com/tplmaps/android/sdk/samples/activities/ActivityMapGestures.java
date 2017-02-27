package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.TouchInput;

public class ActivityMapGestures extends AppCompatActivity implements MapView.OnMapReadyCallback,
        CompoundButton.OnCheckedChangeListener {

    //private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_gestures);

        // Initializing and getting MapView resource
        mMapView = (MapView) findViewById(R.id.map);
        // Loading map Asynchronously
        MapUtils.initAndLoadMaps(savedInstanceState, mMapView, this);

        CheckBox cbDoubleTapZoomGesture = ((CheckBox) findViewById(R.id.cb_dtzl));
        cbDoubleTapZoomGesture.setChecked(true);
        cbDoubleTapZoomGesture.setOnCheckedChangeListener(this);

        CheckBox cbMapAllGestures = ((CheckBox) findViewById(R.id.cb_mag));
        cbMapAllGestures.setChecked(true);
        cbMapAllGestures.setOnCheckedChangeListener(this);
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

        mMapController = mapController;

        // TODO: Map loaded and ready, write your map tasks here
    }


    enum ListenerType {
        CLICK_SINGLE, CLICK_DOUBLE, CLICK_LONG, PAN, ROTATE, SCALE, SHOVE, POI
    }

    public void registerListener(ListenerType listenerType, boolean register) {
        switch (listenerType) {
            case CLICK_SINGLE:
                if (mMapController != null) {
                    mMapController.setOnMapClickListener(register ? new MapController.OnMapClickListener() {
                        @Override
                        public void onMapClick(LngLat lngLat) {

                        }
                    } : null);
                }
                break;
            case CLICK_DOUBLE:
                if (mMapController != null) {
                    mMapController.setOnMapDoubleClickListener(register ? new TouchInput.DoubleTapResponder() {
                        @Override
                        public boolean onDoubleTap(float x, float y) {
                            return false;
                        }
                    } : null);
                }
                break;
            case CLICK_LONG:
                if (mMapController != null) {
                    mMapController.setOnMapLongClickListener(register ? new TouchInput.LongPressResponder() {
                        @Override
                        public void onLongPress(float x, float y) {

                        }
                    } : null);
                }
                break;
            case PAN:
                if (mMapController != null) {
                    mMapController.setOnMapPanListener(register ? new TouchInput.PanResponder() {
                        @Override
                        public boolean onPan(float startX, float startY, float endX, float endY) {
                            return false;
                        }

                        @Override
                        public boolean onFling(float posX, float posY, float velocityX, float velocityY) {
                            return false;
                        }
                    } : null);
                }
                break;
            case ROTATE:
                break;
            case SCALE:
                break;
            case SHOVE:
                break;
            case POI:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        switch (compoundButton.getId()) {
            case R.id.cb_dtzl:
                if (mMapController != null) {
                    // Set enable/disable double tap zoom gesture
                    mMapController.getUiSettings().setDoubleTapZoomInGestureEnabled(checked);
                }
                break;

            case R.id.cb_mag:
                if (mMapController != null) {
                    // Set enable/disable all gestures
                    mMapController.getUiSettings().setAllMapGesturesEnabled(checked);
                }
                break;
        }
    }
}
