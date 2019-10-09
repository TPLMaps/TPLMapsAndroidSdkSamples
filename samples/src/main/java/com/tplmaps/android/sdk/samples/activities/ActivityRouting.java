package com.tplmaps.android.sdk.samples.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

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
import com.tplmaps3d.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ActivityRouting extends BaseMapActivity {

    TPLRouteManager mRouteManager;
    BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing);

        // Initiating TPLRouteManager instance
        mRouteManager = new TPLRouteManager();
        mRouteManager.onCreate(this);

        // Initiating bottom sheet and setting its default state
        initBottomSheet();

        // Filled field with sample location values
        String strSrc = "33.711556,73.058382";
        ((EditText) findViewById(R.id.source)).setText(strSrc);
        String strDest = "33.522695,73.094223";
        ((EditText) findViewById(R.id.destination)).setText(strDest);

        onMapCreate(savedInstanceState);
    }

    private void initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));
        findViewById(R.id.header_arrow).setOnClickListener(view -> {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                updateUIForCollapsedState();
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                updateUIForExpandedState();
            }
        });

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
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
        // Loading Default Map Controls
        mapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent("Permission Required",
                        "Location permission is required for the application to show your" +
                                " precise and accurate location on map");
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
        source.setX(Double.valueOf(arrSource[1]));
        source.setY(Double.valueOf(arrSource[0]));
        // Destination Location
        Place destination = new Place();
        destination.setName("Destination");
        destination.setX(Double.valueOf(arrDestination[1]));
        destination.setY(Double.valueOf(arrDestination[0]));

        locations.add(source);
        locations.add(destination);

        // Building route config
        TPLRouteConfig config = new TPLRouteConfig.Builder()
                .reRoute(false)
                .endPoints(locations)
                .heading(90)
                .build();

        // Calling for calculating routes for source and destination locations with config
        mRouteManager.calculate(this, config, (endPoints, routes) -> {
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

                // Setting map (regarding route's extent e.g. zoom and position)
                RouteUtils routeUtils = new RouteUtils(mMapView.getWidth(), mMapView.getHeight(),
                        mMapView.getScrollX(), mMapView.getScrollY(), mapController.getMapCameraPosition().getZoom());
                HashMap<String, String> mapValues = routeUtils.zoomToPointsBoundingBox(
                        route.getRouteNodes());

                double zoom = Double.valueOf(Objects.requireNonNull(mapValues.get(RouteUtils.KEY_ZOOM_LEVEL)));
                int zoomEased = Integer.valueOf(Objects.requireNonNull(mapValues.get(RouteUtils.KEY_ZOOM_EASED)));
                double lat = Double.valueOf(Objects.requireNonNull(mapValues.get(RouteUtils.KEY_POSITION_LAT)));
                double lng = Double.valueOf(Objects.requireNonNull(mapValues.get(RouteUtils.KEY_POSITION_LNG)));
                int positionEased = Integer.valueOf(Objects.requireNonNull(mapValues.get(RouteUtils.KEY_POSITION_EASED)));

                mapController.setZoomBy((float) zoom, zoomEased);
                mapController.setLngLat(new LngLat(lng, lat), positionEased);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRouteManager.onDestroy();
        mRouteManager = null;
    }
}
