package com.tplmaps.android.sdk.samples.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;

public class ActivityMapStyle extends AppCompatActivity implements MapView.OnMapReadyCallback {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_style);

        // Initializing and getting MapView resource
        mMapView = (MapView) findViewById(R.id.map);

        // Setting custom map style before map ready
        setMapStyle();

        // Loading map Asynchronously
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

        if (mMapView != null)
            mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        if (mMapView != null)
            mMapView.onLowMemory();
    }

    @Override
    public void onMapReady(MapController mapController) {

        // TODO: Do you map tasks here

        // Setting custom map style after map ready
        //setMapStyle();

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

    private void setMapStyle() {
        // Set style specified in json string
        //mMapView.setMapStyle(getJsonString());

        // Set style specified in a resource file
        mMapView.setMapStyle(R.raw.sample_map_style1);
    }

    /*private String getJsonString() {
        return "[\n" +
                "  {\n" +
                "    \"featureType\": \"map.mode\",\n" +
                "\t\"styler\": { \n" +
                "\t\t\"mode\": \"night\" \n" +
                "\t}\n" +
                "  },{\n" +
                "    \"featureType\": \"map.pois\",\n" +
                "\t\"styler\": { \n" +
                "\t\t\"visible\": \"true\" \n" +
                "\t}\n" +
                "  },{\n" +
                "    \"featureType\": \"map.buildings\",\n" +
                "\t\"styler\": { \n" +
                "\t\t\"visible\": \"true\" \n" +
                "\t}\n" +
                "  },{\n" +
                "    \"featureType\": \"road.motorway\",\n" +
                "    \"styler\": { \n" +
                "\t\t\"color\": \"'#4194f4'\",\n" +
                "\t\t\"width\": \"[[5, 0.4px],[10,2px],[15,5px],[20,65px]]\",\n" +
                "\t\t\"outline.color\": \"'#000000'\",\n" +
                "\t\t\"outline.width\": \"[[5, 0.1px],[10, 0.5px],[15,1px],[20,2px]]\",\n" +
                "\t\t\"visible\": \"true\" \n" +
                "\t}\n" +
                "  },{\n" +
                "    \"featureType\": \"road.street\",\n" +
                "\t\"styler\": { \n" +
                "\t\t\"color\": \"'#f44141'\", \n" +
                "\t\t\"width\": \"[[14, 1px],[15,3.5px],[20,50px]]\", \n" +
                "\t\t\"outline.color\": \"'#000000'\", \n" +
                "\t\t\"visible\": \"true\" \n" +
                "\t}\n" +
                "  },{\n" +
                "    \"featureType\": \"road.main\",\n" +
                "\t\"styler\": { \n" +
                "\t\t\"color\": \"'#55f441'\", \n" +
                "\t\t\"width\": \"[[7, 0.5px],[10,1px],[15,4px],[20,100px]]\", \n" +
                "\t\t\"outline.color\": \"'#000000'\", \n" +
                "\t\t\"outline.width\": \"[[5, 0.1px],[10, 0.5px],[15,5px]]\", \n" +
                "\t\t\"visible\": \"true\" \n" +
                "\t}\n" +
                "  }\n" +
                "]";
    }*/
}
