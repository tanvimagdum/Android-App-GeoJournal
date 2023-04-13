package com.example.nu_mad_sp2023_final_project_15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginAndSignUp extends AppCompatActivity {

    private Button signUp;
    private Button login;
    private TextView firstName;
    private TextView lastName;
    private TextView displayName;
    private TextView signUpEmail;
    private TextView signUpPassword;
    private TextView confirmPassword;
    private TextView email;
    private TextView password;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private FirebaseFirestore db;
    private TextView signUpPhno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_sign_up);
        setTitle("Login/Sign Up");
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        signUp = findViewById(R.id.btnSignUp);
        signUpPhno = findViewById(R.id.txtSignupPhno);
        login = findViewById(R.id.btnLogin);
        firstName = findViewById(R.id.txtSignUpFName);
        lastName = findViewById(R.id.txtSignUpLName);
        displayName = findViewById(R.id.txtSignUpDisplayName);
        signUpEmail = findViewById(R.id.txtSignUpEmail);
        signUpPassword = findViewById(R.id.txtSignUpPassword);
        email = findViewById(R.id.txtLoginEmail);
        password = findViewById(R.id.txtLoginPassword);
        confirmPassword = findViewById(R.id.txtSignUpConfirmPassword);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(signUpEmail.getText().toString().trim().equals("") ||
                        signUpPassword.getText().toString().trim().equals("") ||
                        confirmPassword.getText().toString().trim().equals("") ||
                        displayName.getText().toString().trim().equals("") ||
                        firstName.getText().toString().trim().equals("") ||
                        lastName.getText().toString().trim().equals("")){
                    Toast.makeText(
                                    getApplicationContext(),
                                    "Please enter the input fields first",
                                    Toast.LENGTH_LONG)
                            .show();
                }else{
                    if(signUpPassword.getText().toString().trim().equals(confirmPassword.getText().toString())) {
                        createNewAccount(signUpEmail.getText().toString(), signUpPassword.getText().toString(),
                                displayName.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), signUpPhno.getText().toString());
                    }else{
                        Toast.makeText(
                                        getApplicationContext(),
                                        "Passwords do not match",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")){
                    Toast.makeText(
                                    getApplicationContext(),
                                    "Please enter the input fields first",
                                    Toast.LENGTH_LONG)
                            .show();
                }else{
                    login(email.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent
                    = new Intent(LoginAndSignUp.this,
                    LandingPage.class);
            startActivity(intent);
        }
    }

    public void createNewAccount(String email, String password, String displayName, String firstName, String lastName, String phno){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            currentUser = task.getResult().getUser();
                            UserProfileChangeRequest upcr = new UserProfileChangeRequest.Builder().setDisplayName(displayName).build();
                            currentUser.updateProfile(upcr)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(getApplicationContext(),
                                                                "Display Name is set!",
                                                                Toast.LENGTH_LONG)
                                                        .show();
                                            }
                                        }
                                    });
                            User user = new User(firstName, lastName, email, phno);
                            db.collection("users").document(currentUser.getEmail()).set(user);
                            Toast.makeText(getApplicationContext(),
                                            "Registration successful!",
                                            Toast.LENGTH_LONG)
                                    .show();
                            Intent intent
                                    = new Intent(LoginAndSignUp.this,
                                    LandingPage.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "Registration failed!!"
                                            + " Please try again later",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void login(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                            .show();
                                    Intent intent
                                            = new Intent(LoginAndSignUp.this,
                                            LandingPage.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),
                                                    "Login failed!!",
                                                    Toast.LENGTH_LONG)
                                            .show();
                                }
                            }
                        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}