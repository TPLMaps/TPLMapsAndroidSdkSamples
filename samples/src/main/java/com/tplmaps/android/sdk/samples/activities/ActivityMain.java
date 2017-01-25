package com.tplmaps.android.sdk.samples.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Button btnUIControls = (Button) findViewById(R.id.btn_ui_controls);
        btnUIControls.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_maps:
                startActivity(new Intent(ActivityMain.this, ActivityMaps.class));
                break;
            case R.id.btn_ui_controls:
                startActivity(new Intent(ActivityMain.this, ActivityUIControls.class));
                break;
        }
    }
}
