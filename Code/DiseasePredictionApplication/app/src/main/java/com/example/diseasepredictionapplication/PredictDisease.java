package com.example.diseasepredictionapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class PredictDisease extends AppCompatActivity {

    // Symptom list
    private String[] symptomsList = {"No symptom","Itching", "Skin rash", "Nodal skin eruptions", "Continuous sneezing",
    "Shivering", "Chills", "Joint pain", "Stomach pain", "Acidity", "Ulcers on tongue", "Muscle wasting",
    "Vomiting", "Burning micturition", "Spotting urination", "Fatigue", "Weight gain", "Anxiety",
    "Cold hands and feet", "Mood swings", "Weight loss", "Restlessness", "Lethargy", "Patches in throat",
    "Irregular sugar level", "Cough", "High fever", "Sunken eyes", "Breathlessness", "Sweating",
    "Dehydration", "Indigestion", "Headache", "Yellowish skin", "Dark urine", "Nausea", "Loss of appetite",
    "Pain behind the eyes", "Back pain", "Constipation", "Abdominal pain", "Diarrhoea", "Mild fever",
    "Yellow urine", "Yellowing of eyes", "Acute liver failure", "Fluid overload", "Swelling of stomach",
    "Swelled lymph nodes", "Malaise", "Blurred and distorted vision", "Phlegm", "Throat irritation",
    "Redness of eyes", "Sinus pressure", "Runny nose", "Congestion", "Chest pain", "Weakness in limbs",
    "Fast heart rate", "Pain during bowel movements", "Pain in anal region", "Bloody stool",
    "Irritation in anus", "Neck pain", "Dizziness", "Cramps", "Bruising", "Obesity", "Swollen legs",
    "Swollen blood vessels", "Puffy face and eyes", "Enlarged thyroid", "Brittle nails",
    "Swollen extremities", "Excessive hunger", "Extra marital contacts", "Drying and tingling lips",
    "Slurred speech", "Knee pain", "Hip joint pain", "Muscle weakness", "Stiff neck",
    "Swelling joints", "Movement stiffness", "Spinning movements", "Loss of balance", "Unsteadiness",
    "Weakness of one body side", "Loss of smell", "Bladder discomfort", "Foul smell of urine",
    "Continuous feel of urine", "Passage of gases", "Internal itching", "Toxic look (Typhus)",
    "Depression", "Irritability", "Muscle pain", "Altered sensorium", "Red spots over body",
    "Belly pain", "Abnormal menstruation", "Dischromic patches", "Watering from eyes",
    "Increased appetite", "Polyuria (Excessive Urination)", "Family history", "Mucoid sputum", "Rusty sputum",
    "Lack of concentration", "Visual disturbances", "Receiving blood transfusion",
    "Receiving unsterile injections", "Coma", "Stomach bleeding", "Distention of abdomen",
    "History of alcohol consumption", "Fluid overload", "Blood in sputum", "Prominent veins on calf",
    "Palpitations", "Painful walking", "Pus filled pimples", "Blackheads", "Scurring", "Skin peeling",
    "Silver like dusting", "Small dents in nails", "Inflammatory nails", "Blister", "Red sore around nose",
    "Yellow crust ooze"};

    // Diseases labels
    private String[] diseaseLabels = {"Fungal Infection", "Allergy", "GERD", "Chronic Cholestasis",
    "Drug Reaction", "Peptic Ulcer Disease", "AIDS", "Diabetes", "Gastroenteritis", "Bronchial Asthma",
    "Hypertension", "Migraine", "Cervical Spondylosis", "Paralysis (Brain Hemorrhage)", "Jaundice",
    "Malaria", "Chicken Pox", "Dengue", "Typhoid", "Hepatitis A", "Hepatitis B", "Hepatitis C",
    "Hepatitis D", "Hepatitis E", "Alcoholic Hepatitis", "Tuberculosis", "Common Cold", "Pneumonia",
    "Dimorphic Heammorhoids (Piles)", "Heart Attack", "Varicose Veins", "Hypothyroidism",
    "Hyperthyroidism", "Hypoglycemia", "Osteoarthritis", "Arthritis", "Paroxysmal Positional Vertigo (Vertigo)",
    "Acne", "Urinary Tract Infection", "Psoriasis", "Impetigo"};

    // Interpreter object using Interpreter class from Java API for TensorFlow Lite inference
    Interpreter interpreter;

    // Boolean variable to indicate if a disease has been predicted or not
    private boolean diseaseHasBeenPredicted = false;

    // Result index variable
    private int resultIndexForDiseaseLabels;

    // Result string for predicted disease
    private String predictedDisease;

    // Database reference
    DatabaseReference databasePredictedResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict_disease);

        // Initialise this value to get reference to the database
        databasePredictedResults = FirebaseDatabase.getInstance().getReference();

        try {
            // Load the model to the interpreter
            interpreter = new Interpreter(loadModelFile());
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Create drop down menus for symptom input
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, symptomsList);
        Spinner symptomOne = findViewById(R.id.symptomSpinner1);
        symptomOne.setAdapter(arrayAdapter);
        Spinner symptomTwo = findViewById(R.id.symptomSpinner2);
        symptomTwo.setAdapter(arrayAdapter);
        Spinner symptomThree = findViewById(R.id.symptomSpinner3);
        symptomThree.setAdapter(arrayAdapter);
        Spinner symptomFour = findViewById(R.id.symptomSpinner4);
        symptomFour.setAdapter(arrayAdapter);
        Spinner symptomFive = findViewById(R.id.symptomSpinner5);
        symptomFive.setAdapter(arrayAdapter);
        Spinner symptomSix = findViewById(R.id.symptomSpinner6);
        symptomSix.setAdapter(arrayAdapter);
        Spinner symptomSeven = findViewById(R.id.symptomSpinner7);
        symptomSeven.setAdapter(arrayAdapter);
        Spinner symptomEight = findViewById(R.id.symptomSpinner8);
        symptomEight.setAdapter(arrayAdapter);
        Spinner symptomNine = findViewById(R.id.symptomSpinner9);
        symptomNine.setAdapter(arrayAdapter);
        Spinner symptomTen = findViewById(R.id.symptomSpinner10);
        symptomTen.setAdapter(arrayAdapter);
        Spinner symptomEleven = findViewById(R.id.symptomSpinner11);
        symptomEleven.setAdapter(arrayAdapter);
        Spinner symptomTwelve = findViewById(R.id.symptomSpinner12);
        symptomTwelve.setAdapter(arrayAdapter);
        Spinner symptomThirteen = findViewById(R.id.symptomSpinner13);
        symptomThirteen.setAdapter(arrayAdapter);
        Spinner symptomFourteen = findViewById(R.id.symptomSpinner14);
        symptomFourteen.setAdapter(arrayAdapter);
        Spinner symptomFifteen = findViewById(R.id.symptomSpinner15);
        symptomFifteen.setAdapter(arrayAdapter);
        Spinner symptomSixteen = findViewById(R.id.symptomSpinner16);
        symptomSixteen.setAdapter(arrayAdapter);
        Spinner symptomSeventeen = findViewById(R.id.symptomSpinner17);
        symptomSeventeen.setAdapter(arrayAdapter);

        TextView diseaseResult = findViewById(R.id.diseaseRes);
        Button predictDiseaseButton = findViewById(R.id.predictDiseaseBtn);
        // predict disease once button is clicked
        predictDiseaseButton.setOnClickListener((v) -> {
            // array to hold the values of user symptom input
            // The position of the item in the drop down (minus 1) corresponds
            // to the symptom in the symptom list
            int[] symptomInputs = new int[17];
            symptomInputs[0] =  symptomOne.getSelectedItemPosition();
            symptomInputs[1] =  symptomTwo.getSelectedItemPosition();
            symptomInputs[2] =  symptomThree.getSelectedItemPosition();
            symptomInputs[3] =  symptomFour.getSelectedItemPosition();
            symptomInputs[4] =  symptomFive.getSelectedItemPosition();
            symptomInputs[5] =  symptomSix.getSelectedItemPosition();
            symptomInputs[6] =  symptomSeven.getSelectedItemPosition();
            symptomInputs[7] =  symptomEight.getSelectedItemPosition();
            symptomInputs[8] =  symptomNine.getSelectedItemPosition();
            symptomInputs[9] =  symptomTen.getSelectedItemPosition();
            symptomInputs[10] =  symptomEleven.getSelectedItemPosition();
            symptomInputs[11] =  symptomTwelve.getSelectedItemPosition();
            symptomInputs[12] =  symptomThirteen.getSelectedItemPosition();
            symptomInputs[13] =  symptomFourteen.getSelectedItemPosition();
            symptomInputs[14] =  symptomFifteen.getSelectedItemPosition();
            symptomInputs[15] =  symptomSixteen.getSelectedItemPosition();
            symptomInputs[16] =  symptomSeventeen.getSelectedItemPosition();

            // 2D input array for the model with size 1 * 132
            // Each of the 132 is representative of a symptom
            float[][] modelInputArray = new float[1][132];

            // Assure disease prediction only occurs if user enters 4 symptoms
            // Check to see if any or all of first 4 user inputs are No symptom (i.e. value of 0)
            // Display toast and error message to the user if true otherwise predict disease
            if (symptomInputs[0] == 0 || symptomInputs[1] == 0|| symptomInputs[2] == 0 || symptomInputs[3] == 0){
                // Display a Toast (error message to the user) tell them they must select 4 symptoms
                Toast.makeText(PredictDisease.this, "You must enter four initial symptoms to predict your disease!", Toast.LENGTH_SHORT).show();
            } else {
                for (int i = 0; i < symptomInputs.length; i++) {
                    // If the value is not 0 (no symptom), indicate the presence of the symptom
                    // In the modelInputArray by assigning a value of 1 in the corresponding element
                    // Note, minus 1 to value as we added an extra drop down option of
                    // No symptom to drop down menu
                    if (symptomInputs[i] != 0) {
                        int value = symptomInputs[i];
                        int symptomPresentIndex = value - 1;
                        modelInputArray[0][symptomPresentIndex] = 1;
                    }
                }
                // Call the predictDisease method, passing the modelInputArray as an argument
                // and assign it the result to the modelOutputArray
                float[][] modelOutputArray = predictDisease(modelInputArray);
                // get index of element that is predicted to most likely be the disease
                resultIndexForDiseaseLabels = findMaxIndex(modelOutputArray);
                // use index to provide verbose output to the user
                predictedDisease = diseaseLabels[resultIndexForDiseaseLabels];
                diseaseResult.setText("You are likely to have: " + predictedDisease);
                // Assign the value of the boolean that denotes if a disease has been predicted or not as true
                diseaseHasBeenPredicted = true;
            }
        });

        // Return button to main landing page
        Button returnPred = findViewById(R.id.returnPred);
        returnPred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        // View more button to direct to webpage about the disease predicted
        Button viewDiseaseInfo = findViewById(R.id.viewInfoBtn);
        viewDiseaseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!diseaseHasBeenPredicted){
                    // Display a Toast (error message to the user) to tell them they must predict a disease first to
                    // get further information about it
                    Toast.makeText(PredictDisease.this, "You haven't acquired a disease prediction!", Toast.LENGTH_SHORT).show();
                } else {
                    // Show the website, go from this class to DiseaseWebsite class
                    Intent intent = new Intent(PredictDisease.this, DiseaseWebsite.class);
                    // Pass the resultIndexForDiseaseLabel variable to the DiseaseWebsite activity
                    intent.putExtra("predictedDiseaseIndex", resultIndexForDiseaseLabels);
                    startActivity(intent);
                }
            }
        });

        // Save result button to save predicted result to Firebase realtime database
        Button savePredictedResult = findViewById(R.id.saveResultBtn);
        savePredictedResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!diseaseHasBeenPredicted){
                    // Display a Toast (error message to the user) to tell them they must predict a disease first to
                    // save it
                    Toast.makeText(PredictDisease.this, "You haven't acquired a disease prediction!", Toast.LENGTH_SHORT).show();
                } else {
                    //Method called to save the users predicted result to the firebase db
                    saveResult();
                    // Display a Toast (message to the user) to tell them their result has been saved
                    // and they can view it from the view predicted results page
                    Toast.makeText(PredictDisease.this, "Your disease prediction result has been saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to save the user's predicted result and the date/time they acquired this prediction
    public void saveResult(){
        // Get current date and time and assign it to the string predictionDate
        SimpleDateFormat dateFormatting = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String predictionDate = dateFormatting.format(calendar.getTime());
        // Create a child node in the database which has a key that matches the string of
        // the logged in Uid
        // Randomly generate a key for inner branch and give this branch
        // the prediction result and prediction date variables
        HashMap<String , Object > data  = new HashMap<>();
        data.put("diseasePrediction", predictedDisease);
        data.put("dateOfPrediction" , predictionDate);
        databasePredictedResults.child(FirebaseAuth.getInstance().getUid()).push().updateChildren(data);
    }

    //Method that takes user input and returns the output of prediction
    //from the model
    private float[][] predictDisease(float[][] inputArray){
        // 2D output array that is 1 * 41 for the 41 categories (diseases) of classification
        float[][] output = new float[1][41];
        // Call to TensorflowLite API class to run inference on model
        // input array passed to the run method and the result will be stored in the output array
        interpreter.run(inputArray, output);
        // return output array
        // output array, holds 41 elements, each element has a float value representing
        // how likely the model thinks the input is that disease
        // each element corresponds to a disease labels
        return output;
    }

    // Method that mimics the behaviour of np.argMax() of numpy package in my python file
    // Return the index of output array element that has the highest value predicted
    private int findMaxIndex(float[][] array){
        int index = 0;
        float maxValue = 0;
        float[][] checkArray = array;
        for (int i = 0; i < checkArray.length; i++) {
            for (int j = 0; j < checkArray[i].length; j++) {
                if(maxValue<array[i][j]){
                    maxValue = array[i][j];
                    index = j;
                }
            }
        }
        // Since it's a 1 x 41 array, only need to return the index value of the 2nd dimension
        return index;
    }

    /** A utility method used to load the model to use it for inference
     *
     * Utilised source code from a video tutorial posted by the Youtube channel
     * Machine Mobile Learning on Youtube at time stamp 11:46:
     * https://www.youtube.com/watch?v=63bCmY5uRto
     *
     * @return MappedByteBuffer
     * @throws IOException
     */
    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor assetFileDescriptor = this.getAssets().openFd("diseasepredictionmodel.tflite");
        FileInputStream fileInputStream = new FileInputStream(assetFileDescriptor.getFileDescriptor());
        FileChannel fileChannel = fileInputStream.getChannel();
        long startOffset = assetFileDescriptor.getStartOffset();
        long length = assetFileDescriptor.getLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, length);
    }
}