package com.tplmaps.android.sdk.samples.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.Circle;
import com.tplmaps3d.CircleOptions;
import com.tplmaps3d.IconFactory;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.Marker;
import com.tplmaps3d.MarkerOptions;
import com.tplmaps3d.Polygon;
import com.tplmaps3d.PolygonOptions;
import com.tplmaps3d.Polyline;
import com.tplmaps3d.PolylineOptions;
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
    protected void onResume() {
        super.onResume();

        if (mMapView != null)
            mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mMapView != null)
            mMapView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mMapView != null)
            mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mMapView != null)
            mMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mMapView != null)
            mMapView.onDestroy();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();

        if (mMapView != null)
            mMapView.onLowMemory();
    }

    @Override
    public void onMapReady(final MapController mapController) {

        mapController.setLngLat(new LngLat(73.093104, 33.730494));
        mapController.setZoomBy(15);
        mMapController = mapController;
        addMarkers();
        addPolyLines();
        addPolygons();
        addCircles();

        mMapController.setOnMapClickListener(new MapController.OnMapClickListener() {
            @Override
            public void onMapClick(LngLat lngLat) {
                Log.i(TAG, "Called: onMapClick lnglat = " + lngLat.longitude + " , " + lngLat.latitude);
                //marker1.setPositionEased(lngLat, 1);
                //mMapController.addMarker(new MarkerOptions().position(lngLat));
            }
        });

        mMapController.setOnPoiClickListener(new MapController.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest place) {
                Log.i(TAG, "Called: onPoiClick id = " + place.id);
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
                Log.i(TAG, "Called: tplPolygon stroke width = " + tplPolygon.getOutlineWidth());
                //mapController.clearMap();
            }
        });

        mMapController.setOnCircleClickListener(new MapController.OnCircleClickListener() {
            @Override
            public void onCircleClick(Circle tplCircle) {
                Log.i(TAG, "Called: tplCircle stroke radius = " + tplCircle.getRadius());
                Log.i(TAG, "Called: total markers = " + mapController.getAllMarkers().size());
                Log.i(TAG, "Called: total polylines = " + mapController.getAllPolyLines().size());
                Log.i(TAG, "Called: total polygons = " + mapController.getAllPolygons().size());
                Log.i(TAG, "Called: total circles = " + mapController.getAllCircles().size());
            }
        });

        // Loading Default Map Controls
        mapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent("Permission Required",
                        "Location permission is required for the application to show your" +
                                " precise and accurate location on map");
        mapController.getUiSettings().showZoomControls(true);
        mapController.getUiSettings().showMyLocationButton(true);

    }

    Marker marker1;
    private void addMarkers() {

        LngLat latLngISB = new LngLat(73.093104, 33.730494);
        marker1 = mMapController.addMarker(new MarkerOptions()
                .position(latLngISB)
                .title("marker1")
                .description("This is my spot!").infoWindowOffset(new android.graphics.Point(-150, 0))
                .rotation(50).flat(true)
                .icon(IconFactory.fromResource(R.drawable.ic_pin_drop)).visible(false).order(1));

        marker1.setFlat(false);
        marker1.setRotation(0);
        marker1.setInfoWindowOffset(new android.graphics.Point(0, 0));
        marker1.showInfoWindow();
        marker1.setVisible(true);
        marker1.showInfoWindow();

        marker1.setProperties(latLngISB);

        marker1.setTitle("Titlum");
        marker1.setDescription("aklsdfj");

        final Marker marker2 = mMapController.addMarker(new MarkerOptions()
                .position(new LngLat(73.092159, 33.728945))
                .title("marker2")
                .description("This is not my spot!").flat(false).order(0));

        marker2.showInfoWindow();
        marker2.setTitle("ksd lfjaksdl fjklad fjkads fklads fjkalsd fjklad fjkalds fjaksd fjaksdl fjakldf jakld fjkald fjkladsf jaklsd fjkals");

        mMapController.removeMarker(marker2);

    }

    private void addPolyLines() {

        final Polyline polyline1 = mMapController.addPolyline(new PolylineOptions()
                .add(new LngLat(73.094177, 33.729113), new LngLat(73.090913, 33.727616))
                .add(new LngLat(73.090913, 33.727616), new LngLat(73.096118, 33.728488))
                .add(new LngLat(73.096118, 33.728488), new LngLat(73.106513, 33.714936))
                .color(Color.WHITE).width(10).order(5).outlineWidth(2)
                .outlineColor(Color.BLUE).clickable(true));
        polyline1.setOutlineWidth(5);
        polyline1.setOutlineColor(Color.parseColor("#FF69B4"));

        final Polyline polyline2 = mMapController.addPolyline(new PolylineOptions()
                .add(new LngLat(71.094177, 33.729113),
                        new LngLat(73.090913, 33.727616)).color(Color.RED).width(5).order(5));
        polyline2.remove();
    }

    private void addPolygons() {

        ArrayList<LngLat> lngLats1 = new ArrayList<>();
        lngLats1.add(new LngLat(73.092159, 33.728945));
        lngLats1.add(new LngLat(73.092620, 33.727624));
        lngLats1.add(new LngLat(73.091322, 33.726795));
        lngLats1.add(new LngLat(73.092159, 33.728945));
        Polygon tplPolygon = mMapController.addPolygon(new PolygonOptions().addAll(lngLats1).order(2)
                .fillColor(Color.BLACK).outlineColor(Color.GRAY).outlineWidth(10));
        tplPolygon.setClickable(true);

        ArrayList<LngLat> lngLats2 = new ArrayList<>();
        lngLats2.add(new LngLat(73.092159, 33.728945));
        lngLats2.add(new LngLat(73.093758, 33.728300));
        lngLats2.add(new LngLat(73.093190, 33.729430));
        lngLats2.add(new LngLat(73.092159, 33.728945));
        Polygon tplPolygon1 = mMapController.addPolygon(new PolygonOptions().addAll(lngLats2)
                .order(2).fillColor(Color.YELLOW).outlineColor(Color.GREEN).outlineWidth(10).clickable(true));

        mMapController.removePolygon(tplPolygon);
    }

    private void addCircles() {

        Circle tplCircle = mMapController.addCircle(new CircleOptions()
                .center(new LngLat(73.092159, 33.728945))
                .radius(30).fillColor(Color.CYAN)
                .order(1).clickable(true));
        /*tplCircle.setStrokeColor(Color.CYAN);
        tplCircle.setFillColor(Color.YELLOW);
        tplCircle.setOrder(2);*/

        //tplCircle.setFillColor(Color.parseColor("#50FF00FF"));
        //tplCircle.setStrokeColor(Color.TRANSPARENT);

        Circle tplCircle1 = mMapController.addCircle(new CircleOptions()
                .center(new LngLat(73.093104, 33.730494))
                .radius(150).fillColor(Color.BLUE)
                .order(1).clickable(true));

        tplCircle.remove();
        //tplCircle1.remove();
        //mMapController.removeCircle(tplCircle);
        //mMapController.removeAllCircles();
    }
}
