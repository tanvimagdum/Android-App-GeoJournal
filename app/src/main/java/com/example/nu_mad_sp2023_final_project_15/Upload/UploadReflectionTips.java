package com.example.nu_mad_sp2023_final_project_15.Upload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nu_mad_sp2023_final_project_15.LandingPage;
import com.example.nu_mad_sp2023_final_project_15.R;
import com.example.nu_mad_sp2023_final_project_15.TravelInfo;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class UploadReflectionTips extends AppCompatActivity {

    private TextView txtUploadReflection;
    private TextView txtUploadTips;
    private Button btnUploadRTSave;
    private Button btnUploadRTExit;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_reflection_tips);
        setTitle("Add Location");

        txtUploadReflection = findViewById(R.id.txtUploadReflection);
        txtUploadTips = findViewById(R.id.txtUploadTips);
        btnUploadRTSave = findViewById(R.id.btnUploadRTSave);
        btnUploadRTExit = findViewById(R.id.btnUploadRTExit);

        db = FirebaseFirestore.getInstance();

        LatLng LatLng = getIntent().getParcelableExtra("LatLng");
        String LatLngString = LatLng.latitude + "," + LatLng.longitude;
        String place = getIntent().getStringExtra("place");
        String date = getIntent().getStringExtra("date");
        List<Uri> images = getIntent().getParcelableArrayListExtra("images");
        String itinerary = getIntent().getStringExtra("itinerary");
        String expense = getIntent().getStringExtra("expense");
        String culture = getIntent().getStringExtra("culture");
        String language = getIntent().getStringExtra("language");

        List<String> stringImages = new ArrayList<>();

        for (Uri uri : images) {
            String uriString = uri.toString();
            stringImages.add(uriString);
        }

        btnUploadRTSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String reflection = txtUploadReflection.getText().toString().trim();
                String tips = txtUploadTips.getText().toString().trim();

                if (reflection.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter notes on reflection and thoughts", Toast.LENGTH_LONG).show();
                    return;
                }
                else if (tips.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter notes on travel tips", Toast.LENGTH_LONG).show();
                    return;
                }

                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                TravelInfo travelInfo = new TravelInfo(place, date, itinerary, expense,
                        culture, language, reflection, tips, stringImages, userId);

                db.collection("travel_info").document(LatLngString).set(travelInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Travel Information saved.", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Error saving Travel Information. " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                Intent intent = new Intent(UploadReflectionTips.this, LandingPage.class);
                startActivity(intent);

            }
        });

        btnUploadRTExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadReflectionTips.this, LandingPage.class);
                startActivity(intent);
            }
        });

    }
}