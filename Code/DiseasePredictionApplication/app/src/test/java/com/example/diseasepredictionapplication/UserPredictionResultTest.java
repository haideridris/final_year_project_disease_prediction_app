package com.example.diseasepredictionapplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserPredictionResultTest {

    // Default Constructor
    public UserPredictionResultTest(){
    }

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }

    @Test
    public void testGetDiseasePrediction() {
        UserPredictionResult test = new UserPredictionResult();
        test.setDiseasePrediction("Fungal Infection");
        String expectResult = "Fungal Infection";
        String result = test.getDiseasePrediction();
        assertEquals(expectResult, result);
    }

    @Test
    public void testSetDiseasePrediction() {
        String disease = "Fungal Infection";
        UserPredictionResult test = new UserPredictionResult();
        test.setDiseasePrediction(disease);
        assertEquals(disease, test.getDiseasePrediction());
    }

    @Test
    public void testGetDateOfPrediction() {
        // Set the prediction date using current date and time
        SimpleDateFormat dateFormatting = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String expectedCurrentDate = dateFormatting.format(calendar.getTime());
        UserPredictionResult test = new UserPredictionResult();
        test.setDateOfPrediction(expectedCurrentDate);
        String result = test.getDateOfPrediction();
        assertEquals(expectedCurrentDate, result);
    }

    @Test
    public void testSetDateOfPrediction() {
        SimpleDateFormat dateFormatting = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String currentDate = dateFormatting.format(calendar.getTime());
        UserPredictionResult test = new UserPredictionResult();
        test.setDateOfPrediction(currentDate);
        assertEquals(currentDate, test.getDateOfPrediction());
    }

}
