package com.example.nu_mad_sp2023_final_project_15.Display;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nu_mad_sp2023_final_project_15.R;

public class DisplayItineraryActivity extends AppCompatActivity {

    private TextView txtDisplayItineraryDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_itinerary);
        setTitle("Itinerary and Plans");

        txtDisplayItineraryDetail = findViewById(R.id.txtDisplayItineraryDetail);
        String itinerary = getIntent().getStringExtra("itinerary");
        txtDisplayItineraryDetail.setText(itinerary);

    }
}