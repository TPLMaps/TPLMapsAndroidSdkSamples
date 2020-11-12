package com.tplmaps.android.sdk.samples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tplmaps.android.BuildConfig;
import com.tplmaps.android.R;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("TPLMaps SDK v" + BuildConfig.VERSION_NAME);
        //CommonUtils.showToast(this, getDeviceAbi(), Toast.LENGTH_LONG, true);
    }

    /*public String getDeviceAbi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Build.SUPPORTED_ABIS[0];
        } else {
            return Build.CPU_ABI;
        }
    }*/

    private void initViews() {
        Button btnMaps = findViewById(R.id.btn_maps);
        btnMaps.setOnClickListener(this);

        Button btnMapFeatures = findViewById(R.id.btn_map_features);
        btnMapFeatures.setOnClickListener(this);

        Button btnUIControls = findViewById(R.id.btn_ui_controls);
        btnUIControls.setOnClickListener(this);

        Button btnCamera = findViewById(R.id.btn_map_camera);
        btnCamera.setOnClickListener(this);

        Button btnMapGestures = findViewById(R.id.btn_map_gestures);
        btnMapGestures.setOnClickListener(this);

        Button btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);

        Button btnRouting = findViewById(R.id.btn_routing);
        btnRouting.setOnClickListener(this);

        Button btnShapes = findViewById(R.id.btn_shapes);
        btnShapes.setOnClickListener(this);

        Button btnInfoWindows = findViewById(R.id.btn_info_windows);
        btnInfoWindows.setOnClickListener(this);

        Button btnMapStyle = findViewById(R.id.btn_map_style);
        btnMapStyle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_maps:
                startActivity(new Intent(ActivityMain.this, ActivityMaps.class));
                break;
            case R.id.btn_map_features:
                startActivity(new Intent(ActivityMain.this, ActivityMapFeatures.class));
                break;
            case R.id.btn_ui_controls:
                startActivity(new Intent(ActivityMain.this, ActivityUIControls.class));
                break;
            case R.id.btn_map_camera:
                startActivity(new Intent(ActivityMain.this, ActivityCamera.class));
                break;
            case R.id.btn_map_gestures:
                startActivity(new Intent(ActivityMain.this, ActivityMapGestures.class));
                break;
            case R.id.btn_search:
                startActivity(new Intent(ActivityMain.this, ActivitySearch.class));
                break;
            case R.id.btn_routing:
                startActivity(new Intent(ActivityMain.this, ActivityRouting.class));
                break;
            case R.id.btn_shapes:
                startActivity(new Intent(ActivityMain.this, ActivityShapes.class));
                break;
            case R.id.btn_info_windows:
                startActivity(new Intent(ActivityMain.this, ActivityInfoWindows.class));
                break;
            case R.id.btn_map_style:
                startActivity(new Intent(ActivityMain.this, ActivityMapStyle.class));
                break;
        }
    }
}
