package com.example.nu_mad_sp2023_final_project_15.Upload;

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

import java.util.List;

public class UploadReflectionTips extends AppCompatActivity {

    private TextView txtUploadReflection;
    private TextView txtUploadTips;
    private Button btnUploadRTSave;
    private Button btnUploadRTExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_reflection_tips);
        setTitle("Add Location");

        txtUploadReflection = findViewById(R.id.txtUploadReflection);
        txtUploadTips = findViewById(R.id.txtUploadTips);
        btnUploadRTSave = findViewById(R.id.btnUploadRTSave);
        btnUploadRTExit = findViewById(R.id.btnUploadRTExit);

        String place = getIntent().getStringExtra("place");
        String date = getIntent().getStringExtra("date");
        List<Uri> images = getIntent().getParcelableArrayListExtra("images");
        String itinerary = getIntent().getStringExtra("itinerary");
        String expense = getIntent().getStringExtra("expense");
        String culture = getIntent().getStringExtra("culture");
        String language = getIntent().getStringExtra("language");

        btnUploadRTSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String reflection = txtUploadReflection.toString().trim();
                String tips = txtUploadTips.toString().trim();

                if (reflection.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter notes on reflection and thoughts", Toast.LENGTH_LONG).show();
                    return;
                }
                else if (tips.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter notes on travel tips", Toast.LENGTH_LONG).show();
                    return;
                }

                Log.d("place", place);
                Log.d("date", date);
                Log.d("itinerary", itinerary);
                Log.d("expense", expense);
                Log.d("culture", culture);
                Log.d("lang", language);
                Log.d("reflec", reflection);
                Log.d("Tips", tips);
                if (images.size()!=0) {
                    Log.d("images", "i have 'em all");
                }

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