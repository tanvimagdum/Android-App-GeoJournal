package com.example.nu_mad_sp2023_final_project_15.Display;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.nu_mad_sp2023_final_project_15.R;

public class DisplayCultureActivity extends AppCompatActivity {

    private TextView txtDisplayCultureDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_culture);
        setTitle("Culture and Cuisine");

        txtDisplayCultureDetail = findViewById(R.id.txtDisplayCultureDetail);
        String culture = getIntent().getStringExtra("culture");
        txtDisplayCultureDetail.setText(culture);
    }
}