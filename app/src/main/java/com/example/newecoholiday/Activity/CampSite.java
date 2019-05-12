package com.example.newecoholiday.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newecoholiday.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CampSite extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    private MarkerOptions activitySpot;

    TextView txtParkCampSite,txtCampSiteDesc,txtCampSite,txtType,txtTime,txtLength,txtFacilities;
    LinearLayout linLenTime,linFacilities;
    SharedPreferences sharedpreferences;
    String parkName,facilityName,latitude,longitude,description,type;
    String facilities="";
    float trackLength,trackTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_site);

        txtCampSiteDesc = (TextView)findViewById(R.id.txtCampSiteDesc);
        txtParkCampSite = (TextView)findViewById(R.id.txtParkCampSite);
        txtCampSite = (TextView)findViewById(R.id.txtCampSite);
        txtLength = (TextView)findViewById(R.id.txtLength);
        txtTime = (TextView)findViewById(R.id.txtTime);
        txtFacilities = (TextView)findViewById(R.id.txtFacilities);

        sharedpreferences = getSharedPreferences(Home.MyPREFERENCES, Context.MODE_PRIVATE);

        parkName = sharedpreferences.getString("ParkName","");
        facilityName = sharedpreferences.getString("FacilityName","");
        description = sharedpreferences.getString("FacilityDesc","");
        latitude = sharedpreferences.getString("FacilityLatitude","");
        longitude = sharedpreferences.getString("FacilityLongitude","");
        type = sharedpreferences.getString("FacilityType","");
        facilities = sharedpreferences.getString("CampFacilities","");
        linLenTime = (LinearLayout)findViewById(R.id.linLenTime);
        linFacilities = (LinearLayout)findViewById(R.id.linFacilities);


        //Toast.makeText(getApplicationContext()," " + facilities, Toast.LENGTH_LONG).show();

        if(type == "Hiking"){

            linFacilities.setVisibility(View.GONE);

        }else if (type == "Camping"){
            linLenTime.setVisibility(View.GONE);
            txtFacilities.setText(facilities);

        }else {
            linLenTime.setVisibility(View.GONE);
            linFacilities.setVisibility(View.GONE);
        }
        trackLength = sharedpreferences.getFloat("TrackLength",0);
        trackTime= sharedpreferences.getFloat("TrackTime",0);
        txtLength.setText(String.valueOf(trackLength+" KMs"));
        txtTime.setText(String.valueOf(trackTime+" hrs"));

        txtCampSite.setText(facilityName);
        txtParkCampSite.setText(parkName);
        txtCampSiteDesc.setText(description);

        SupportMapFragment mapa = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        activitySpot =  new MarkerOptions().position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude))).title(facilityName).icon(BitmapDescriptorFactory.fromResource(R.drawable.current));;
        mapa.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getApplicationContext());

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        //Log.d("mylog", "Added Markers");
        mMap.addMarker(activitySpot);

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude)))
                .zoom(10)
                .bearing(0)
                .tilt(45)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 100, null);
    }


}
