package com.tplmaps.android.sdk.samples.activities;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tplmaps.android.R;
import com.tplmaps3d.CameraPosition;
import com.tplmaps3d.IconFactory;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.Marker;
import com.tplmaps3d.MarkerOptions;
import com.tplmaps3d.sdk.CustomInfoWindowListener;
import com.tplmaps3d.sdk.utils.MapViewUtils;


public class ActivityInfoWindows extends BaseMapActivity {

    private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapController mMapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_windows);

        onMapCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(final MapController mapController) {

        mMapController = mapController;

        CameraPosition cameraPosition = mMapView.getMapController().getCameraPosition();
        cameraPosition.latitude = 33.730494;
        cameraPosition.longitude = 73.093104;
        cameraPosition.zoom = 15;
        MapViewUtils.setCameraPosition(mMapView.getMapController(), cameraPosition, null);

        normalInfoWindows();
        customInfoWindows();
        customInfoWindowsMultipleViews();

        mMapController.setOnMarkerClickListener(tplMarker -> Log.i(TAG, "Called: tplMarker tile = " + tplMarker.getTitle()));

        mMapController.setOnInfoWindowClickListener(tplMarker -> Log.i(TAG, "Called: tplMarker snippet = " + tplMarker.getDescription()));

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

    }

    private void normalInfoWindows() {

        final Marker marker1 = mMapController.addMarker(new MarkerOptions()
                .position(new LngLat(73.093104, 33.730494))
                .title("marker1")
                .description("This is my spot!").flat(false).order(1));

        marker1.showInfoWindow();
        /*marker1.setPosition(new LngLat(73.090947, 33.730283));
        marker1.setSize(new Point(102, 102));
        marker1.setIcon(IconFactory.fromResource(R.drawable.marker_default));
        marker1.setInfoWindowOffset(new Point(-150, 0));
        marker1.hideInfoWindow();
        marker1.setVisible(false);*/

        final Marker marker2 = mMapController.addMarker(new MarkerOptions()
                .position(new LngLat(73.092159, 33.728945))
                .title("marker2")
                .description("This is not my spot!").flat(false).order(0)
                .icon(IconFactory.defaultMarker(IconFactory.YELLOW)));
        marker2.showInfoWindow();

        //marker2.setTitle("Titlum");
        //marker2.setDescription("TestSnip");
    }

    private void customInfoWindows() {
        normalInfoWindows();
        // Set custom info view to all info windows
        mMapController.setCustomInfoWindow(new CustomInfoWindowListener() {
            @Override
            public View onInfoWindow(Marker tplMarker) {
                return null;
            }

            @Override
            public View onInfoWindowContent(Marker tplMarker) {
                return prepareInfoView(tplMarker);
            }
        });
    }

    Marker marker3, marker4;

    private void customInfoWindowsMultipleViews() {

        // Set different custom views to different info windows
        normalInfoWindows();
        marker3 = mMapController.addMarker(new MarkerOptions()
                .position(new LngLat(73.096719, 33.728160)).order(0)
                .icon(IconFactory.defaultMarker(IconFactory.GREEN)));

        marker3.showInfoWindow();
        /*marker3.setPosition(new LngLat(73.092159, 33.728945));
        marker3.setSize(new Point(102, 102));
        marker3.setIcon(IconFactory.fromResource(R.drawable.marker_default));
        marker3.setInfoWindowOffset(new Point(-150, 0));
        marker3.hideInfoWindow();
        marker3.setVisible(false);*/

        marker4 = mMapController.addMarker(new MarkerOptions()
                .position(new LngLat(73.090947, 33.730283)).order(0)
                .icon(IconFactory.defaultMarker(IconFactory.RED)));

        marker4.showInfoWindow();
        marker4.setInfoWindowOffset(new Point(-150, -20));

        mMapController.setCustomInfoWindow(new CustomInfoWindowListener() {
            @Override
            public View onInfoWindow(Marker tplMarker) {
                if (tplMarker == marker3)
                    return prepareInfoView(tplMarker);
                return null;
            }

            @Override
            public View onInfoWindowContent(Marker tplMarker) {
                if (tplMarker == marker4)
                    return prepareInfoView(tplMarker);
                return null;
            }
        });
    }


    private View prepareInfoView(Marker tplMarker) {
        //prepare InfoView programmatically
        LinearLayout infoView = new LinearLayout(ActivityInfoWindows.this);
        LinearLayout.LayoutParams infoViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        infoView.setOrientation(LinearLayout.HORIZONTAL);
        infoView.setLayoutParams(infoViewParams);

        ImageView infoImageView = new ImageView(ActivityInfoWindows.this);
        Drawable drawable = getResources().getDrawable(android.R.drawable.ic_dialog_map);
        infoImageView.setImageDrawable(drawable);
        infoView.addView(infoImageView);

        LinearLayout subInfoView = new LinearLayout(ActivityInfoWindows.this);
        LinearLayout.LayoutParams subInfoViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        subInfoView.setOrientation(LinearLayout.VERTICAL);
        subInfoView.setLayoutParams(subInfoViewParams);

        TextView subInfoLat = new TextView(ActivityInfoWindows.this);
        String text = "Lat: " + tplMarker.getPosition().latitude;
        subInfoLat.setText(text);
        TextView subInfoLnt = new TextView(ActivityInfoWindows.this);
        text = "Lnt: " + tplMarker.getPosition().longitude;
        subInfoLnt.setText(text);
        subInfoView.addView(subInfoLat);
        subInfoView.addView(subInfoLnt);
        infoView.addView(subInfoView);

        return infoView;
    }
}
