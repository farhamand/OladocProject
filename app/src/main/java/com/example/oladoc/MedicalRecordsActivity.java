package com.example.oladoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.FirebaseDatabase;

public class MedicalRecordsActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    MedicalRVAdapter adapter;
    FloatingActionButton addMedRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records);

        bottomNavigationView =findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.appointments);
        recyclerView=(RecyclerView)findViewById(R.id.idRVMedRec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<MedicalRVModal> options =
                new FirebaseRecyclerOptions.Builder<MedicalRVModal>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("MedicalRecords"), MedicalRVModal.class)
                        .build();

        adapter= new MedicalRVAdapter(options);
        recyclerView.setAdapter(adapter);

        addMedRec=findViewById(R.id.idFabAddMedRec);

        addMedRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddMedicalRecordsActivity.class);
                startActivity(intent);
            }
        });




        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){


                    case R.id.appointments:
                        startActivity(new Intent(getApplicationContext(), AppointmentsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.medicalrecords:
                        return true;

                }



                return false;
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(MedicalRecordsActivity.this, "You Clicked ", Toast.LENGTH_SHORT).show();

        }
    }




}