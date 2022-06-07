package com.example.oladoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddProfilesActivity extends AppCompatActivity {
    private Button addProfRecBtn, btnBack;
    private TextInputEditText profileNameEdt, ageEdt, numEdt;
    private String profileID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profiles);





        addProfRecBtn = (Button)findViewById(R.id.idBtnProfRecord);
        btnBack = (Button)findViewById(R.id.back);
        profileNameEdt = (TextInputEditText) findViewById(R.id.idEdtProfileName);
        ageEdt = (TextInputEditText)findViewById(R.id.idEdtAge);
        numEdt = (TextInputEditText)findViewById(R.id.idEdtNum);
        profileID=profileNameEdt.getText().toString();



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ProfilesActivity.class);
                startActivity(i);
            }
        });



        addProfRecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProfileRec();


            }
        });


    }

    private void addProfileRec() {

        Map<String,Object> map= new HashMap<>();
        map.put("profileName",profileNameEdt.getText().toString());
        map.put("age",ageEdt.getText().toString());
        map.put("num",numEdt.getText().toString());
        map.put("Id",profileNameEdt.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("ProfileRecords").push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        profileNameEdt.setText("");
                        ageEdt.setText("");
                        numEdt.setText("");
                        Toast.makeText(AddProfilesActivity.this, "Record Added", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(AddProfilesActivity.this, "Record Could not Added", Toast.LENGTH_SHORT).show();


                    }
                });


    }
}