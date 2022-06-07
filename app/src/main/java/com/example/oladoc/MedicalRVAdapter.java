package com.example.oladoc;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MedicalRVAdapter extends FirebaseRecyclerAdapter<MedicalRVModal,MedicalRVAdapter.myviewholder>{




    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MedicalRVAdapter(@NonNull FirebaseRecyclerOptions<MedicalRVModal> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, final int position, @NonNull MedicalRVModal model)
    {

        holder.pnane.setText(model.getPatientName());
        holder.date.setText(model.getDate());
        holder.docname.setText(model.getDocName());
        holder.pres.setText(model.getPrescription());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogplus= DialogPlus.newDialog(holder.pnane.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview = dialogplus.getHolderView();
                EditText date=myview.findViewById(R.id.idTVDate);
                EditText pname=myview.findViewById(R.id.idTVPatientName);
                EditText docName=myview.findViewById(R.id.idTVDocName);
                EditText pres=myview.findViewById(R.id.idTVPres);
                Button update= myview.findViewById(R.id.UpdateMedRec);

                date.setText(model.getDate());
                pname.setText(model.getPatientName());
                docName.setText(model.getDocName());
                pres.setText(model.getPrescription());

                dialogplus.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map=new HashMap<>();
                        map.put("patientName",pname.getText().toString());
                        map.put("date",date.getText().toString());
                        map.put("docName",docName.getText().toString());
                        map.put("prescription",pres.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("MedicalRecords")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        dialogplus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogplus.dismiss();
                                    }
                                });

                    }
                });



            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.pnane.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("MedicalRecords")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_rv_item,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder{

        TextView pnane,pres,date,docname;
        Button btnEdit, btnAdd, btnDelete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            pnane=(TextView)itemView.findViewById(R.id.idTVPatientName);
            pres=(TextView)itemView.findViewById(R.id.idTVPres);
            date=(TextView)itemView.findViewById(R.id.idTVDate);
            docname=(TextView)itemView.findViewById(R.id.idTVDocName);


            btnEdit = (Button)itemView.findViewById(R.id.idBtnEditMedicalRecord);
            btnDelete= (Button)itemView.findViewById(R.id.idBtnDeleteMedicalRecord);
            btnAdd= (Button)itemView.findViewById(R.id.idFabAddMedRec);




        }
    }
}
