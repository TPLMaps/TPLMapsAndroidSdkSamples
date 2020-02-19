package com.tplmaps.android.sdk.samples.activities;

import android.graphics.PointF;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.tplmaps.android.R;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.TouchInput;
import com.tplmaps3d.sdk.OnPoiClickListener;

import java.text.DecimalFormat;

public class ActivityMapGestures extends BaseMapActivity implements CompoundButton.OnCheckedChangeListener {

    //private static final String TAG = ActivityMaps.class.getSimpleName();
    TextView tvListener, tvValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_gestures);

        onMapCreate(savedInstanceState);

        tvListener = findViewById(R.id.tv_listener);
        tvValues = findViewById(R.id.tv_value);

        CheckBox cbDoubleTapZoomGesture = findViewById(R.id.cb_dtzl);
        cbDoubleTapZoomGesture.setChecked(true);
        cbDoubleTapZoomGesture.setOnCheckedChangeListener(this);

        CheckBox cbMapAllGestures = findViewById(R.id.cb_mag);
        cbMapAllGestures.setChecked(true);
        cbMapAllGestures.setOnCheckedChangeListener(this);

        CheckBox cbClick = findViewById(R.id.cb_click);
        cbClick.setChecked(false);
        cbClick.setOnCheckedChangeListener(this);

        CheckBox cbClickDouble = findViewById(R.id.cb_click_double);
        cbClickDouble.setChecked(false);
        cbClickDouble.setOnCheckedChangeListener(this);

        CheckBox cbClickLong = findViewById(R.id.cb_click_long);
        cbClickLong.setChecked(false);
        cbClickLong.setOnCheckedChangeListener(this);

        CheckBox cbGesturePan = findViewById(R.id.cb_gesture_pan);
        cbGesturePan.setChecked(false);
        cbGesturePan.setOnCheckedChangeListener(this);

        CheckBox cbGestureRotate = findViewById(R.id.cb_gestures_rotate);
        cbGestureRotate.setChecked(false);
        cbGestureRotate.setOnCheckedChangeListener(this);

        CheckBox cbGestureScale = findViewById(R.id.cb_gesture_scale);
        cbGestureScale.setChecked(false);
        cbGestureScale.setOnCheckedChangeListener(this);

        CheckBox cbGestureShove = findViewById(R.id.cb_gesture_shove);
        cbGestureShove.setChecked(false);
        cbGestureShove.setOnCheckedChangeListener(this);

        CheckBox cbClickPOI = findViewById(R.id.cb_click_poi);
        cbClickPOI.setChecked(false);
        cbClickPOI.setOnCheckedChangeListener(this);

        getMapView().setLogo(Gravity.TOP | Gravity.END);
    }

    private MapController mMapController;

    @Override
    public void onMapReady(final MapController mapController) {

        mMapController = mapController;

        mapController.setPickRadius(getResources().getInteger(R.integer.pick_radius));

        // Loading Default Map Controls
        mapController.setMaxTilt(85);
        mapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent("Permission Required",
                        "Location permission is required for the application to show your" +
                                " precise and accurate location on map");
        mapController.getUiSettings().showZoomControls(true);
        mapController.getUiSettings().showMyLocationButton(true);
        mapController.getUiSettings().showCompass(true);

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
                    mMapController.setTapResponder(register ? new TouchInput.TapResponder() {
                        @Override
                        public boolean onSingleTapUp(float x, float y) {
                            return false;
                        }

                        @Override
                        public boolean onSingleTapConfirmed(float x, float y) {
                            tvListener.setText(getString(R.string.click_map));
                            LngLat lngLat = mMapController.screenPositionToLngLat(new PointF(x, y));
                            String text = roundDecimalsUpto(lngLat.latitude, 4)
                                    + ", " + roundDecimalsUpto(lngLat.longitude, 4);
                            tvValues.setText(text);
                            return false;
                        }
                    } : null);
                }
                break;

            case CLICK_DOUBLE:
                if (mMapController != null) {
                    mMapController.setDoubleTapResponder(register ? (TouchInput.DoubleTapResponder) (x, y) -> {
                        LngLat lngLat = mMapController.screenPositionToLngLat(new PointF(x, y));
                        tvListener.setText(getString(R.string.click_double_map));
                        String text = roundDecimalsUpto(lngLat.latitude, 4)
                                + ", " + roundDecimalsUpto(lngLat.longitude, 4);
                        tvValues.setText(text);
                        return false;
                    } : null);
                }
                break;

            case CLICK_LONG:
                if (mMapController != null) {
                    mMapController.setLongPressResponder(register ? (TouchInput.LongPressResponder) (x, y) -> {
                        LngLat lngLat = mMapController.screenPositionToLngLat(new PointF(x, y));
                        tvListener.setText(getString(R.string.click_long_map));
                        String text = roundDecimalsUpto(lngLat.latitude, 4)
                                + ", " + roundDecimalsUpto(lngLat.longitude, 4);
                        tvValues.setText(text);
                    } : null);
                }
                break;

            case PAN:
                if (mMapController != null) {
                    mMapController.setPanResponder(register ? new TouchInput.PanResponder() {
                        @Override
                        public boolean onPanBegin() {
                            return false;
                        }

                        @Override
                        public boolean onPan(float startX, float startY, float endX, float endY) {
                            tvListener.setText(getString(R.string.gesture_pan_map));
                            tvValues.setText("");
                            return false;
                        }

                        @Override
                        public boolean onPanEnd() {
                            return false;
                        }

                        @Override
                        public boolean onFling(float posX, float posY, float velocityX, float velocityY) {
                            tvListener.setText(getString(R.string.gesture_fling_map));
                            tvValues.setText("");
                            return false;
                        }

                        @Override
                        public boolean onCancelFling() {
                            return false;
                        }
                    } : null);
                }
                break;

            case ROTATE:
                if (mMapController != null) {
                    mMapController.setRotateResponder(register ? new TouchInput.RotateResponder() {
                        @Override
                        public boolean onRotateBegin() {
                            return false;
                        }

                        @Override
                        public boolean onRotate(float x, float y, float rotation) {
                            tvListener.setText(getString(R.string.gesture_rotate_map));
                            String valueRotation = "Rotation: " + roundDecimalsUpto(rotation, 2);
                            tvValues.setText(valueRotation);
                            return false;
                        }

                        @Override
                        public boolean onRotateEnd() {
                            return false;
                        }
                    } : null);
                }
                break;

            case SCALE:
                if (mMapController != null) {
                    mMapController.setScaleResponder(register ? new TouchInput.ScaleResponder() {
                        @Override
                        public boolean onScaleBegin() {
                            return false;
                        }

                        @Override
                        public boolean onScale(float x, float y, float scale, float velocity) {
                            tvListener.setText(getString(R.string.gesture_scale_map));
                            String value = "Scale: " + roundDecimalsUpto(scale, 2);
                            tvValues.setText(value);
                            return false;
                        }

                        @Override
                        public boolean onScaleEnd() {
                            return false;
                        }
                    } : null);
                }
                break;

            case SHOVE:
                if (mMapController != null) {
                    mMapController.setShoveResponder(register ? new TouchInput.ShoveResponder() {
                        @Override
                        public boolean onShoveBegin() {
                            return false;
                        }

                        @Override
                        public boolean onShove(float distance) {
                            tvListener.setText(getString(R.string.gesture_shove_map));
                            String value = "Shove: " + roundDecimalsUpto(distance, 2);
                            tvValues.setText(value);
                            return false;
                        }

                        @Override
                        public boolean onShoveEnd() {
                            return false;
                        }

                    } : null);
                }
                break;

            case POI:
                if (mMapController != null) {
                    mMapController.setOnPoiClickListener(register ? (OnPoiClickListener) place -> {
                        LngLat lngLat = mMapController.screenPositionToLngLat(new PointF((float) place.lngLat.longitude,
                                (float) place.lngLat.latitude));
                        tvListener.setText(getString(R.string.click_poi));
                        String text = place.name + ", " + roundDecimalsUpto(lngLat.latitude, 4)
                                + ", " + roundDecimalsUpto(lngLat.longitude, 4);
                        tvValues.setText(text);
                    } : null);
                }
                break;
        }
    }

    double roundDecimalsUpto(double value, int upto) {
        if (upto <= 0)
            return value;

        StringBuilder pattern = new StringBuilder("#.");

        int digits = upto;
        while (digits > 0) {
            pattern.append("#");
            digits -= 1;
        }

        DecimalFormat decimalFormat = new DecimalFormat(pattern.toString());
        return Double.valueOf(decimalFormat.format(value));
    }
}
