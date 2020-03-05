package com.example.careonthego;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.careonthego.ui.feedbackFragment;
import com.example.careonthego.ui.medicationFragment;
import com.example.careonthego.ui.patientInfoFragment;
import com.example.careonthego.ui.timetableFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();


        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(OnNavigationViewSelectedListenerChoice);

        toolbar.setTitle(("Timetable"));
        loadFragment(new timetableFragment());
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

    private void loadFragment(Fragment fragment) {
        // For loading fragments
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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