package com.tplmaps.android.sdk.samples.activities;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.Circle;
import com.tplmaps3d.CircleOptions;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.IconFactory;
import com.tplmaps3d.Marker;
import com.tplmaps3d.MarkerOptions;
import com.tplmaps3d.Polygon;
import com.tplmaps3d.PolygonOptions;
import com.tplmaps3d.Polyline;
import com.tplmaps3d.PolylineOptions;
import com.tplmaps3d.sdk.model.IconSize;
import com.tplmaps3d.sdk.model.PointOfInterest;

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
        //addPolygons();
        //addCircles();

        mMapController.setOnMapClickListener(new MapController.OnMapClickListener() {
            @Override
            public void onMapClick(LngLat lngLat) {
                Log.i(TAG, "Called: onMapClick lnglat = " + lngLat.longitude + " , " + lngLat.latitude);
                //mMapController.addMarker(new MarkerOptions().position(lngLat));
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
            public void onMarkerClick(Marker tplMarker) {
                Log.i(TAG, "Called: tplMarker tile = " + tplMarker.getTitle());
            }
        });

        mMapController.setOnInfoWindowClickListener(new MapController.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker tplMarker) {
                Log.i(TAG, "Called: tplMarker snippet = " + tplMarker.getDescription());
            }
        });

        mMapController.setOnPolylineClickListener(new MapController.OnPolylineClickListener() {
            @Override
            public void onPolylineClick(Polyline tplPolyline) {
                Log.i(TAG, "Called: tplPolyline order = " + tplPolyline.getOrder());
            }
        });

        mMapController.setOnPolygonClickListener(new MapController.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon tplPolygon) {
                Log.i(TAG, "Called: tplPolygon stroke width = " + tplPolygon.getStrokeWidth());
            }
        });

        mMapController.setOnCircleClickListener(new MapController.OnCircleClickListener() {
            @Override
            public void onCircleClick(Circle tplCircle) {
                Log.i(TAG, "Called: tplCircle stroke radius = " + tplCircle.getRadius());
            }
        });

    }

    private void addMarkers() {

        final Marker marker1 = mMapController.addMarker(new MarkerOptions()
                .position(new LngLat(73.093104, 33.730494))
                .title("marker1")
                .description("This is my spot!").infoWindowOffset(new android.graphics.Point(-150, 0))
                .rotation(50).flat(true)
                .icon(IconFactory.fromResource(R.drawable.ic_pin_drop, new IconSize(150, 150))).visible(false).order(1));

        //marker1.setZIndex(1);
        marker1.setPosition(new LngLat(73.092159, 33.728945));
        //marker1.setIcon(IconFactory.defaultMarker(IconFactory.ORANGE));
        marker1.setFlat(false);
        marker1.setRotation(0);
        marker1.setInfoWindowOffset(new android.graphics.Point(0, 0));
        marker1.showInfoWindow();
        marker1.setVisible(true);
        marker1.showInfoWindow();

        marker1.setTitle("Titlum");
        marker1.setDescription("aklsdfj");

        //marker1.hideInfoWindow();

        //marker1.setInfoWindowOffset(new Point(0, 0));
        /*
        marker1.hideInfoWindow();*/
        //marker1.setVisible(false);

        /*final Marker marker2 = mMapController.addMarker(new MarkerOptions()
                .position(new LngLat(73.092159, 33.728945))
                .title("marker2")
                .description("This is not my spot!").flat(false).order(0));

        //marker2.setDescription("ksd lfjaksdl fjklad fjkads fklads fjkalsd fjklad fjkalds fjaksd fjaksdl fjakldf jakld fjkald fjkladsf jaklsd fjkals");
        marker2.showInfoWindow();
        marker2.setTitle("ksd lfjaksdl fjklad fjkads fklads fjkalsd fjklad fjkalds fjaksd fjaksdl fjakldf jakld fjkald fjkladsf jaklsd fjkals");*/


        //marker2.setIcon(IconFactory.fromResource(R.drawable.ic_pin_drop));

    }

    private void addPolyLines() {

        /*final Polyline polyline = mMapController.addPolyline(new PolylineOptions()
                .add(new LngLat(73.094177, 33.729113),
                        new LngLat(73.090913, 33.727616)).color(Color.RED).width(10).order(5));*/

        final Polyline polyline = mMapController.addPolyline(new PolylineOptions()
                .add(new LngLat(71.094177, 33.729113),
                        new LngLat(73.090913, 33.727616)).color(Color.RED).width(5).order(5));

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
        final Polyline polyline = mMapController.addPolyline(new PolylineOptions());
        polyline.setPoints(lngLats);*/
    }

    private void addPolygons() {

        ArrayList<LngLat> lngLats1 = new ArrayList<>();
        lngLats1.add(new LngLat(73.092159, 33.728945));
        lngLats1.add(new LngLat(73.092620, 33.727624));
        lngLats1.add(new LngLat(73.091322, 33.726795));
        lngLats1.add(new LngLat(73.092159, 33.728945));

        // full map points
        /*lngLats1.add(new LngLat(72.092649, 74.595951));
        lngLats1.add(new LngLat(-14.040163, 51.522430));
        lngLats1.add(new LngLat(78.596555, 6.861009));
        lngLats1.add(new LngLat(145.217649, 51.957821));*/

        Polygon tplPolygon = mMapController.addPolygon(new PolygonOptions().addAll(lngLats1).order(2).fillColor(Color.YELLOW).strokeColor(Color.GREEN).strokeWidth(10));
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
        tplPolygon.setOrder(1); */
        //tplPolygon.setFillColor(Color.TRANSPARENT);
    }

    private void addCircles() {

        //TPLCircle tplCircle = mMapView.addCircle(new TPLCircleOptions().center(new LngLat(73.092159, 33.728945)).radius(100).fillColor(Color.YELLOW).strokeColor(Color.CYAN).strokeWidth(5).zIndex(0));
        Circle tplCircle = mMapController.addCircle(new CircleOptions().center(null).radius(100).fillColor(Color.CYAN).order(1).clickable(true));
        /*tplCircle.setStrokeColor(Color.CYAN);
        tplCircle.setFillColor(Color.YELLOW);
        tplCircle.setOrder(2);*/

        //tplCircle.setFillColor(Color.parseColor("#50FF00FF"));
        //tplCircle.setStrokeColor(Color.TRANSPARENT);

    }
}
