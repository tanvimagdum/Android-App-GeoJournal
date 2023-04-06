package com.example.nu_mad_sp2023_final_project_15.Upload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nu_mad_sp2023_final_project_15.LandingPage;
import com.example.nu_mad_sp2023_final_project_15.R;

import java.util.List;

public class UploadItineraryExpensePage extends AppCompatActivity {

    private TextView txtUploadItinerary;
    private TextView txtUploadExpense;
    private Button btnUploadIENext;
    private Button btnUploadIEExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_itinerary_expense_page);
        setTitle("Add Location");

        txtUploadItinerary = findViewById(R.id.txtUploadItinerary);
        txtUploadExpense = findViewById(R.id.txtUploadExpense);
        btnUploadIENext = findViewById(R.id.btnUploadIENext);
        btnUploadIEExit = findViewById(R.id.btnUploadIEExit);

        btnUploadIENext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itinerary = txtUploadItinerary.getText().toString().trim();
                String expense = txtUploadExpense.getText().toString().trim();

                if (itinerary.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter notes on itinerary and plans", Toast.LENGTH_LONG).show();
                    return;
                }
                else if (expense.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter notes on expenses", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(UploadItineraryExpensePage.this, UploadCultureLanguagePage.class);
                intent.putExtra("place", getIntent().getStringExtra("place"));
                intent.putExtra("date", getIntent().getStringExtra("date"));
                intent.putExtra("images", getIntent().getParcelableArrayListExtra("images"));
                intent.putExtra("itinerary", itinerary);
                intent.putExtra("expense", expense);
                startActivity(intent);
            }
        });

        btnUploadIEExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadItineraryExpensePage.this, LandingPage.class);
                startActivity(intent);
            }
        });


    }
}