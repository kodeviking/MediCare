package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapRoute;
import com.here.android.mpa.mapping.MapView;
import com.here.android.mpa.mapping.SupportMapFragment;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import java.io.IOException;

public class MapAcitvity extends AppCompatActivity {
    private AndroidXMapFragment mapFragment = null;
    private Map map = null;
    private static MapRoute mapRoute1;
    private String locationAddress = "";
    private Double latitude  = 19.0363;
    private Double langitude = 73.0154;
    private static View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_acitvity);




            mapFragment = (AndroidXMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapfragment);
//        mapFragment.getMapAsync(this);
            mapFragment.init(new OnEngineInitListener() {
                @Override
                public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                    if (error == OnEngineInitListener.Error.NONE) {
                        map = null;
                        map = mapFragment.getMap();
                        // Set the map center to the Vancouver region (no animation)
                        map.setCenter(new GeoCoordinate(latitude, langitude, 0.0),
                                Map.Animation.NONE);
                        map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
                        map.getPositionIndicator().setVisible(true);
                        try {
//                        MapMarker defaultMarker = new MapMarker();
//                        defaultMarker.setCoordinate(new GeoCoordinate(37.7397, -121.4252, 0.0));
//                        map.addMapObject(defaultMarker);
                            Image image = new Image();
                            image.setImageResource(R.drawable.mmarker);
                            MapMarker customMarker = new MapMarker(new GeoCoordinate(19.0363, 73.0154, 0.0), image);
                            map.addMapObject(customMarker);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("HEREMAP", "Cannot initialize MapFragment (" + error + ")");
                    }
                }
            });
    }
}
