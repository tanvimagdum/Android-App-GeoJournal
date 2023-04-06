package com.example.nu_mad_sp2023_final_project_15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.nu_mad_sp2023_final_project_15.Upload.UploadPicturesPage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, LoginAndSignUp.class);
        startActivity(intent);
    }
}