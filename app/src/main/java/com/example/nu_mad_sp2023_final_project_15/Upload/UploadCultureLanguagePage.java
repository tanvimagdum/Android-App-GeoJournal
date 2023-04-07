package com.example.nu_mad_sp2023_final_project_15.Upload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nu_mad_sp2023_final_project_15.LandingPage;
import com.example.nu_mad_sp2023_final_project_15.R;
import com.google.android.gms.maps.model.LatLng;

public class UploadCultureLanguagePage extends AppCompatActivity {

    private TextView txtUploadCulture;
    private TextView txtUploadLanguage;
    private Button btnUploadCLNext;
    private Button btnUploadCLExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_culture_language_page);
        setTitle("Add Location");

        txtUploadCulture = findViewById(R.id.txtUploadCulture);
        txtUploadLanguage = findViewById(R.id.txtUploadLanguage);
        btnUploadCLNext = findViewById(R.id.btnUploadCLNext);
        btnUploadCLExit = findViewById(R.id.btnUploadCLExit);

        btnUploadCLNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String culture = txtUploadCulture.getText().toString().trim();
                String language = txtUploadLanguage.getText().toString().trim();

                if (culture.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter notes on culture", Toast.LENGTH_LONG).show();
                    return;
                }
                else if (language.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter notes on language", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(UploadCultureLanguagePage.this, UploadReflectionTips.class);
                LatLng LatLng = getIntent().getParcelableExtra("LatLng");
                intent.putExtra("LatLng", LatLng);
                intent.putExtra("place", getIntent().getStringExtra("place"));
                intent.putExtra("date", getIntent().getStringExtra("date"));
                intent.putExtra("images", getIntent().getParcelableArrayListExtra("images"));
                intent.putExtra("itinerary", getIntent().getStringExtra("itinerary"));
                intent.putExtra("expense", getIntent().getStringExtra("expense"));
                intent.putExtra("culture", culture);
                intent.putExtra("language", language);
                startActivity(intent);
            }
        });

        btnUploadCLExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadCultureLanguagePage.this, LandingPage.class);
                startActivity(intent);
            }
        });

    }
}