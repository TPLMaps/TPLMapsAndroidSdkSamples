package com.tplmaps.android.sdk.samples;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.tpl.maps.sdk.routing.StringUtils;
import com.tpl.maps.sdk.routing.TPLRouteConfig;
import com.tpl.maps.sdk.routing.TPLRouteManager;
import com.tpl.maps.sdk.routing.structures.Place;
import com.tpl.maps.sdk.routing.structures.TPLRoute;
import com.tpl.maps.sdk.utils.boundingBox.RouteUtils;
import com.tplmaps.android.R;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ActivityRouting extends AppCompatActivity implements MapView.OnMapReadyCallback {

    private TPLRouteManager mRouteManager;
    //private BottomSheetBehavior bottomSheetBehavior;
    private MapView mMapView;
    private MapController mMapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing);

        // Initiating TPLRouteManager instance
        mRouteManager = new TPLRouteManager();
        mRouteManager.onCreate(this);

        // Initiating bottom sheet and setting its default state
        initBottomSheet();

        findViewById(R.id.calculate).setOnClickListener(view -> {
            String source = ((EditText) findViewById(R.id.source)).getText().toString();
            String destination = ((EditText) findViewById(R.id.destination)).getText().toString();
            calculateRoute(mMapController, source, destination);
        });

        // Filled field with sample location values
        String strSrc = "33.711556,73.058382";
        ((EditText) findViewById(R.id.source)).setText(strSrc);
        String strDest = "33.522695,73.094223";
        ((EditText) findViewById(R.id.destination)).setText(strDest);

        // Getting MapView resource from layout
        mMapView = findViewById(R.id.map);
        // Calling MapView's onCreate() lifecycle method
        mMapView.onCreate(savedInstanceState);
        // Loading map Asynchronously vie registering call
        mMapView.loadMapAsync(this);
    }

    private void initBottomSheet() {
        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));
        findViewById(R.id.header_arrow).setOnClickListener(view -> {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                updateUIForCollapsedState();
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                updateUIForExpandedState();
            }
        });

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    ((ImageView) findViewById(R.id.header_arrow)).setImageResource(R.drawable.ic_arrow_drop_down);
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    ((ImageView) findViewById(R.id.header_arrow)).setImageResource(R.drawable.ic_arrow_drop_up);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        updateUIForExpandedState();
    }

    private void updateUIForCollapsedState() {
        ((ImageView) findViewById(R.id.header_arrow)).setImageResource(R.drawable.ic_arrow_drop_up);
    }

    private void updateUIForExpandedState() {
        ((ImageView) findViewById(R.id.header_arrow)).setImageResource(R.drawable.ic_arrow_drop_down);
    }


    @Override
    public void onMapReady(MapController mapController) {
        mMapController = mapController;
        // TODO: Do you map tasks here

        // Setting map max tilt value
        mapController.setMaxTilt(85);
        // Settings map location permission and setting related configuration
        mapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent(getString(R.string.dialog_reason_title),
                        getString(R.string.dialog_reason_message));
        // Loading Default Map UI Controls
        mapController.getUiSettings().showZoomControls(true);
        mapController.getUiSettings().showMyLocationButton(true);

        findViewById(R.id.calculate).setOnClickListener(view -> {
            String source = ((EditText) findViewById(R.id.source)).getText().toString();
            String destination = ((EditText) findViewById(R.id.destination)).getText().toString();
            calculateRoute(mapController, source, destination);
        });

        findViewById(R.id.clear).setOnClickListener(view -> {
            ((EditText) findViewById(R.id.source)).getText().clear();
            ((EditText) findViewById(R.id.destination)).getText().clear();
            mapController.clearMap();
        });
    }

    public void calculateRoute(MapController mapController, String strSource, String strDestination) {

        if (!(StringUtils.isValidString(strSource) && StringUtils.isValidString(strDestination))) {
            Snackbar.make(findViewById(R.id.root), getString(R.string.locations_are_invalid),
                    Snackbar.LENGTH_LONG).show();
            return;
        }

        String[] arrSource = strSource.split(",");
        String[] arrDestination = strDestination.split(",");

        // Initializing/preparing source and destination locations array
        ArrayList<Place> locations = new ArrayList<>();
        // Source location
        Place source = new Place();
        source.setName("Source");
        source.setX(Double.parseDouble(arrSource[1]));
        source.setY(Double.parseDouble(arrSource[0]));
        // Destination Location
        Place destination = new Place();
        destination.setName("Destination");
        destination.setX(Double.parseDouble(arrDestination[1]));
        destination.setY(Double.parseDouble(arrDestination[0]));

        locations.add(source);
        locations.add(destination);

        // Building route config
        TPLRouteConfig config = new TPLRouteConfig.Builder()
                .reRoute(false)
                .endPoints(locations)
                .heading(90)
                .build();

        List<com.tpl.maps.sdk.routing.LngLat> listNodes = new ArrayList<>();
        // Calling for calculating routes for source and destination locations with config
        mRouteManager.calculate(this, config, (endPoints, routes) -> {
            if (mapController == null)
                return;

            for (int i = 0; i < routes.size(); i++) {
                // Drawing/Rendering route on map
                TPLRoute route = routes.get(i);
                LngLat[] lnglats = new LngLat[route.getRouteNodes().size()];
                for (int j = 0; j < route.getRouteNodes().size(); j++) {
                    com.tpl.maps.sdk.routing.LngLat lngLat = route.getRouteNodes().get(j);
                    lnglats[j] = new LngLat(lngLat.longitude, lngLat.latitude);
                }
                mapController.addPolyline(new PolylineOptions()
                        .add(lnglats)
                        .color((i == 0) ? Color.BLUE : Color.GRAY)
                        .width(10)
                        .order(5)
                        .outlineWidth(2)
                        .clickable(true));

                listNodes.addAll(route.getRouteNodes());
            }

            // Setting map (regarding route's extent e.g. zoom and position)
            RouteUtils routeUtils = new RouteUtils(mMapView.getWidth(), mMapView.getHeight(),
                    mMapView.getScrollX(), mMapView.getScrollY(),
                    mapController.getMapCameraPosition().getZoom());
            HashMap<String, String> mapValues = routeUtils.zoomToPointsBoundingBox(listNodes);

            double zoom = Double.parseDouble(Objects.requireNonNull(mapValues.get(RouteUtils.KEY_ZOOM_LEVEL)));
            int zoomEased = Integer.parseInt(Objects.requireNonNull(mapValues.get(RouteUtils.KEY_ZOOM_EASED)));
            double lat = Double.parseDouble(Objects.requireNonNull(mapValues.get(RouteUtils.KEY_POSITION_LAT)));
            double lng = Double.parseDouble(Objects.requireNonNull(mapValues.get(RouteUtils.KEY_POSITION_LNG)));
            int positionEased = Integer.parseInt(Objects.requireNonNull(mapValues.get(RouteUtils.KEY_POSITION_EASED)));

            mapController.setZoomBy((float) zoom, zoomEased);
            mapController.setLngLat(new LngLat(lng, lat), positionEased);
        });
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (mMapController != null)
//            mMapController.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

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

        if (mRouteManager != null) {
            mRouteManager.onDestroy();
            mRouteManager = null;
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mMapView != null)
            mMapView.onLowMemory();
    }
}
