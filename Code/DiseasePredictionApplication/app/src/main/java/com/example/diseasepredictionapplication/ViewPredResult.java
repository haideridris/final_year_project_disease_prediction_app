package com.example.diseasepredictionapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewPredResult extends AppCompatActivity {

    // Reference to database
    DatabaseReference databasePredictedResults;
    // ListView variable
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pred_result);

        listView = findViewById(R.id.resultsList);
        // Arraylist to store the results
        ArrayList<String> listResults = new ArrayList<>();
        // An adapter to present results on screen
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, listResults);
        // Set adapter
        listView.setAdapter(adapter);

        // Initialise database reference variable to the correct node
        // match the node to the current logged in users uid
        databasePredictedResults = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getUid());
        databasePredictedResults.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // clear arraylist before adding to it
                listResults.clear();
                // Iterate through results, convert them to strings and add to the adapter
                // to be outputted in app
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    UserPredictionResult upr = snapshot.getValue(UserPredictionResult.class);
                    String outputResult = upr.getDiseasePrediction() + " : " + upr.getDateOfPrediction();
                    listResults.add(outputResult);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Button to return to main landing page
        Button returnViewPred = findViewById(R.id.returnViewPred);
        returnViewPred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}