package com.example.nu_mad_sp2023_final_project_15.Upload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nu_mad_sp2023_final_project_15.LandingPage;
import com.example.nu_mad_sp2023_final_project_15.R;

import org.w3c.dom.Text;

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

                String place = txtUploadTravelPlace.getText().toString().trim();
                String date = txtUploadTravelDate.getText().toString().trim();

                if (place.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter travel place", Toast.LENGTH_LONG).show();
                    return;
                }
                else if (date.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter travel date", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(UploadPlacePage.this, UploadPicturesPage.class);
                intent.putExtra("place", place);
                intent.putExtra("date", date);
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