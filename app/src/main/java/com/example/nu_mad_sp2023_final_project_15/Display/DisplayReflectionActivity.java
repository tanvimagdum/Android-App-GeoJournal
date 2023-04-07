package com.example.nu_mad_sp2023_final_project_15.Display;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.nu_mad_sp2023_final_project_15.R;

public class DisplayReflectionActivity extends AppCompatActivity {

    private TextView txtDisplayReflectionDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_reflection);
        setTitle("Reflection and Thoughts");

        txtDisplayReflectionDetail = findViewById(R.id.txtDisplayReflectionDetail);
        String reflection = getIntent().getStringExtra("reflections");
        txtDisplayReflectionDetail.setText(reflection);
    }
}