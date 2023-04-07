package com.example.nu_mad_sp2023_final_project_15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nu_mad_sp2023_final_project_15.Display.DisplayPage;
import com.example.nu_mad_sp2023_final_project_15.Upload.UploadPlacePage;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LandingPage extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button myProfile;
    private Button btnAddLocation;
    private Marker yellowMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        setTitle("Locations Visited");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        myProfile = findViewById(R.id.btnMyProfile);
        btnAddLocation = findViewById(R.id.btnAddLocation);

        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent
                        = new Intent(LandingPage.this,
                        ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPage.this, UploadPlacePage.class);
                intent.putExtra("LatLng", yellowMarker.getPosition().toString());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        float hue = BitmapDescriptorFactory.HUE_YELLOW;
        MarkerOptions markerOptions = new MarkerOptions()
                .position(sydney)
                .title("Draggable Marker")
                .icon(BitmapDescriptorFactory.defaultMarker(hue)).draggable(true);
        yellowMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                Log.d("demo",sydney.toString());
                if(marker.getTitle().equals("Draggable Marker")){
                    return false;
                }
                Intent intent = new Intent(LandingPage.this, DisplayPage.class);
                LatLng syd = marker.getPosition();
                startActivity(intent);
                return false;
            }
        });


    }
}