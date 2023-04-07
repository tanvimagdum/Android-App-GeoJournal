package com.example.nu_mad_sp2023_final_project_15.Display;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.nu_mad_sp2023_final_project_15.R;

public class DisplayTipsActivity extends AppCompatActivity {

    private TextView txtDisplayTipsDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tips);
        setTitle("Travel Tips");

        txtDisplayTipsDetail = findViewById(R.id.txtDisplayTipsDetail);
        String tips = getIntent().getStringExtra("travelTips");
        txtDisplayTipsDetail.setText(tips);
    }
}