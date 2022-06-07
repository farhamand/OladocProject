package com.example.oladoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditMedicalRecordsActivity extends AppCompatActivity {

    private TextInputEditText patientNameEdt, presEdt, dateEdt, docNameEdt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MedicalRVModal medicalRVModal;
    private ProgressBar loadingPB;
    private String patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medical_records);


        Button addMedRecBtn = findViewById(R.id.idBtnAddMedicalRecord);
        patientNameEdt = findViewById(R.id.idEdtPatientName);
        presEdt = findViewById(R.id.idEdtPres);
        dateEdt = findViewById(R.id.idEdtDate);
        docNameEdt = findViewById(R.id.idEdtDocName);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // on below line we are getting our modal class on which we have passed.
        medicalRVModal = getIntent().getParcelableExtra("medicalRecords");
        Button deleteMedicalRecordsBtn = findViewById(R.id.idBtnDeleteMedicalRecord);

        if (medicalRVModal != null) {
            // on below line we are setting data to our edit text from our modal class.
            patientNameEdt.setText(medicalRVModal.getPatientName());
            dateEdt.setText(medicalRVModal.getDate());
            docNameEdt.setText(medicalRVModal.getDocName());
            presEdt.setText(medicalRVModal.getPrescription());
            patientID = medicalRVModal.getPatientId();
        }

        // on below line we are initialing our database reference and we are adding a child as our course id.
        databaseReference = firebaseDatabase.getReference("MedicalRecords").child(patientID);
        // on below line we are adding click listener for our add course button.
        addMedRecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are making our progress bar as visible.
                loadingPB.setVisibility(View.VISIBLE);
                // on below line we are getting data from our edit text.
                String patientName = patientNameEdt.getText().toString();
                String pres = presEdt.getText().toString();
                String date = dateEdt.getText().toString();
                String docName = docNameEdt.getText().toString();
                // on below line we are creating a map for
                // passing a data using key and value pair.
                Map<String, Object> map = new HashMap<>();
                map.put("patientName", patientName);
                map.put("prescription", pres);
                map.put("date", date);
                map.put("docName", docName);
                map.put("patientId", patientID);

                // on below line we are calling a database reference on
                // add value event listener and on data change method
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // making progress bar visibility as gone.
                        loadingPB.setVisibility(View.GONE);
                        // adding a map to our database.
                        databaseReference.updateChildren(map);
                        // on below line we are displaying a toast message.
                        Toast.makeText(EditMedicalRecordsActivity.this, "Records Updated..", Toast.LENGTH_SHORT).show();
                        // opening a new activity after updating our coarse.
                        startActivity(new Intent(EditMedicalRecordsActivity.this, MedicalRecordsActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // displaying a failure message on toast.
                        Toast.makeText(EditMedicalRecordsActivity.this, "Fail to update records..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // adding a click listener for our delete course button.
        deleteMedicalRecordsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete a course.
                deleteMedRec();
            }
        });

    }

    private void deleteMedRec() {
        // on below line calling a method to delete the course.
        databaseReference.removeValue();
        // displaying a toast message on below line.
        Toast.makeText(this, "Record Deleted..", Toast.LENGTH_SHORT).show();
        // opening a main activity on below line.
        startActivity(new Intent(EditMedicalRecordsActivity.this, MedicalRecordsActivity.class));


    }
}