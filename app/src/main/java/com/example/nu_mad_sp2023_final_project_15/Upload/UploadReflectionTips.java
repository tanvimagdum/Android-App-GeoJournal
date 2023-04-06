package com.example.nu_mad_sp2023_final_project_15.Upload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nu_mad_sp2023_final_project_15.LandingPage;
import com.example.nu_mad_sp2023_final_project_15.R;

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

        btnUploadRTSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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