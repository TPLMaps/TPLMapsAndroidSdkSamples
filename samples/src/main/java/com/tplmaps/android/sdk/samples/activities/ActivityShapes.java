package com.tplmaps.android.sdk.samples.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
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

import static com.tplmaps.android.R.id.map;

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

        mapController.setPosition(new LngLat(73.093104, 33.730494));
        mapController.setZoom(15);

        addMarkers();
        //addPolyLines();
        //addPolygons();
        //addCircles();
    }

    private void addMarkers() {

        final TPLMarker marker1 = mMapView.addMarker(new TPLMarkerOptions()
                .position(new LngLat(73.093104, 33.730494))
                .title("marker1")
                .snippet("This is my spot!"));

        final TPLMarker marker2 = mMapView.addMarker(new TPLMarkerOptions()
                .position(new LngLat(73.092159, 33.728945))
                .title("marker2")
                .snippet("Spotum Here"));

        mMapView.setOnMarkerClickListener(new MapView.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(TPLMarker tplMarker) {
                if(marker1.equals(tplMarker)) {
                    Log.i(TAG, "Called: id = " + tplMarker.getId());
                    Log.i(TAG, "Called: title = " + tplMarker.getTitle());
                }
                else if(marker2.equals(tplMarker)) {
                    Log.i(TAG, "Called: id = " + tplMarker.getId());
                    Log.i(TAG, "Called: Snippet = " + tplMarker.getSnippet());
                }
                return false;
            }
        });


        // Tangram marker
        /*String pointStyle = "{ style: 'points', interactive: true, color: 'white', size: [50px, 50px], order: 2000, collide: false }";
        Marker marker = mapController.addMarker();
        marker.setStyling(pointStyle);
        marker.setDrawable(R.drawable.marker_default);
        marker.setPoint(new LngLat(73.092159, 33.728945));
        marker.setRotation(Math.toRadians(50));*/
    }

    private void addPolyLines() {

        /*final TPLPolyline polyline = mMapView.addPolyline(new TPLPolylineOptions()
                .add(new LngLat(73.094177, 33.729113),
                        new LngLat(73.090913, 33.727616)).width(5).color(Color.RED).visible(true).zIndex(0));*/

        final TPLPolyline polyline = mMapView.addPolyline(new TPLPolylineOptions()
                .add(new LngLat(73.094177, 33.729113),
                        new LngLat(73.090913, 33.727616)));
        polyline.setWidth(5);
        polyline.setColor(Color.RED);
        polyline.setZIndex(0);

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

        TPLPolygon tplPolygon = mMapView.addPolygon(new TPLPolygonOptions().addAll(lngLats1).fillColor(Color.GREEN).zIndex(1));
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
        TPLCircle tplCircle = mMapView.addCircle(new TPLCircleOptions().center(new LngLat(73.092159, 33.728945)).radius(100));
        tplCircle.setStrokeColor(Color.CYAN);
        tplCircle.setStrokeWidth(5);
        tplCircle.setFillColor(Color.YELLOW);
        tplCircle.setZIndex(2);
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
