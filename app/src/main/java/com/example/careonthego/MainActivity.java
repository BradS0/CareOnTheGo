package com.example.careonthego;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.careonthego.ui.feedbackFragment;
import com.example.careonthego.ui.medicationFragment;
import com.example.careonthego.ui.patientInfoFragment;
import com.example.careonthego.ui.timetableFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener{

    private ActionBar toolbar;
    DatabaseHelper myDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(OnNavigationViewSelectedListenerChoice);

        toolbar.setTitle(("Timetable"));
        loadFragment(new timetableFragment());
        myDb = new DatabaseHelper(this);

       // RecyclerView recyclerMedication = (RecyclerView)findViewById(R.id.recyclerView);
       // \GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
       // recyclerMedication.setLayoutManager(gridLayoutManager);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener OnNavigationViewSelectedListenerChoice
            =(item) -> {
            switch (item.getItemId()) {
                case R.id.navigation_timetable:
                    toolbar.setTitle("Timetable");
                    loadFragment(new timetableFragment());
                    return true;
                case R.id.navigation_patientInfo:
                    toolbar.setTitle("Patient Information");
                    loadFragment(new patientInfoFragment());
                    return true;
                case R.id.navigation_medManagement:
                    toolbar.setTitle("Medication Management");
                    loadFragment(new medicationFragment());
                    return true;
                case R.id.navigation_feedback:
                    toolbar.setTitle("Feedback");
                    loadFragment(new feedbackFragment());
                    return true;
            }
            return false;
    };

    public void loadFragment(Fragment fragment) {
        // For loading fragments
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


/* things to do:
- Fix the text problem with it not displaying
- Fix that butters green colour
- Fix the problem with the timetable & patient info page being in the wrong order.
- Fix it on the text on the bar too

*/

///    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
//

// Warning:(35, 28) Casting 'findViewById(...)' to 'Spinner' is redundant