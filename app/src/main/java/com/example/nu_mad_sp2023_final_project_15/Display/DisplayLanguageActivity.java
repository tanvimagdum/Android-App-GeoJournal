package com.example.nu_mad_sp2023_final_project_15.Display;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.nu_mad_sp2023_final_project_15.R;

public class DisplayLanguageActivity extends AppCompatActivity {

    private TextView txtDisplayLanguageDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_language);
        setTitle("Language Notes");

        txtDisplayLanguageDetail = findViewById(R.id.txtDisplayLanguageDetail);
        String language = getIntent().getStringExtra("language");
        txtDisplayLanguageDetail.setText(language);
    }
}