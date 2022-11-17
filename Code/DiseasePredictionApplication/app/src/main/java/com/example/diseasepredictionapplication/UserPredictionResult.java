package com.example.diseasepredictionapplication;

public class UserPredictionResult {

    // Variables representative of the data we want to store in the database

    //private String userID;
    private String diseasePrediction;
    private String dateOfPrediction;

    // Default constructor
    public UserPredictionResult() {
    }

    // Constructor
    public UserPredictionResult(String diseasePrediction, String dateOfPrediction) {
        this.diseasePrediction = diseasePrediction;
        this.dateOfPrediction = dateOfPrediction;
    }

    public String getDiseasePrediction() {
        return diseasePrediction;
    }

    public void setDiseasePrediction(String diseasePrediction) {
        this.diseasePrediction = diseasePrediction;
    }

    public String getDateOfPrediction() {
        return dateOfPrediction;
    }

    public void setDateOfPrediction(String dateOfPrediction) {
        this.dateOfPrediction = dateOfPrediction;
    }
}
