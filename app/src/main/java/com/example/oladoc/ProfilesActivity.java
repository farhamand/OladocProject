package com.example.oladoc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class ProfilesActivity extends AppCompatActivity {
    RecyclerView recView;
    ProfilesRVAdapter adapter;
    FloatingActionButton addProfRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        recView=(RecyclerView)findViewById(R.id.idRVProfRec);
        recView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ProfilesRVModal> options =
                new FirebaseRecyclerOptions.Builder<ProfilesRVModal>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ProfileRecords"), ProfilesRVModal.class)
                        .build();

        adapter= new ProfilesRVAdapter(options);
        recView.setAdapter(adapter);

        addProfRec=findViewById(R.id.idFabAddProfRec);

        addProfRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddProfilesActivity.class);
                startActivity(intent);
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

            Toast.makeText(ProfilesActivity.this, "You Clicked ", Toast.LENGTH_SHORT).show();

        }
    }
}