package com.tplmaps.android.sdk.samples.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tplmaps.android.R;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        Button btnMaps = (Button) findViewById(R.id.btn_maps);
        btnMaps.setOnClickListener(this);

        Button btnMapFeatures = (Button) findViewById(R.id.btn_map_features);
        btnMapFeatures.setOnClickListener(this);

        Button btnUIControls = (Button) findViewById(R.id.btn_ui_controls);
        btnUIControls.setOnClickListener(this);

        Button btnCamera = (Button) findViewById(R.id.btn_map_camera);
        btnCamera.setOnClickListener(this);

        Button btnMapGestures = (Button) findViewById(R.id.btn_map_gestures);
        btnMapGestures.setOnClickListener(this);

        Button btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);

        Button btnShapes = (Button) findViewById(R.id.btn_shapes);
        btnShapes.setOnClickListener(this);

        Button btnInfoWindows = (Button) findViewById(R.id.btn_info_windows);
        btnInfoWindows.setOnClickListener(this);

        Button btnMapStyle = (Button) findViewById(R.id.btn_map_style);
        btnMapStyle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
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
