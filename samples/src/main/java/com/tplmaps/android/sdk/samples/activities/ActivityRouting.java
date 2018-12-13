package com.tplmaps.android.sdk.samples.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tpl.maps.sdk.routing.IMapRoute;
import com.tpl.maps.sdk.routing.TPLRouteConfig;
import com.tpl.maps.sdk.routing.TPLRouteManager;
import com.tpl.maps.sdk.routing.structures.Place;
import com.tpl.maps.sdk.routing.structures.TPLRoute;
import com.tpl.maps.sdk.routing.structures.TPLRouteDirection;
import com.tplmaps.android.R;
import com.tplmaps.android.sdk.samples.utils.CommonUtilities;

import java.util.ArrayList;

public class ActivityRouting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing);

        // Initializing locations array
        ArrayList<Place> locations = new ArrayList<>();
        // Preparing source location
        Place source = new Place();
        source.setName("TPL Corp ISE Office");
        source.setX(73.058382);
        source.setY(33.711556);
        // Preparing destination location
        Place destination = new Place();
        destination.setName("TPL Maps Bahria Office");
        destination.setX(73.094223);
        destination.setY(33.522695);

        locations.add(source);
        locations.add(destination);

        TPLRouteConfig config = new TPLRouteConfig.Builder(false, locations)
                .build();

        new TPLRouteManager().calculate(this, config, new IMapRoute() {
            @Override
            public void onMapRoutingOverview(ArrayList<Place> endPoints, ArrayList<TPLRoute> routes) {
                String response;
                response = "Start: " + endPoints.get(0).getY() + "," + endPoints.get(0).getX() + "\n";
                response += "End: " + endPoints.get(1).getY() + "," + endPoints.get(1).getX() + "\n";

                int routeNo = 0;
                for (TPLRoute route : routes) {
                    response += "\n\nRoute " + (++routeNo) + "\nRoute Length (In Meters): " + route.getTotalLength() + "\n"
                            + "Route Time (In Milliseconds): " + route.getTotalTime() + "\n";
                    response += "Turns: \n";
                    for (TPLRouteDirection routeDirection : route.getListRouteDirections()) {
                        response += routeDirection.getCompleteText() + "\n";
                    }
                    response += "-----------------------------------";
                }

                ((TextView) findViewById(R.id.tvResponse)).setText(response);
            }
        });
    }
}
