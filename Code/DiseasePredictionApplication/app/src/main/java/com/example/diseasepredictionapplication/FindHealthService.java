package com.example.diseasepredictionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FindHealthService extends AppCompatActivity {

    Button findHospitals, findGPSurgeries,
            findPharmacies,findOpticians,
            returnFindHealthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_health_service);

        // Find hospitals button to direct to a webpage showing nearby hospitals
        findHospitals = findViewById(R.id.findHospitals);
        findHospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the website, go from this class to HealthcareServiceWebsite class
                Intent intent = new Intent(FindHealthService.this, HealthcareServiceWebsite.class);
                // Pass the value of 0 to the HealthcareServiceWebsite activity
                intent.putExtra("findOption", 0);
                startActivity(intent);
            }
        });

        // Find GP surgeries button to direct to a webpage showing nearby gp surgeries
        findGPSurgeries = findViewById(R.id.findGP);
        findGPSurgeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the website, go from this class to HealthcareServiceWebsite class
                Intent intent = new Intent(FindHealthService.this, HealthcareServiceWebsite.class);
                // Pass the value of 1 to the HealthcareServiceWebsite activity
                intent.putExtra("findOption", 1);
                startActivity(intent);
            }
        });

        // Find pharmacies button to direct to a webpage showing nearby pharmacies
        findPharmacies = findViewById(R.id.findPharmacies);
        findPharmacies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the website, go from this class to HealthcareServiceWebsite class
                Intent intent = new Intent(FindHealthService.this, HealthcareServiceWebsite.class);
                // Pass the value of 2 to the HealthcareServiceWebsite activity
                intent.putExtra("findOption", 2);
                startActivity(intent);
            }
        });

        // Find opticians button to direct to a webpage showing nearby opticians
        findOpticians = findViewById(R.id.findOptician);
        findOpticians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the website, go from this class to HealthcareServiceWebsite class
                Intent intent = new Intent(FindHealthService.this, HealthcareServiceWebsite.class);
                // Pass the value of 3 to the HealthcareServiceWebsite activity
                intent.putExtra("findOption", 3);
                startActivity(intent);
            }
        });

        // Return button to return to main page
        returnFindHealthService = findViewById(R.id.returnFindHealthService);
        returnFindHealthService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}