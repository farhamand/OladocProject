package com.example.oladoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private CardView cardView, cardView1, cardView2, cardView3;
    private ActionBarDrawerToggle toggle;

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardView = findViewById(R.id.testlab);
        cardView1 = findViewById(R.id.docappointmentss);
        cardView2 = findViewById(R.id.videodoc);
        cardView3 = findViewById(R.id.hospitals);


        drawerLayout= findViewById(R.id.my_drawer);
        navigationView= findViewById(R.id.navigation_view);


        toggle= new ActionBarDrawerToggle(this, drawerLayout,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),DoctorsTypesActivity.class);
                startActivity(a);
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),DoctorsTypesActivity.class);
                startActivity(a);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),DoctorsTypesActivity.class);
                startActivity(a);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),DoctorsTypesActivity.class);
                startActivity(a);
            }
        });



        bottomNavigationView =findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);




        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){


                    case R.id.appointments:
                        startActivity(new Intent(getApplicationContext(), AppointmentsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        return true;

                    case R.id.medicalrecords:
                        startActivity(new Intent(getApplicationContext(), MedicalRecordsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }



                return false;
            }
        });








    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     *
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param menuItem The selected item
     * @return true to display the item as the selected item
     */


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.myprofiles:
                Intent intent = new Intent(getApplicationContext(), ProfilesActivity.class);
                startActivity(intent);

                break;
            case R.id.recentdoc:
                Intent intent2 = new Intent(getApplicationContext(),DoctorsTypesActivity.class);
                startActivity(intent2);
                break;
            case R.id.logout:
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
                finish();
                break;

        }
        return false;
    }
}