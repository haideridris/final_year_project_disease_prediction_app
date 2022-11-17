package com.example.diseasepredictionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button logout, predictDisease,viewPredResults, findHealthServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        predictDisease = findViewById(R.id.goToPredPgBtn);
        predictDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Taken to Predict Disease page
                startActivity(new Intent(getApplicationContext(), PredictDisease.class));
                finish();
            }
        });
        viewPredResults = findViewById(R.id.viewPredResultsBtn);
        viewPredResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Taken to View Prediction results page
                startActivity(new Intent(getApplicationContext(), ViewPredResult.class));
                finish();
            }
        });
        findHealthServices = findViewById(R.id.findHealthServiceBtn);
        findHealthServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Taken to Find Healths service page
                startActivity(new Intent(getApplicationContext(), FindHealthService.class));
                finish();
            }
        });
    }
}