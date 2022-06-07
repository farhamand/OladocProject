package com.example.oladoc;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class ProfilesRVAdapter extends FirebaseRecyclerAdapter<ProfilesRVModal,ProfilesRVAdapter.myviewholder> {




    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProfilesRVAdapter(@NonNull FirebaseRecyclerOptions<ProfilesRVModal> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull ProfilesRVModal model)
    {

        holder.profileName.setText(model.getProfileName());
        holder.age.setText(model.getAge());
        holder.num.setText(model.getNum());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogplus= DialogPlus.newDialog(holder.profileName.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontentprofiles))
                        .setExpanded(true,1100)
                        .create();

                View myview = dialogplus.getHolderView();
                EditText profileName=myview.findViewById(R.id.idTVName);
                EditText age=myview.findViewById(R.id.idTVAge);
                EditText num=myview.findViewById(R.id.idTVNum);
                Button update1= myview.findViewById(R.id.UpdateProfRec);


                profileName.setText(model.getProfileName());
                age.setText(model.getAge());
                num.setText(model.getNum());

                dialogplus.show();

                update1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map=new HashMap<>();
                        map.put("profileName",profileName.getText().toString());
                        map.put("age",age.getText().toString());
                        map.put("num",num.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("ProfileRecords")
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
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.age.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("ProfileRecords")
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profiles_rv_item,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder{

        TextView profileName,age,num;
        Button btnEdit, btnAdd, btnDelete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            profileName=(TextView)itemView.findViewById(R.id.idTVName);
            age=(TextView)itemView.findViewById(R.id.idTVAge);
            num=(TextView)itemView.findViewById(R.id.idTVNum);


            btnEdit = (Button)itemView.findViewById(R.id.idBtnEditProfRecord);
            btnDelete= (Button)itemView.findViewById(R.id.idBtnDeleteProfRecord);
            btnAdd= (Button)itemView.findViewById(R.id.idFabAddProfRec);




        }
    }
}


