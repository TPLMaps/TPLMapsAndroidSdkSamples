package com.tplmaps.android.sdk.samples.activities;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.TouchInput;
import com.tplmaps3d.sdk.model.PointOfInterest;

import java.text.DecimalFormat;

public class ActivityMapGestures extends AppCompatActivity implements MapView.OnMapReadyCallback,
        CompoundButton.OnCheckedChangeListener {

    //private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapView mMapView;

    TextView tvListener, tvValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_gestures);

        // Initializing and getting MapView resource
        mMapView = (MapView) findViewById(R.id.map);
        // Loading map Asynchronously
        MapUtils.initAndLoadMaps(savedInstanceState, mMapView, this);


        tvListener = (TextView) findViewById(R.id.tv_listener);
        tvValues = (TextView) findViewById(R.id.tv_value);

        CheckBox cbDoubleTapZoomGesture = ((CheckBox) findViewById(R.id.cb_dtzl));
        cbDoubleTapZoomGesture.setChecked(true);
        cbDoubleTapZoomGesture.setOnCheckedChangeListener(this);

        CheckBox cbMapAllGestures = ((CheckBox) findViewById(R.id.cb_mag));
        cbMapAllGestures.setChecked(true);
        cbMapAllGestures.setOnCheckedChangeListener(this);

        CheckBox cbClick = ((CheckBox) findViewById(R.id.cb_click));
        cbClick.setChecked(false);
        cbClick.setOnCheckedChangeListener(this);

        CheckBox cbClickDouble = ((CheckBox) findViewById(R.id.cb_click_double));
        cbClickDouble.setChecked(false);
        cbClickDouble.setOnCheckedChangeListener(this);

        CheckBox cbClickLong = ((CheckBox) findViewById(R.id.cb_click_long));
        cbClickLong.setChecked(false);
        cbClickLong.setOnCheckedChangeListener(this);

        CheckBox cbGesturePan = ((CheckBox) findViewById(R.id.cb_gesture_pan));
        cbGesturePan.setChecked(false);
        cbGesturePan.setOnCheckedChangeListener(this);

        CheckBox cbGestureRotate = ((CheckBox) findViewById(R.id.cb_gestures_rotate));
        cbGestureRotate.setChecked(false);
        cbGestureRotate.setOnCheckedChangeListener(this);

        CheckBox cbGestureScale = ((CheckBox) findViewById(R.id.cb_gesture_scale));
        cbGestureScale.setChecked(false);
        cbGestureScale.setOnCheckedChangeListener(this);

        CheckBox cbGestureShove = ((CheckBox) findViewById(R.id.cb_gesture_shove));
        cbGestureShove.setChecked(false);
        cbGestureShove.setOnCheckedChangeListener(this);

        CheckBox cbClickPOI = ((CheckBox) findViewById(R.id.cb_click_poi));
        cbClickPOI.setChecked(false);
        cbClickPOI.setOnCheckedChangeListener(this);
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

        mapController.setPickRadius(getResources().getInteger(R.integer.pick_radius));

        // TODO: Map loaded and ready, write your map tasks here
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

            case R.id.cb_click:
                registerListener(ListenerType.CLICK_SINGLE, checked);
                break;

            case R.id.cb_click_double:
                registerListener(ListenerType.CLICK_DOUBLE, checked);
                break;

            case R.id.cb_click_long:
                registerListener(ListenerType.CLICK_LONG, checked);
                break;

            case R.id.cb_gesture_pan:
                registerListener(ListenerType.PAN, checked);
                break;

            case R.id.cb_gestures_rotate:
                registerListener(ListenerType.ROTATE, checked);
                break;

            case R.id.cb_gesture_scale:
                registerListener(ListenerType.SCALE, checked);
                break;

            case R.id.cb_gesture_shove:
                registerListener(ListenerType.SHOVE, checked);
                break;

            case R.id.cb_click_poi:
                registerListener(ListenerType.POI, checked);
                break;
        }
    }


    private enum ListenerType {
        CLICK_SINGLE, CLICK_DOUBLE, CLICK_LONG, PAN, ROTATE, SCALE, SHOVE, POI
    }

    public void registerListener(ListenerType listenerType, boolean register) {
        switch (listenerType) {
            case CLICK_SINGLE:
                if (mMapController != null) {
                    mMapController.setOnMapClickListener(register ? new MapController.OnMapClickListener() {
                        @Override
                        public void onMapClick(LngLat lngLat) {
                            tvListener.setText(getString(R.string.click_map));
                            tvValues.setText(roundDecimalsUpto(lngLat.latitude, 4) + ", " + roundDecimalsUpto(lngLat.longitude, 4));
                        }
                    } : null);
                }
                break;

            case CLICK_DOUBLE:
                if (mMapController != null) {
                    mMapController.setOnMapDoubleClickListener(register ? new TouchInput.DoubleTapResponder() {
                        @Override
                        public boolean onDoubleTap(float x, float y) {
                            LngLat lngLat = mMapController.screenPositionToLngLat(new PointF(x, y));
                            tvListener.setText(getString(R.string.click_double_map));
                            tvValues.setText(roundDecimalsUpto(lngLat.latitude, 4) + ", " + roundDecimalsUpto(lngLat.longitude, 4));
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
                            LngLat lngLat = mMapController.screenPositionToLngLat(new PointF(x, y));
                            tvListener.setText(getString(R.string.click_long_map));
                            tvValues.setText(roundDecimalsUpto(lngLat.latitude, 4) + ", " + roundDecimalsUpto(lngLat.longitude, 4));
                        }
                    } : null);
                }
                break;

            case PAN:
                if (mMapController != null) {
                    mMapController.setOnMapPanListener(register ? new TouchInput.PanResponder() {
                        @Override
                        public boolean onPan(float startX, float startY, float endX, float endY) {
                            tvListener.setText(getString(R.string.gesture_pan_map));
                            tvValues.setText("");
                            return false;
                        }

                        @Override
                        public boolean onFling(float posX, float posY, float velocityX, float velocityY) {
                            tvListener.setText(getString(R.string.gesture_fling_map));
                            tvValues.setText("");
                            return false;
                        }
                    } : null);
                }
                break;

            case ROTATE:
                if (mMapController != null) {
                    mMapController.setOnMapRotateListener(register ? new TouchInput.RotateResponder() {
                        @Override
                        public boolean onRotate(float x, float y, float rotation) {
                            tvListener.setText(getString(R.string.gesture_rotate_map));
                            String valueRotation = "Rotation: " +
                                    roundDecimalsUpto(rotation, 2);
                            tvValues.setText(valueRotation);
                            return false;
                        }
                    } : null);
                }
                break;

            case SCALE:
                /*if (mMapController != null) {
                    mMapController.setOnMapScaleListener(register ? new TouchInput.ScaleResponder() {
                        @Override
                        public boolean onScale(float x, float y, float scale, float velocity) {
                            tvListener.setText(getString(R.string.gesture_rotate_map));
                            tvValues.setText(scale);
                            return false;
                        }
                    } : null);
                }*/
                break;

            case SHOVE:
                break;

            case POI:
                if (mMapController != null) {
                    mMapController.setOnPoiClickListener(register ? new MapController.OnPoiClickListener() {
                        @Override
                        public void onPoiClick(PointOfInterest place) {
                            LngLat lngLat = mMapController.screenPositionToLngLat(new PointF((float) place.lngLat.longitude,
                                    (float) place.lngLat.latitude));
                            tvListener.setText(getString(R.string.click_poi));
                            tvValues.setText(place.name + ", " + roundDecimalsUpto(lngLat.latitude, 4)
                                    + ", " + roundDecimalsUpto(lngLat.longitude, 4));
                        }
                    } : null);
                }
                break;
        }
    }

    double roundDecimalsUpto(double d, int digitsAfterDecimalPoint) {

        if (digitsAfterDecimalPoint <= 0)
            return d;

        String pattern = "#.";

        int digits = digitsAfterDecimalPoint;
        while (digits > 0) {
            pattern += "#";
            digits -= 1;
        }

        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return Double.valueOf(decimalFormat.format(d));
    }
}
