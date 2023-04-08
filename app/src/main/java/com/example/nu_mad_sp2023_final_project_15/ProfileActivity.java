package com.example.nu_mad_sp2023_final_project_15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProfileActivity extends AppCompatActivity {

    private TextView profFName;
    private TextView profLName;
    private TextView profEmail;
    private TextView profPwd;
    private TextView profConfPwd;
    private TextView phNo;
    private FirebaseUser currentUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private Button logout;
    private Button saveProfile;
    private ImageView profImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profFName = findViewById(R.id.txtProfileFName);
        profLName = findViewById(R.id.txtProfileLName);
        profEmail = findViewById(R.id.txtProfileEmail);
        profPwd = findViewById(R.id.txtProfilePassword);
        profImg = findViewById(R.id.imgProfileAvatar);
        profConfPwd = findViewById(R.id.txtProfileConfirmPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        phNo = findViewById(R.id.txtProfilePhone);
        profEmail.setText("vivdan@gmail.com");
        profEmail.setFocusable(false);
        db = FirebaseFirestore.getInstance();
        logout = findViewById(R.id.btnLogout);
        saveProfile = findViewById(R.id.btnProfileSave);
        profImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent
                        = new Intent(ProfileActivity.this,
                        CameraMain.class);
                startActivity(intent);
            }
        });
        setImg();
        Task<QuerySnapshot> snapshots = db.collection("users").get();
        snapshots.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot document : task.getResult().getDocuments()){
                    if(document.toObject(User.class).getEmail().equals(currentUser.getEmail())) {
                        User you = document.toObject(User.class);
                        profFName.setText(you.getFirstName());
                        profLName.setText(you.getLastName());
                        profEmail.setText(you.getEmail());
                        phNo.setText(you.getPhone());
                    }
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentUser = null;
                firebaseAuth.signOut();
                Intent intent
                        = new Intent(ProfileActivity.this,
                        LoginAndSignUp.class);
                startActivity(intent);
            }
        });
        saveProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(profConfPwd.getText().toString().equals(profPwd.getText().toString()) && !profPwd.getText().toString().trim().equals("")) {
                    user.updatePassword(profPwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),
                                        "Password Updated",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Error password not updated",
                                        Toast.LENGTH_LONG).show();
                                Log.d("demo", "Error password not updated");
                            }
                        }
                    });
                }else if(!profConfPwd.getText().toString().equals(profPwd.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Password Do not match.",
                            Toast.LENGTH_LONG).show();
                }
                User you = new User(profFName.getText().toString(), profLName.getText().toString(),
                        profEmail.getText().toString(), phNo.getText().toString());
                db.collection("users").document(currentUser.getEmail()).set(you).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),
                                "Fields Updated",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setImg();
    }

    private void setImg(){
        storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("images/"+currentUser.getEmail());
        final long ONE_MEGABYTE = 1024 * 1024 * 1024;
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                profImg.setImageBitmap(bmp);
                profImg.setRotation(90);
            }
        });
    }
}