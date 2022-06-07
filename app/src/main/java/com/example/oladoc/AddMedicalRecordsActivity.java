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

public class AddMedicalRecordsActivity extends AppCompatActivity {
    private Button addMedRecBtn , btnBack;
    private TextInputEditText patientNameEdt, presEdt, dateEdt, docNameEdt;
    private String patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medical_records);

        addMedRecBtn = (Button)findViewById(R.id.idBtnAddMedicalRecord);
        btnBack = (Button)findViewById(R.id.back);
        patientNameEdt = (TextInputEditText) findViewById(R.id.idEdtPatientName);
        presEdt = (TextInputEditText)findViewById(R.id.idEdtPres);
        dateEdt = (TextInputEditText)findViewById(R.id.idEdtDate);
        docNameEdt = (TextInputEditText)findViewById(R.id.idEdtDocName);
        patientID=patientNameEdt.getText().toString();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MedicalRecordsActivity.class);
                startActivity(i);
            }
        });



        addMedRecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMedRec();


            }
        });


    }

    private void addMedRec() {

        Map<String,Object> map= new HashMap<>();
        map.put("patientName",patientNameEdt.getText().toString());
        map.put("date",dateEdt.getText().toString());
        map.put("docName",docNameEdt.getText().toString());
        map.put("prescription",presEdt.getText().toString());
        map.put("Id",patientNameEdt.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("MedicalRecords").push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        patientNameEdt.setText("");
                        dateEdt.setText("");
                        docNameEdt.setText("");
                        presEdt.setText("");
                        Toast.makeText(AddMedicalRecordsActivity.this, "Record Added", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(AddMedicalRecordsActivity.this, "Record Could not Added", Toast.LENGTH_SHORT).show();


                    }
                });


    }
}