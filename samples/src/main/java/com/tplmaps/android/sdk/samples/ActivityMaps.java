package com.tplmaps.android.sdk.samples;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.tplmaps.android.R;
import com.tplmaps3d.CameraPosition;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapMode;
import com.tplmaps3d.MapView;
import com.tplmaps3d.sdk.utils.CommonUtils;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityMaps extends AppCompatActivity implements MapView.OnMapReadyCallback {

    //private static final String TAG = ActivityMaps.class.getSimpleName();
    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Getting MapView resource from layout
        mMapView = findViewById(R.id.map);

        /* MapView is initialized here, call enable traffic function now (Traffic is disabled by default)
         * Calling function here means map will be loaded with the traffic update (pre call),
         * you can also call function in onMapReady callback method means traffic update
         * will be applied when map will be on ready to render state.*/
        //mMapView.setTrafficEnabled(true);

        // Calling MapView's onCreate() lifecycle method
        mMapView.onCreate(savedInstanceState);
        // Loading map Asynchronously vie registering call
        mMapView.loadMapAsync(this);

    }

    @Override
    public void onMapReady(final MapController mapController) {
        CommonUtils.showToast(this, "Map Ready", Toast.LENGTH_SHORT, false);

        // TODO: Map loaded and ready, perform your map operations from here
        // Setting map max tilt value
        mapController.setMaxTilt(85);

        /* MapView is initialized here, call enable traffic function now (Traffic is disabled by default)
         * Calling function here means map will update traffic when it is loaded and ready (post call),
         * you can also call function in onMapReady callback method means traffic update
         * will be applied when map will be on ready to render state.*/
        //mMapView.setTrafficEnabled(true);
        mapController.getUiSettings().showZoomControls(false);
        mapController.getUiSettings().showMyLocationButton(false);


        mapController.setMyLocationEnabled(true , MapController.MyLocationArg.ZOOM_LOCATION_ON_FIRST_FIX);


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
