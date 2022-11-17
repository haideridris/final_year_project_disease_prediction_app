package com.example.diseasepredictionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HealthcareServiceWebsite extends AppCompatActivity {

    private WebView healthcareServiceWebView;

    // An array of urls relating to nearby healthcare services using google map webpages
    private String[] mapsUrls = {
            "https://www.google.com/maps/search/hospital/", // Hospitals
            "https://www.google.com/maps/search/GP/", // GP Surgeries
            "https://www.google.com/maps/search/pharmacy/", // Pharmacies
            "https://www.google.com/maps/search/opticians/", // Opticians
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthcare_service_website);

        Intent intent = getIntent();

        if (intent != null) {
            // Acquire the int value of the button clicked from FindHealthService
            // The webpage displayed to the user is dependent on the button clicked
            int mapsUrlIndex = intent.getIntExtra("findOption", 0);
            // Acquire the correct url for the chose healthcare service
            // using the mapsUrls string array
            String nearbyHealthServiceUrl = mapsUrls[mapsUrlIndex];
            // initialise the healthcareServiceWebView object
            healthcareServiceWebView = findViewById(R.id.healthcareWebView);
            // Open the url relating to the selected health care service
            healthcareServiceWebView.loadUrl(nearbyHealthServiceUrl);
            // Open the webpage within the Disease Prediction Application
            healthcareServiceWebView.setWebViewClient(new WebViewClient());
            // Enable the use of javascript on webpages
            healthcareServiceWebView.getSettings().setJavaScriptEnabled(true);
        }
    }

    // Method that controls the behaviour of pressing back whilst in web browser client
    @Override
    public void onBackPressed(){
        // if the method returns true, allow the client to go back a webpage
        // Method .canGoBack() returns true if it is possible to go back a webpage
        if(healthcareServiceWebView.canGoBack()){
            healthcareServiceWebView.goBack();
        } else {
            // If there is no more webpages it can go back to, leave web client and return into
            // application
            super.onBackPressed();
        }
    }
}