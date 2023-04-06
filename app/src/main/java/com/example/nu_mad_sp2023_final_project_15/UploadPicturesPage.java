package com.example.nu_mad_sp2023_final_project_15;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class UploadPicturesPage extends AppCompatActivity implements View.OnClickListener {

    private Button btnUploadPicture;
    private Button btnUploadPictureNext;
    private Button btnUploadPictureExit;
    private Uri imageUri;

    private List<Uri> images = new ArrayList<>();
    private RecyclerView recyclerView;
    private UploadPictureAdapter adapter;

    ActivityResultLauncher<Intent> startActivityForResult
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        assert result.getData() != null;
                        imageUri = result.getData().getData();
                        if (imageUri == null) {
                            Bundle extras = result.getData().getExtras();
                            Bitmap imageBitmap = (Bitmap) extras.get("data");
                            imageUri = saveImageToStorage(imageBitmap);
                        }
                        images.add(imageUri);
                        adapter.notifyDataSetChanged();
//                        if (imgPreview != null) {
//                            imgPreview.setVisibility(View.VISIBLE);
//                            Glide.with(getActivity()).load(imageUri).into(imgPreview);
//                        }
                    }
                }
            });

    private Uri saveImageToStorage(Bitmap bitmap) {
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "ProfilePhoto", null);
        return Uri.parse(path);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pictures_page);
        setTitle("Add Location");

        btnUploadPicture = findViewById(R.id.btnUploadPicture);
        btnUploadPictureNext = findViewById(R.id.btnUploadPictureNext);
        btnUploadPictureExit = findViewById(R.id.btnUploadPictureExit);

        btnUploadPicture.setOnClickListener(this);

        btnUploadPictureNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadPicturesPage.this, UploadItineraryExpensePage.class);
                startActivity(intent);
            }
        });

        btnUploadPictureExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadPicturesPage.this, LandingPage.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerUploadPicture);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new UploadPictureAdapter(images);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUploadPicture:
                selectPhoto();
                break;
        }
    }

    private void selectPhoto() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {cameraIntent});

        startActivityForResult.launch(chooserIntent);
    }

}