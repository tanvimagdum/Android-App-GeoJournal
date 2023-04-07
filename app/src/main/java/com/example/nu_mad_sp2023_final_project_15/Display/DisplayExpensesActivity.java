package com.example.nu_mad_sp2023_final_project_15.Display;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.nu_mad_sp2023_final_project_15.R;

public class DisplayExpensesActivity extends AppCompatActivity {

    private TextView txtDisplayExpenseDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_expenses);
        setTitle("Expenses");

        txtDisplayExpenseDetail = findViewById(R.id.txtDisplayExpenseDetail);
        String expense = getIntent().getStringExtra("expenses");
        txtDisplayExpenseDetail.setText(expense);
    }
}