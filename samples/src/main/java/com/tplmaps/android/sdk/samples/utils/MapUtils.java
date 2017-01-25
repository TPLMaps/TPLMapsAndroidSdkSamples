package com.tplmaps.android.sdk.samples.utils;

import android.os.Bundle;
import android.util.Log;

import com.tplmaps3d.MapView;

/**
 * Created by hassanjamil on 2017-01-25.
 * @author hassanjamil
 */

public class MapUtils {

    public static final String TAG = MapUtils.class.getSimpleName();

    public static void initAndLoadMaps(Bundle savedInstanceState, MapView mapView,
                                       MapView.OnMapReadyCallback callback) {
        if(mapView == null) {
            Log.e(TAG, "::initAndLoadMaps: MapView instance is null");
            return;
        }

        if(callback == null) {
            Log.e(TAG, "::initAndLoadMaps: OnMapReadyCallback instance is null");
            return;
        }

        mapView.onCreate(savedInstanceState);

        // Registering listener, loading scene file and loading map async
        mapView.loadMapAsync(callback);
    }
}
