package com.tplmaps.android.sdk.samples.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tpl.maps.sdk.routing.IMapRoute;
import com.tpl.maps.sdk.routing.TPLRouteConfig;
import com.tpl.maps.sdk.routing.TPLRouteManager;
import com.tpl.maps.sdk.routing.structures.Place;
import com.tpl.maps.sdk.routing.structures.TPLRoute;
import com.tpl.maps.sdk.routing.structures.TPLRouteDirection;
import com.tplmaps.android.R;

import java.util.ArrayList;

public class ActivityRouting extends AppCompatActivity {

    TPLRouteManager mRouteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing);

        mRouteManager = new TPLRouteManager();
        mRouteManager.onCreate(this);
    }

    public void calculateRoute(View v) {
        v.setVisibility(View.GONE);

        // Initializing/preparing source and destination locations array
        ArrayList<Place> locations = new ArrayList<>();
        // Source location
        Place source = new Place();
        source.setName("TPL Corp ISE Office");
        source.setX(73.058382);
        source.setY(33.711556);
        // Destination Location
        Place destination = new Place();
        destination.setName("TPL Maps Bahria Office");
        destination.setX(73.094223);
        destination.setY(33.522695);

        locations.add(source);
        locations.add(destination);

        // Building route config
        TPLRouteConfig config = new TPLRouteConfig.Builder()
                .reRoute(false)
                .endPoints(locations)
                .heading(90)
                .build();

        // Calling for calculating routes for source and destination locations with config
        mRouteManager.calculate(this, config, new IMapRoute() {
            @Override
            public void onMapRoutingOverview(ArrayList<Place> endPoints, ArrayList<TPLRoute> routes) {
                StringBuilder response;
                response = new StringBuilder("Start: " + endPoints.get(0).getName()
                        + " (" + endPoints.get(0).getY() +
                        "," + endPoints.get(0).getX() + ")\n");
                response.append("End: ").append(endPoints.get(1).getName()).append(" (")
                        .append(endPoints.get(1).getY()).append(",")
                        .append(endPoints.get(1).getX()).append(")\n");

                int routeNo = 0;
                for (TPLRoute route : routes) {
                    response.append("\n\nRoute ").append(++routeNo)
                            .append("\nRoute Length (In Meters): ").append(route.getTotalLength())
                            .append("\n").append("Route Time (In Milliseconds): ")
                            .append(route.getTotalTime()).append("\n");
                    response.append("Turns: \n");
                    for (TPLRouteDirection routeDirection : route.getListRouteDirections()) {
                        response.append(routeDirection.getCompleteText()).append("\n");
                    }
                    response.append("-----------------------------------");
                }

                ((TextView) findViewById(R.id.tvResponse)).setText(response.toString());
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
