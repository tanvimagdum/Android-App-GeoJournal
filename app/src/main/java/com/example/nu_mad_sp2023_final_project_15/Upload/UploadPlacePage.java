package com.example.nu_mad_sp2023_final_project_15.Upload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nu_mad_sp2023_final_project_15.LandingPage;
import com.example.nu_mad_sp2023_final_project_15.R;

public class UploadPlacePage extends AppCompatActivity {

    private TextView txtUploadTravelPlace;
    private TextView txtUploadTravelDate;
    private Button btnUploadPlaceNext;
    private Button btnUploadPlaceExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_place_page);
        setTitle("Add Location");

        txtUploadTravelPlace = findViewById(R.id.txtUploadTravelPlace);
        txtUploadTravelDate = findViewById(R.id.txtUploadTravelDate);
        btnUploadPlaceNext = findViewById(R.id.btnUploadPlaceNext);
        btnUploadPlaceExit = findViewById(R.id.btnUploadPlaceExit);

        btnUploadPlaceNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadPlacePage.this, UploadPicturesPage.class);
                startActivity(intent);
            }
        });

        btnUploadPlaceExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadPlacePage.this, LandingPage.class);
            }
        });
    }
}