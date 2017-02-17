package com.tplmaps.android.sdk.samples.activities;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.sdk.model.BitmapDescriptorFactory;
import com.tplmaps3d.sdk.model.TPLCircle;
import com.tplmaps3d.sdk.model.TPLCircleOptions;
import com.tplmaps3d.sdk.model.TPLMarker;
import com.tplmaps3d.sdk.model.TPLMarkerOptions;
import com.tplmaps3d.sdk.model.TPLPolygon;
import com.tplmaps3d.sdk.model.TPLPolygonOptions;
import com.tplmaps3d.sdk.model.TPLPolyline;
import com.tplmaps3d.sdk.model.TPLPolylineOptions;

import java.util.ArrayList;


public class ActivityInfoWindows extends AppCompatActivity implements MapView.OnMapReadyCallback {

    private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapView mMapView;
    private MapController mMapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_windows);
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

        mapController.setLngLat(new LngLat(73.093104, 33.730494));
        mapController.setZoomBy(15);
        mMapController = mapController;

        normalInfoWindows();
        //customInfoWindows();
        //customInfoWindowsMultipleViews();

        mMapController.setOnMarkerClickListener(new MapController.OnMarkerClickListener() {
            @Override
            public void onMarkerClick(TPLMarker tplMarker) {
                Log.i(TAG, "Called: tplMarker tile = " + tplMarker.getTitle());
            }
        });

        mMapController.setOnInfoWindowClickListener(new MapController.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(TPLMarker tplMarker) {
                Log.i(TAG, "Called: tplMarker snippet = " + tplMarker.getSnippet());
            }
        });

    }

    private void normalInfoWindows() {

        final TPLMarker marker1 = mMapController.addMarker(new TPLMarkerOptions()
                .position(new LngLat(73.093104, 33.730494))
                .title("marker1")
                .snippet("This is my spot!").flat(false).zIndex(1));

        marker1.showInfoWindow();
        /*marker1.setPosition(new LngLat(73.090947, 33.730283));
        marker1.setSize(new Point(102, 102));
        marker1.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_default));
        marker1.setInfoWindowOffset(new Point(-150, 0));
        marker1.hideInfoWindow();
        marker1.setVisible(false);*/

        final TPLMarker marker2 = mMapController.addMarker(new TPLMarkerOptions()
                .position(new LngLat(73.092159, 33.728945))
                .title("marker2")
                .snippet("This is not my spot!").flat(false).zIndex(0)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.YELLOW)));
        marker2.showInfoWindow();

        //marker2.setTitle("Titlum");
        //marker2.setSnippet("TestSnip");
    }

    private void customInfoWindows() {
        normalInfoWindows();
        // Set custom info view to all info windows
        mMapController.setCustomInfoWindow(new MapController.CustomInfoWindow() {
            @Override
            public View onInfoWindow(TPLMarker tplMarker) {
                return null;
            }

            @Override
            public View onInfoWindowContent(TPLMarker tplMarker) {
                return prepareInfoView(tplMarker);
            }
        });
    }

    TPLMarker marker3, marker4;
    private void customInfoWindowsMultipleViews() {

        // Set different custom views to different info windows
        normalInfoWindows();
        marker3 = mMapController.addMarker(new TPLMarkerOptions()
                .position(new LngLat(73.096719, 33.728160)).zIndex(0)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.GREEN)));

        marker3.showInfoWindow();
        /*marker3.setPosition(new LngLat(73.092159, 33.728945));
        marker3.setSize(new Point(102, 102));
        marker3.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_default));
        marker3.setInfoWindowOffset(new Point(-150, 0));
        marker3.hideInfoWindow();
        marker3.setVisible(false);*/

        marker4 = mMapController.addMarker(new TPLMarkerOptions()
                .position(new LngLat(73.090947, 33.730283)).zIndex(0)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.RED)));

        marker4.showInfoWindow();
        marker4.setInfoWindowOffset(new Point(-150, -20));

        mMapController.setCustomInfoWindow(new MapController.CustomInfoWindow() {
            @Override
            public View onInfoWindow(TPLMarker tplMarker) {
                if(tplMarker == marker3)
                    return prepareInfoView(tplMarker);
                return null;
            }

            @Override
            public View onInfoWindowContent(TPLMarker tplMarker) {
                if(tplMarker == marker4)
                    return prepareInfoView(tplMarker);
                return null;
            }
        });
    }


    private View prepareInfoView(TPLMarker tplMarker) {
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
        subInfoLat.setText("Lat: " + tplMarker.getPosition().latitude);
        TextView subInfoLnt = new TextView(ActivityInfoWindows.this);
        subInfoLnt.setText("Lnt: " + tplMarker.getPosition().longitude);
        subInfoView.addView(subInfoLat);
        subInfoView.addView(subInfoLnt);
        infoView.addView(subInfoView);

        return infoView;
    }
}
