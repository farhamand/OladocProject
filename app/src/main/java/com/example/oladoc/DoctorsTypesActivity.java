package com.example.oladoc;
import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DoctorsTypesActivity extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"Skin Specialist",  "Dentist","Heart Specialist","General Physician","Gynecologist","Child Specialist","Urologist",
            "Orthopedic Surgeon","ENT Specialist","Kidney Specialist","Neurologist","Pulmonologist"};
    /*String mDescription[] = {"Skin Specialist", "Dentist", "Heart Specialist", "General Physician", "Gynecologist","Child Specialist"};*/
    int images[] = {R.drawable.ss,R.drawable.dentist, R.drawable.hearts, R.drawable.gp, R.drawable.gyneo, R.drawable.cp,R.drawable.urologist,
            R.drawable.orthopedic,R.drawable.ent,R.drawable.neurologist,R.drawable.pulmonologist,R.drawable.kiddney};
    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_types);

        listView = (ListView) findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, mTitle, images);
        listView.setAdapter(adapter);


        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index= position;
                Toast.makeText(getApplicationContext(), "You selected "+ mTitle[index], Toast.LENGTH_SHORT).show();

            }
        });
        // so item click is done now check list view
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        /*String rDescription[];*/
        int rImgs[];

        MyAdapter(Context c, String[] title, int[] imgs) {
            super(c, R.layout.raw, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            // this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View raw = layoutInflater.inflate(R.layout.raw, parent, false);
            ImageView images =(ImageView) raw.findViewById(R.id.image);
            TextView myTitle = (TextView) raw.findViewById(R.id.textView1);
            //TextView myDescription = (TextView) raw.findViewById(R.id.textView2);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            //myDescription.setText(rDescription[position]);




            return raw;
        }
    }
}
