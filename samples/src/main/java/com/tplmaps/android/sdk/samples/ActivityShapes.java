package com.tplmaps.android.sdk.samples;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tplmaps.android.R;
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

import java.util.ArrayList;
import java.util.Objects;


public class ActivityShapes extends AppCompatActivity implements MapView.OnMapReadyCallback {

    private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapController mMapController;
    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes);

        // Getting MapView resource from layout
        mMapView = findViewById(R.id.map);
        // Calling MapView's onCreate() lifecycle method
        mMapView.onCreate(savedInstanceState);
        // Loading map Asynchronously vie registering call
        mMapView.loadMapAsync(this);
    }

    @Override
    public void onMapReady(final MapController mapController) {
        // TODO: Do your map tasks here

        // Setting map max tilt value
        mapController.setMaxTilt(85);
        mapController.setLngLat(new LngLat(73.093104, 33.730494));
        mapController.setZoomBy(15);
        mMapController = mapController;
        addMarkers();
        addPolyLines();
        addPolygons();
        addCircles();

        mMapController.setOnMapClickListener(lngLat -> {
            Log.i(TAG, "Called: onMapClick lnglat = " + lngLat.longitude + " , " + lngLat.latitude);
            //marker1.setPositionEased(lngLat, 1);
            //mMapController.addMarker(new MarkerOptions().position(lngLat));
        });

        mMapController.setOnPoiClickListener(place -> {
            Log.i(TAG, "Called: onPoiClick id = " + place.id);
            Log.i(TAG, "Called: onPoiClick tile = " + place.name);
            Log.i(TAG, "Called: onPoiClick lnglat = " + place.lngLat.longitude + " , " + place.lngLat.latitude);
            Log.i(TAG, " // /// ///// /// ");
        });

        mMapController.setOnMarkerClickListener(tplMarker -> Log.i(TAG, "Called: tplMarker tile = " + tplMarker.getTitle()));

        mMapController.setOnInfoWindowClickListener(tplMarker -> Log.i(TAG, "Called: tplMarker snippet = " + tplMarker.getDescription()));

        mMapController.setOnPolylineClickListener(tplPolyline -> Log.i(TAG, "Called: tplPolyline order = " + tplPolyline.getOrder()));

        mMapController.setOnPolygonClickListener(tplPolygon -> {
            Log.i(TAG, "Called: tplPolygon stroke width = " + tplPolygon.getOutlineWidth());
            //mapController.clearMap();
        });

        mMapController.setOnCircleClickListener(tplCircle -> {
            Log.i(TAG, "Called: tplCircle stroke radius = " + tplCircle.getRadius());
            Log.i(TAG, "Called: total markers = " + Objects.requireNonNull(mapController.getAllMarkers()).size());
            Log.i(TAG, "Called: total polylines = " + Objects.requireNonNull(mapController.getAllPolyLines()).size());
            Log.i(TAG, "Called: total polygons = " + Objects.requireNonNull(mapController.getAllPolygons()).size());
            Log.i(TAG, "Called: total circles = " + Objects.requireNonNull(mapController.getAllCircles()).size());
        });

        // Loading Default Map Controls
        // Settings map location permission and setting related configuration
        mapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent(getString(R.string.dialog_reason_title),
                        getString(R.string.dialog_reason_message));
        // Loading Default Map UI Controls
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
        mMapController.addPolygon(new PolygonOptions().addAll(lngLats2)
                .order(2).fillColor(Color.YELLOW).outlineColor(Color.GREEN).outlineWidth(10).clickable(true));

        mMapController.removePolygon(tplPolygon);
    }

    private void addCircles() {

        Circle tplCircle = mMapController.addCircle(new CircleOptions()
                .center(new LngLat(73.092159, 33.728945))
                .radius(30).fillColor(Color.CYAN)
                .order(1).clickable(true));

        mMapController.addCircle(new CircleOptions()
                .center(new LngLat(73.093104, 33.730494))
                .radius(150).fillColor(Color.BLUE)
                .order(1).clickable(true));

        tplCircle.remove();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mMapController != null)
            mMapController.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (mMapController != null)
            mMapController.onActivityResult(requestCode, resultCode, data);*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mMapView != null)
            mMapView.onStart();
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
    protected void onStop() {
        super.onStop();
        if (mMapView != null)
            mMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMapView != null)
            mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mMapView != null)
            mMapView.onLowMemory();
    }
}
