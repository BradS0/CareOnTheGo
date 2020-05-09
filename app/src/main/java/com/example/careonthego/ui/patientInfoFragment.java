package com.example.careonthego.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.R;

import java.util.ArrayList;
import java.util.List;

public class patientInfoFragment extends Fragment {

    Spinner patientSpinner;
    DatabaseHelper db;
    TextView emergencyDetailsDisplay, relevantDetailsDisplay, addressDetailsDisplay;
    Button addNewPatientBtn;

    public patientInfoFragment() {
        //Constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        db = new DatabaseHelper(getContext());
        View v =  inflater.inflate(R.layout.fragment_patient_information, container, false);
/*
        Spinner patientSpinner = (Spinner) v.findViewById(R.id.patientSpinner);
        ArrayAdapter<CharSequence> patientAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.patient_array, android.R.layout.simple_spinner_dropdown_item);
        patientAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        patientSpinner.setAdapter(patientAdapter);

*/
        emergencyDetailsDisplay = (TextView)v.findViewById(R.id.emergencyDetailsDisplay);
        relevantDetailsDisplay = (TextView)v.findViewById(R.id.relevantInformationDisplay);
        addressDetailsDisplay = (TextView)v.findViewById(R.id.patientAddressDisplay);
        patientSpinner = (Spinner)v.findViewById(R.id.patientSpinner);
        loadSpinnerChoices();
        patientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //On selection of spinner item
                String patientLabel = parent.getItemAtPosition(position).toString();

                Toast.makeText(parent.getContext(), "You selected: " + patientLabel,
                        Toast.LENGTH_LONG).show();
                ArrayList<String> fetchedData = db.getPatientInfoDetails(patientLabel);
                emergencyDetailsDisplay.setText(fetchedData.get(0));
                relevantDetailsDisplay.setText(fetchedData.get(1));
                addressDetailsDisplay.setText(fetchedData.get(2));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        addNewPatientBtn = (Button)v.findViewById(R.id.newPatientButton);
        addNewPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                loadFragment(new addNewPatientFragment());
            }
        });
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void loadSpinnerChoices() {
        //Fetching Spinner Elements
        List<String> patientNames = db.getPatientLabels();

        //Creating Spinner Adapter
        ArrayAdapter<String> patientAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, patientNames);

        //Setting drop down layout styling
        patientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Attaching patientAdapter to patientSpinner
        patientSpinner.setAdapter(patientAdapter);
    }

    public void loadFragment(Fragment fragment) {
        // For loading fragments
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}


