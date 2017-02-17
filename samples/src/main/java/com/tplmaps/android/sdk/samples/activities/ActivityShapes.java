package com.tplmaps.android.sdk.samples.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.LabelPickResult;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.sdk.model.TPLCircle;
import com.tplmaps3d.sdk.model.TPLCircleOptions;
import com.tplmaps3d.sdk.model.TPLMarker;
import com.tplmaps3d.sdk.model.TPLMarkerOptions;
import com.tplmaps3d.sdk.model.TPLPolygon;
import com.tplmaps3d.sdk.model.TPLPolygonOptions;
import com.tplmaps3d.sdk.model.TPLPolyline;
import com.tplmaps3d.sdk.model.TPLPolylineOptions;

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
        addMarkers();
        //addPolyLines();
        //addPolygons();
        //addCircles();

        mMapController.setLabelPickListener(new MapController.LabelPickListener() {
            @Override
            public void onLabelPick(LabelPickResult labelPickResult, float positionX, float positionY) {
                if(labelPickResult != null) {
                    Log.i(TAG, "Called: lable = " + labelPickResult.toString());
                }
            }
        });

        /*mMapController.setFeaturePickListener(new MapController.FeaturePickListener() {
            @Override
            public void onFeaturePick(Map<String, String> properties, float positionX, float positionY) {
                if(properties != null)
                    Log.i(TAG, "Called: lable = " + properties.toString());
            }
        });*/
    }

    private void addMarkers() {

        final TPLMarker marker1 = mMapController.addMarker(new TPLMarkerOptions()
                .position(new LngLat(73.093104, 33.730494))
                .title("marker1")
                .snippet("This is my spot!").flat(false).visible(false).zIndex(0).rotation(45));

        marker1.setVisible(true);
        //marker1.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.ORANGE));
        marker1.setPosition(new LngLat(73.092159, 33.728945));
        //marker1.setSize(new Point(300,300));
        //marker1.setIcon(BitmapDescriptorFactory.fromAsset("images/pinassets.png"));
        //marker1.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_default));

        //marker1.setZIndex(0);
        /*marker1.setSize(new Point(36,36));
        marker1.setFlat(true);
        marker1.setRotation(60);*/

        //marker1.setVisible(true);
        //marker1.setZIndex(2);

        /*final TPLMarker marker2 = mMapView.addMarker(new TPLMarkerOptions()
                .position(new LngLat(73.093104, 33.730494))
                .title("marker2")
                .snippet("This is my spot!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.YELLOW)));

        marker2.setZIndex(1);*/

        /*final TPLMarker marker2 = mMapController.addMarker(new TPLMarkerOptions()
                .position(new LngLat(73.092159, 33.728945))
                .title("marker2")
                .snippet("Spotum Here"));

        marker2.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_default));
        marker2.setZIndex(1);*/

        //marker2.setVisible(true);
        //marker2.setVisible(false);

        /*mMapView.setOnMarkerClickListener(new MapView.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(TPLMarker tplMarker) {
                if(marker1.equals(tplMarker)) {
                    Log.i(TAG, "Called: id = " + tplMarker.getId());
                    Log.i(TAG, "Called: title = " + tplMarker.getTitle());
                    Log.i(TAG, "Called: getPosition = " + tplMarker.getPosition().latitude + " , " + tplMarker.getPosition().longitude);
                    Log.i(TAG, "Called: getRotation = " + tplMarker.getRotation());

                    //marker2.setVisible(!marker2.isVisible());
                }
                else if(marker2.equals(tplMarker)) {
                    Log.i(TAG, "Called: id = " + tplMarker.getId());
                    Log.i(TAG, "Called: Snippet = " + tplMarker.getSnippet());
                }
                return false;
            }
        });*/

        /*String pointStyle2 = "{ style: 'points', interactive: true, color: 'white', size: [50px, 50px], order: 2000, collide: false }";
        Marker marker2 = mMapController.addMarker();
        marker2.setStyling(pointStyle2);
        marker2.setDrawable(R.mipmap.ic_launcher);
        marker2.setPoint(new LngLat(73.092159, 33.728945));*/

        // Tangram marker
        /*String pointStyle = "{ style: 'points', interactive: true, color: 'red', visible: true, size: [50px, 50px], order: 1000, collide: false }";
        Marker marker = mMapController.addMarker();
        marker.setStyling(pointStyle);
        //marker.setDrawable(R.drawable.marker_default);
        marker.setPoint(new LngLat(73.092159, 33.728945));

        String pointStyle3 = "{ style: 'points', interactive: true, color: 'green', visible: true, size: [50px, 50px], order: 1000, collide: false }";
        marker.setStyling(pointStyle3);
        marker.setPoint(new LngLat(73.092162, 33.728950));*/

    }

    private void addPolyLines() {

        final TPLPolyline polyline = mMapController.addPolyline(new TPLPolylineOptions()
                .add(new LngLat(73.094177, 33.729113),
                        new LngLat(73.090913, 33.727616)).color(Color.RED).width(10).zIndex(5));

        /*final TPLPolyline polyline = mMapView.addPolyline(new TPLPolylineOptions()
                .add(new LngLat(73.094177, 33.729113),
                        new LngLat(73.090913, 33.727616)));
        polyline.setWidth(5);
        polyline.setColor(Color.RED);
        polyline.setZIndex(0);*/

        /*ArrayList<LngLat> lngLats = new ArrayList<>();
        lngLats.add(new LngLat(73.091229, 33.732780));
        lngLats.add(new LngLat(73.091669, 33.731334));
        polyline.setPoints(lngLats);*/

        /*ArrayList<LngLat> lngLats = new ArrayList<>();
        lngLats.add(new LngLat(73.092159, 33.728945));
        lngLats.add(new LngLat(73.092620, 33.727624));
        lngLats.add(new LngLat(73.091322, 33.726795));
        final TPLPolyline polyline = mMapView.addPolyline(new TPLPolylineOptions());
        polyline.setPoints(lngLats);*/

        //String pointStyle = "{ style: 'lines', color: '#06a6d4', width: 5px, order: 2000, outline }";
        /*String pointStyle = "{ style: 'lines', color: '#06a6d4', width: 5px, order: 2000, outline:color: '#ff0000', outline:width: 1px }";
        Marker marker = mMapController.addMarker();
        marker.setStyling(pointStyle);
        ArrayList<LngLat> lngLats1 = new ArrayList<>();
        lngLats1.add(new LngLat(73.092159, 33.728945));
        lngLats1.add(new LngLat(73.092620, 33.727624));
        lngLats1.add(new LngLat(73.091322, 33.726795));
        lngLats1.add(new LngLat(73.089788, 33.727928));
        HashMap<String, String> properties = new HashMap<>();
        marker.setPolyline(new Polyline(lngLats1, properties));*/
    }

    private void addPolygons() {

        ArrayList<LngLat> lngLats1 = new ArrayList<>();
        lngLats1.add(new LngLat(73.092159, 33.728945));
        lngLats1.add(new LngLat(73.092620, 33.727624));
        lngLats1.add(new LngLat(73.091322, 33.726795));
        lngLats1.add(new LngLat(73.092159, 33.728945));

        TPLPolygon tplPolygon = mMapController.addPolygon(new TPLPolygonOptions().addAll(lngLats1).zIndex(1).fillColor(Color.YELLOW).strokeColor(Color.CYAN).strokeWidth(4));

        ArrayList<LngLat> lngLats2 = new ArrayList<>();
        lngLats2.add(new LngLat(73.092159, 33.728945));
        lngLats2.add(new LngLat(73.093758, 33.728300));
        lngLats2.add(new LngLat(73.093190, 33.729430));
        lngLats2.add(new LngLat(73.092159, 33.728945));
        tplPolygon.setPoints(lngLats2);

        tplPolygon.setStrokeColor(Color.BLUE);
        tplPolygon.setStrokeWidth(8);
        tplPolygon.setFillColor(Color.GREEN);

        /*tplPolygon.setStrokeColor(Color.YELLOW);
        tplPolygon.setStrokeWidth(5);*/
        //tplPolygon.setFillColor(Color.MAGENTA);
        /*tplPolygon.setStrokeColor(Color.YELLOW);
        tplPolygon.setStrokeWidth(4);
        tplPolygon.setZIndex(1);*/
        //tplPolygon.setFillColor(Color.TRANSPARENT);

        /*String pointStyle = "{ style: 'polygons', color: '#06a6d4', width: 5px, order: 2000 }";
        Marker marker = mMapController.addMarker();
        marker.setStyling(pointStyle);
        List<List<LngLat>> lngLats = new ArrayList<>();
        lngLats.add(lngLats1);
        HashMap<String, String> properties = new HashMap<>();
        Polygon polygon = new Polygon(lngLats, properties);
        marker.setPolygon(polygon);*/
    }

    private void addCircles() {

        //TPLCircle tplCircle = mMapView.addCircle(new TPLCircleOptions().center(new LngLat(73.092159, 33.728945)).radius(100).fillColor(Color.YELLOW).strokeColor(Color.CYAN).strokeWidth(5).zIndex(0));
        TPLCircle tplCircle = mMapController.addCircle(new TPLCircleOptions().center(new LngLat(73.092159, 33.728945)).radius(100).fillColor(Color.CYAN).zIndex(0));
        /*tplCircle.setStrokeColor(Color.CYAN);
        tplCircle.setFillColor(Color.YELLOW);
        tplCircle.setZIndex(2);*/
        /*LngLat lngLat = new LngLat(73.092159, 33.728945);
        ArrayList<LngLat> lngLats1 = calculateCircle(lngLat, (float) 0.0001);

        String pointStyle = "{ style: 'polygons', color: '#06a6d4', width: 5px, order: 2000 }";
        Marker marker = mMapController.addMarker();
        marker.setStyling(pointStyle);

        List<List<LngLat>> lngLats = new ArrayList<>();
        lngLats.add(lngLats1);

        HashMap<String, String> properties = new HashMap<>();
        properties.put("color", "#06a6d4");
        Polygon polygon = new Polygon(lngLats, properties);
        marker.setPolygon(polygon);*/
    }
}
