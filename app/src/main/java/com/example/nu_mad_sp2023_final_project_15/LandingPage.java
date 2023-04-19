package com.example.nu_mad_sp2023_final_project_15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LandingPage extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button myProfile;
    private Button btnAddLocation;
    private Marker yellowMarker;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private FirebaseFirestore db;
    private boolean isMarkerDragged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        setTitle("Locations Visited");

        isMarkerDragged = false;
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        myProfile = findViewById(R.id.btnMyProfile);
        btnAddLocation = findViewById(R.id.btnAddLocation);

        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isNetworkAvailable()){
                    Context context = getApplicationContext();
                    CharSequence text = "No Internet Connection!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Toast.makeText(context, text, duration);
                    return;
                }
                Intent intent
                        = new Intent(LandingPage.this,
                        ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isNetworkAvailable()){
                    Context context = getApplicationContext();
                    CharSequence text = "No Internet Connection!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Toast.makeText(context, text, duration);
                    return;
                }
                if (isMarkerDragged) {
                    Intent intent = new Intent(LandingPage.this, UploadPlacePage.class);
                    intent.putExtra("LatLng", yellowMarker.getPosition());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please drag the yellow marker to set " +
                            "the location before adding.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!isNetworkAvailable()){
            Context context = getApplicationContext();
            CharSequence text = "No Internet Connection!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Toast.makeText(context, text, duration);
            return;
        }
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser == null){
            Intent intent
                    = new Intent(LandingPage.this,
                    LoginAndSignUp.class);
            startActivity(intent);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng draggable = new LatLng(0, 0);
        float hue = BitmapDescriptorFactory.HUE_YELLOW;
        MarkerOptions markerOptions = new MarkerOptions()
                .position(draggable)
                .title("Drag this marker to any location that you want to add")
                .icon(BitmapDescriptorFactory.defaultMarker(hue)).draggable(true);
        yellowMarker = mMap.addMarker(markerOptions);
        Task<QuerySnapshot> snapshots = db.collection(currentUser.getEmail()).get();
        snapshots.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot document : task.getResult().getDocuments()){
                    String[] coordinates = document.getId().split(",");
                    LatLng pos = new LatLng(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
                    float hue_red = BitmapDescriptorFactory.HUE_RED;
                    MarkerOptions markerOptionsRed = new MarkerOptions()
                            .position(pos)
                            .title(document.get("trPlace").toString())
                            .icon(BitmapDescriptorFactory.defaultMarker(hue_red)).draggable(false);
                    mMap.addMarker(markerOptionsRed);
                }
            }
        });
        mMap.moveCamera(CameraUpdateFactory.newLatLng(draggable));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                if(marker.getTitle().equals("Drag this marker to any location that you want to add")){
                    return false;
                }
                if(!isNetworkAvailable()){
                    Context context = getApplicationContext();
                    CharSequence text = "No Internet Connection!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Toast.makeText(context, text, duration);
                    return false;
                }
                Intent intent = new Intent(LandingPage.this, DisplayPage.class);
                intent.putExtra("LatLng",marker.getPosition());
                startActivity(intent);
                return false;
            }
        });
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {
                isMarkerDragged = true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}