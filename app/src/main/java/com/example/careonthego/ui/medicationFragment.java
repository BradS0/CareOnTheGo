package com.example.careonthego.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.R;

import java.util.ArrayList;
import java.util.List;

public class medicationFragment extends Fragment {

    Spinner medicationSpinner;
    DatabaseHelper db;


    public medicationFragment() {
        //Constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        db = new DatabaseHelper(getContext());
        View v =  inflater.inflate(R.layout.fragment_medication_management, container, false);

        medicationSpinner = (Spinner)v.findViewById(R.id.medicationSpinner);
        loadSpinnerChoices();
        medicationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String medPatientLabel = parent.getItemAtPosition(position).toString();
                Integer medPatientInfoId = db.getPatientInfoID(medPatientLabel);
                Toast.makeText(parent.getContext(), "You selected: " + medPatientLabel,
                        Toast.LENGTH_LONG).show();
                ArrayList<String> retrievedMedication = db.getMedicationInfo(medPatientInfoId);
                tableInit(retrievedMedication);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // RecyclerView recyclerMedication = (RecyclerView) v.findViewById(R.id.recyclerView);
        // GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // recyclerMedication.setLayoutManager(gridLayoutManager);


        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void loadSpinnerChoices() {
        //Fetching Spinner Elements
        List<String> patientNames = db.getPatientLabels();

        //Creating Spinner Adapter
        ArrayAdapter<String> medicationAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, patientNames);

        //Setting drop down layout styling
        medicationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Attaching patientAdapter to patientSpinner
        medicationSpinner.setAdapter(medicationAdapter);
    }

    public void tableInit(ArrayList<String> retrievedMedication){
        TableLayout tableBack = (TableLayout) getView().findViewById(R.id.tableView);
        TableRow tableTitleRow = new TableRow(getContext());

        TextView titleMed = new TextView(getContext());
        titleMed.setText(R.string.table_title);
        titleMed.setTextColor(Color.BLACK);
        titleMed.setGravity(Gravity.CENTER);
        titleMed.setPadding(3,0,0,0);
        tableTitleRow.addView(titleMed);

        TextView titleQuant = new TextView(getContext());
        titleQuant.setText(R.string.table_quantity);
        titleQuant.setTextColor(Color.BLACK);
        titleQuant.setGravity(Gravity.CENTER);
        titleQuant.setPadding(3,0,0,0);
        tableTitleRow.addView(titleQuant);

        int b = 0;
        while(b < retrievedMedication.size()) {
            TableRow row1 = new TableRow(getContext());
            TextView med1 = new TextView(getContext());
            med1.setText(retrievedMedication.get(b));
            med1.setTextColor(Color.BLACK);
            med1.setGravity(Gravity.RIGHT);
            med1.setPadding(0,0,3,0);
            row1.addView(med1);

            b+=1;

            TextView med2 = new TextView(getContext());
            med2.setText(retrievedMedication.get(b));
            med2.setTextColor(Color.BLACK);
            med2.setGravity(Gravity.LEFT);
            med2.setPadding(3,0,0,0);
            row1.addView(med2);

            b+=1;
        }

        // While loop might not be needed depending on how I can integrate the DBHelper method.
    }




}
