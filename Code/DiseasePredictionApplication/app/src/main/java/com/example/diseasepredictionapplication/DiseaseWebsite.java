package com.example.diseasepredictionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DiseaseWebsite extends AppCompatActivity {

    private WebView diseaseWebView;

    // An array of urls relating to the diseases that could be predicted
    private String[] diseaseUrls = {
            "https://www.nhs.uk/conditions/fungal-nail-infection/", // Fungal Infection
            "https://www.nhs.uk/conditions/allergies/", // Allergies
            "https://www.nhs.uk/conditions/heartburn-and-acid-reflux/", // GERD
            "https://www.healthline.com/health/cholestasis", // Chronic Cholestasis
            "https://cks.nice.org.uk/topics/adverse-drug-reactions", // Drug Reaction
            "https://www.nhs.uk/conditions/stomach-ulcer/", // Peptic Ulcer Disease
            "https://www.nhs.uk/conditions/hiv-and-aids/", // AIDS
            "https://www.nhs.uk/conditions/diabetes/", // Diabetes
            "https://www.bupa.co.uk/health-information/digestive-gut-health/gastroenteritis", // Gastroenteritis
            "https://www.nhs.uk/conditions/asthma/", // Bronchial Asthma
            "https://www.nhs.uk/conditions/high-blood-pressure-hypertension/", // Hypertension
            "https://www.nhs.uk/conditions/migraine/", // Migraine
            "https://www.nhs.uk/conditions/cervical-spondylosis/", // Cervival Spondylosis
            "https://www.bupa.co.uk/health-information/heart-blood-circulation/stroke", // Paralysis (Brain Hemorrhage)
            "https://www.nhs.uk/conditions/jaundice/", // Jaundice
            "https://www.nhs.uk/conditions/malaria/", // Malaria
            "https://www.nhs.uk/conditions/chickenpox/", // Chicken Pox
            "https://www.nhs.uk/conditions/dengue/", // Dengue
            "https://www.nhs.uk/conditions/typhoid-fever/", // Typhoid
            "https://www.nhs.uk/conditions/hepatitis-a/", // Hepatitis A
            "https://www.nhs.uk/conditions/hepatitis-b/", // Hepatitis B
            "https://www.nhs.uk/conditions/hepatitis-c/", // Hepatitis C
            "https://www.nhs.uk/conditions/hepatitis/", // Hepatitis D
            "https://britishlivertrust.org.uk/information-and-support/living-with-a-liver-condition/liver-conditions/hepatitis-e/", // Hepatitis E
            "https://www.hopkinsmedicine.org/health/conditions-and-diseases/hepatitis/alcoholic-hepatitis", // Alcoholic Hepatitis
            "https://www.nhs.uk/conditions/tuberculosis-tb/", // Tuberculosis
            "https://www.nhs.uk/conditions/common-cold/", // Common Cold
            "https://www.nhs.uk/conditions/pneumonia/", // Pneumonia
            "https://www.nhs.uk/conditions/piles-haemorrhoids/", // Dimorphic Heamorrhoids (Piles)
            "https://www.nhs.uk/conditions/heart-attack/", // Heart Attack
            "https://www.nhs.uk/conditions/varicose-veins/", // Varicose Veins
            "https://www.nhs.uk/conditions/underactive-thyroid-hypothyroidism/", //Hypothyroidism
            "https://www.nhs.uk/conditions/overactive-thyroid-hyperthyroidism/", // Hyperthyroidism
            "https://www.nhs.uk/conditions/low-blood-sugar-hypoglycaemia/", //Hypoglycemia
            "https://www.nhs.uk/conditions/osteoarthritis/", // Osteoarthritis
            "https://www.nhs.uk/conditions/arthritis/", // Arthritis
            "https://www.nhs.uk/conditions/vertigo/", // Paroxysmal Positional Vertigo (Vertigo)
            "https://www.nhs.uk/conditions/acne/", // Acne
            "https://www.nhs.uk/conditions/urinary-tract-infections-utis/", // Urinary Tract Infection
            "https://www.nhs.uk/conditions/psoriasis/", // Psoriasis
            "https://www.nhs.uk/conditions/impetigo/", // Impetigo
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_website);

        Intent intent = getIntent();

        if(intent != null) {
            // Acquire the int value of the predicted disease index passed from the PredictDisease class
            int diseaseWebsiteUrlIndex = intent.getIntExtra("predictedDiseaseIndex", 42);
            // Acquire the correct url for the disease using the diseaseURlS string array
            String diseaseUrl = diseaseUrls[diseaseWebsiteUrlIndex];
            // initialise the diseaseWebView object
            diseaseWebView = findViewById(R.id.diseaseWebView);
            // Open the url relating to the predicted disease
            diseaseWebView.loadUrl(diseaseUrl);
            // Open the webpage within the Disease Prediction Application
            diseaseWebView.setWebViewClient(new WebViewClient());
            // Enable the use of javascript on webpages
            diseaseWebView.getSettings().setJavaScriptEnabled(true);
        }
    }

    // Method that controls the behaviour of pressing back whilst in web browser client
    @Override
    public void onBackPressed(){
        // if the method returns true, allow the client to go back a webpage
        // Method .canGoBack() returns true if it is possible to go back a webpage
        if(diseaseWebView.canGoBack()){
            diseaseWebView.goBack();
        } else {
            // If there is no more webpages it can go back to, leave web client and return into
            // application
            super.onBackPressed();
        }
    }

}