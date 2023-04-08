package com.example.nu_mad_sp2023_final_project_15.Display;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.nu_mad_sp2023_final_project_15.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.List;

public class DisplayPage extends AppCompatActivity {

    private ImageView imgDisplayPictures;
    private ImageView imgDisplayPrevious;
    private ImageView imgDisplayNext;
    private TextView txtDisplayTravelPlace;
    private TextView txtDisplayTravelDate;
    private TextView txtDisplayItinerary;
    private TextView txtDisplayCulture;
    private TextView txtDisplayTravelTips;
    private TextView txtDisplayReflections;
    private TextView txtDisplayLanguage;
    private TextView txtDisplayExpenses;
    private CardView cardItinerary;
    private CardView cardCulture;
    private CardView cardTravelTips;
    private CardView cardLanguage;
    private CardView cardReflections;
    private CardView cardExpenses;
    private int currentIndex;
    private String place;
    private String date;
    private String itinerary;
    private String culture;
    private String travelTips;
    private String reflections;
    private String language;
    private String expenses;
    private List<String> images;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_page);
        setTitle("Display Location Information");

        txtDisplayTravelPlace = findViewById(R.id.txtDisplayTravelPlace);
        txtDisplayTravelDate = findViewById(R.id.txtDisplayTravelDate);
        txtDisplayItinerary = findViewById(R.id.txtDisplayItinerary);
        txtDisplayCulture = findViewById(R.id.txtDisplayCulture);
        txtDisplayTravelTips = findViewById(R.id.txtDisplayTravelTips);
        txtDisplayReflections = findViewById(R.id.txtDisplayReflections);
        txtDisplayLanguage = findViewById(R.id.txtDisplayLanguage);
        txtDisplayExpenses = findViewById(R.id.txtDisplayExpenses);
        imgDisplayPictures = findViewById(R.id.imgDisplayPictures);
        imgDisplayPrevious = findViewById(R.id.imgDisplayPrevious);
        imgDisplayNext = findViewById(R.id.imgDisplayNext);
        cardItinerary = findViewById(R.id.cardItinerary);
        cardCulture = findViewById(R.id.cardCulture);
        cardTravelTips = findViewById(R.id.cardTravelTips);
        cardLanguage = findViewById(R.id.cardLanguage);
        cardReflections = findViewById(R.id.cardReflections);
        cardExpenses = findViewById(R.id.cardExpenses);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        LatLng LatLng = getIntent().getParcelableExtra("LatLng");
        String LatLngString = LatLng.latitude + "," + LatLng.longitude;

        db.collection(currentUser.getEmail()).document(LatLngString).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        place = documentSnapshot.getString("trPlace");
                        date = documentSnapshot.getString("trDate");
                        itinerary = documentSnapshot.getString("trItinerary");
                        culture = documentSnapshot.getString("trCulture");
                        travelTips = documentSnapshot.getString("trTips");
                        reflections = documentSnapshot.getString("trReflection");
                        language = documentSnapshot.getString("trLanguage");
                        expenses = documentSnapshot.getString("trExpense");
                        images = (List<String>) documentSnapshot.get("trImageUrl");

                        txtDisplayTravelPlace.setText(place);
                        txtDisplayTravelDate.setText(date);
                        txtDisplayItinerary.setText(itinerary);
                        txtDisplayCulture.setText(culture);
                        txtDisplayTravelTips.setText(travelTips);
                        txtDisplayReflections.setText(reflections);
                        txtDisplayLanguage.setText(language);
                        txtDisplayExpenses.setText(expenses);

                        if (images != null && !images.isEmpty()) {
                            currentIndex = 0;
                            displayImage();
                            if (images.size() > 1) {
                                enableButtons();
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error retrieving Travel Information", Toast.LENGTH_LONG).show();
                    }
                });


        imgDisplayPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex - 1 + images.size()) % images.size();
                displayImage();
                enableButtons();
            }
        });

        imgDisplayNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % images.size();
                displayImage();
                enableButtons();
            }
        });

        cardItinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayPage.this, DisplayItineraryActivity.class);
                intent.putExtra("itinerary", itinerary);
                startActivity(intent);
            }
        });

        cardCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayPage.this, DisplayCultureActivity.class);
                intent.putExtra("culture", culture);
                startActivity(intent);
            }
        });

        cardLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayPage.this, DisplayLanguageActivity.class);
                intent.putExtra("language", language);
                startActivity(intent);
            }
        });

        cardTravelTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayPage.this, DisplayTipsActivity.class);
                intent.putExtra("travelTips", travelTips);
                startActivity(intent);
            }
        });

        cardReflections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayPage.this, DisplayReflectionActivity.class);
                intent.putExtra("reflections", reflections);
                startActivity(intent);
            }
        });

        cardExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayPage.this, DisplayExpensesActivity.class);
                intent.putExtra("expenses", expenses);
                startActivity(intent);
            }
        });

    }

    private void displayImage() {

        String imgUrl = images.get(currentIndex);
        disableButtons();
        Glide.with(imgDisplayPictures.getContext()).load(imgUrl).into(imgDisplayPictures);

    }

    private void enableButtons() {
        imgDisplayPrevious.setEnabled(true);
        imgDisplayNext.setEnabled(true);
        imgDisplayPrevious.setVisibility(View.VISIBLE);
        imgDisplayNext.setVisibility(View.VISIBLE);
    }

    private void disableButtons() {
        imgDisplayPrevious.setEnabled(false);
        imgDisplayNext.setEnabled(false);
        imgDisplayPrevious.setVisibility(View.INVISIBLE);
        imgDisplayNext.setVisibility(View.INVISIBLE);
    }

}