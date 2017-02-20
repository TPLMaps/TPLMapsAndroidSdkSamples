package com.tplmaps.android.sdk.samples.activities;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.BitmapDescriptorFactory;
import com.tplmaps3d.sdk.model.PointOfInterest;
import com.tplmaps3d.TPLCircle;
import com.tplmaps3d.TPLCircleOptions;
import com.tplmaps3d.TPLMarker;
import com.tplmaps3d.TPLMarkerOptions;
import com.tplmaps3d.TPLPolygon;
import com.tplmaps3d.TPLPolygonOptions;
import com.tplmaps3d.TPLPolyline;
import com.tplmaps3d.TPLPolylineOptions;

import java.util.ArrayList;


public class ActivityShapes extends AppCompatActivity implements MapView.OnMapReadyCallback {

    private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapView mMapView;
    private MapController mMapController;

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

        mapController.setLngLat(new LngLat(73.093104, 33.730494));
        mapController.setZoomBy(15);
        mMapController = mapController;
        //addMarkers();
        addPolyLines();
        addPolygons();
        addCircles();

        mMapController.setOnOnMapClickListener(new MapController.OnMapClickListener() {
            @Override
            public void onMapClick(LngLat lngLat) {
                Log.i(TAG, "Called: onMapClick lnglat = " + lngLat.latitude);
            }
        });
        mMapController.setOnPoiClickListener(new MapController.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest place) {
                Log.i(TAG, "Called: onPoiClick tile = " + place.id);
                Log.i(TAG, "Called: onPoiClick tile = " + place.name);
                Log.i(TAG, "Called: onPoiClick lnglat = " + place.lngLat.longitude + " , " + place.lngLat.latitude);
                Log.i(TAG, " // /// ///// /// ");
            }
        });

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

        mMapController.setOnPolylineClickListener(new MapController.OnPolylineClickListener() {
            @Override
            public void onPolylineClick(TPLPolyline tplPolyline) {
                Log.i(TAG, "Called: tplPolyline order = " + tplPolyline.getZIndex());
            }
        });

        mMapController.setOnPolygonClickListener(new MapController.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(TPLPolygon tplPolygon) {
                Log.i(TAG, "Called: tplPolygon stroke width = " + tplPolygon.getStrokeWidth());
            }
        });

        mMapController.setOnCircleClickListener(new MapController.OnCircleClickListener() {
            @Override
            public void onCircleClick(TPLCircle tplCircle) {
                Log.i(TAG, "Called: tplCircle stroke radius = " + tplCircle.getRadius());
            }
        });

    }

    private void addMarkers() {

        final TPLMarker marker1 = mMapController.addMarker(new TPLMarkerOptions()
                .position(new LngLat(73.093104, 33.730494))
                .title("marker1")
                .snippet("This is my spot!").infoWindowOffset(new Point(-150, 0))
                .rotation(50).flat(true)
                .size(new Point(150, 150))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_default)).visible(false));

        //marker1.setZIndex(1);
        marker1.setPosition(new LngLat(73.092159, 33.728945));
        marker1.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.ORANGE));
        marker1.setSize(new Point(200, 200));
        marker1.setFlat(false);
        marker1.setRotation(0);
        marker1.setInfoWindowOffset(new Point(0, 0));
        marker1.showInfoWindow();
        marker1.setVisible(true);
        marker1.showInfoWindow();

        /*marker1.setTitle("Titlum");
        marker1.setSnippet("aklsdfj");*/

        //marker1.hideInfoWindow();

        //marker1.setInfoWindowOffset(new Point(0, 0));
        /*
        marker1.hideInfoWindow();*/
        //marker1.setVisible(false);

        /*final TPLMarker marker2 = mMapController.addMarker(new TPLMarkerOptions()
                .position(new LngLat(73.092159, 33.728945))
                .title("marker2")
                .snippet("This is not my spot!").flat(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.YELLOW)));*/

    }

    private void addPolyLines() {

        final TPLPolyline polyline = mMapController.addPolyline(new TPLPolylineOptions()
                .add(new LngLat(73.094177, 33.729113),
                        new LngLat(73.090913, 33.727616)).color(Color.RED).width(10).zIndex(5));

        //polyline.setWidth(3);
        //polyline.setColor(Color.YELLOW);
        //polyline.setClickable(true);
        /*final TPLPolyline polyline = mMapController.addPolyline(new TPLPolylineOptions()
                .add(new LngLat(73.094177, 33.729113),
                        new LngLat(73.090913, 33.727616)).color(Color.TRANSPARENT));
        polyline.setWidth(5);*/
        //polyline.setColor(Color.TRANSPARENT);
        //polyline.setZIndex(5);
        //polyline.setColor(Color.parseColor("#E0FF00FF"));
        //polyline.setColor(Color.parseColor("#F00FF"));


        /*ArrayList<LngLat> lngLats = new ArrayList<>();
        lngLats.add(new LngLat(73.091229, 33.732780));
        lngLats.add(new LngLat(73.091669, 33.731334));
        polyline.setColor(Color.GREEN);
        polyline.setPoints(lngLats);*/

        /*ArrayList<LngLat> lngLats = new ArrayList<>();
        lngLats.add(new LngLat(73.092159, 33.728945));
        lngLats.add(new LngLat(73.092620, 33.727624));
        lngLats.add(new LngLat(73.091322, 33.726795));
        final TPLPolyline polyline = mMapView.addPolyline(new TPLPolylineOptions());
        polyline.setPoints(lngLats);*/
    }

    private void addPolygons() {

        ArrayList<LngLat> lngLats1 = new ArrayList<>();
        lngLats1.add(new LngLat(73.092159, 33.728945));
        lngLats1.add(new LngLat(73.092620, 33.727624));
        lngLats1.add(new LngLat(73.091322, 33.726795));
        lngLats1.add(new LngLat(73.092159, 33.728945));

        TPLPolygon tplPolygon = mMapController.addPolygon(new TPLPolygonOptions().addAll(lngLats1).zIndex(1).fillColor(Color.YELLOW).strokeColor(Color.GREEN).strokeWidth(4));
        tplPolygon.setClickable(true);
        ArrayList<LngLat> lngLats2 = new ArrayList<>();
        lngLats2.add(new LngLat(73.092159, 33.728945));
        lngLats2.add(new LngLat(73.093758, 33.728300));
        lngLats2.add(new LngLat(73.093190, 33.729430));
        lngLats2.add(new LngLat(73.092159, 33.728945));
        //tplPolygon.setPoints(lngLats2);

        //tplPolygon.setFillColor(Color.parseColor("#E0FF00FF"));
        //tplPolygon.setStrokeColor(Color.TRANSPARENT);
        /*tplPolygon.setStrokeColor(Color.BLUE);
        tplPolygon.setStrokeWidth(8);
        tplPolygon.setFillColor(Color.GREEN);*/

        /*tplPolygon.setStrokeColor(Color.YELLOW);
        tplPolygon.setStrokeWidth(5);*/
        //tplPolygon.setFillColor(Color.MAGENTA);
        /*tplPolygon.setStrokeColor(Color.YELLOW);
        tplPolygon.setStrokeWidth(4);
        tplPolygon.setZIndex(1);*/
        //tplPolygon.setFillColor(Color.TRANSPARENT);
    }

    private void addCircles() {

        //TPLCircle tplCircle = mMapView.addCircle(new TPLCircleOptions().center(new LngLat(73.092159, 33.728945)).radius(100).fillColor(Color.YELLOW).strokeColor(Color.CYAN).strokeWidth(5).zIndex(0));
        TPLCircle tplCircle = mMapController.addCircle(new TPLCircleOptions().center(new LngLat(73.092159, 33.728945)).radius(100).fillColor(Color.CYAN).zIndex(0).clickable(true));
        /*tplCircle.setStrokeColor(Color.CYAN);
        tplCircle.setFillColor(Color.YELLOW);
        tplCircle.setZIndex(2);*/

        //tplCircle.setFillColor(Color.parseColor("#50FF00FF"));
        //tplCircle.setStrokeColor(Color.TRANSPARENT);

    }
}
