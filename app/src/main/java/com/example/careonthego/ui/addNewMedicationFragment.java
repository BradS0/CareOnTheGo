package com.example.careonthego.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.R;

import java.util.List;

public class addNewMedicationFragment extends Fragment {

    DatabaseHelper db;
    EditText editMedName, editMedQuant;
    Button submitNewMedBtn;
    Spinner newMedPatientSpinner;
    String patientLabel, convertQuant;
    Integer finalQuant;

    public addNewMedicationFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_add_new_medication, container, false);
        db = new DatabaseHelper(getContext());

        newMedPatientSpinner =(Spinner)v.findViewById(R.id.addNewMedSpinner);
        loadSpinnerChoices();
        newMedPatientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                patientLabel = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "You selected: " + patientLabel,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editMedName = (EditText)v.findViewById(R.id.medNameInput);
        editMedQuant = (EditText)v.findViewById(R.id.medQuantInput);
        submitNewMedBtn = (Button)v.findViewById(R.id.addNewMedSubmit);
        addNewMedicationData();
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void loadSpinnerChoices() {
        //Fetching Spinner Elements
        List<String> patientNames = db.getPatientLabels();

        //Creating Spinner Adapter
        ArrayAdapter<String> newMedSpinnerAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, patientNames);

        //Setting drop down layout styling
        newMedSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Attaching patientAdapter to patientSpinner
        newMedPatientSpinner.setAdapter(newMedSpinnerAdapter);
    }

    public void addNewMedicationData() {
        submitNewMedBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                convertQuant = editMedQuant.getText().toString();
                finalQuant = Integer.parseInt(convertQuant);
                boolean newMedicationDataInserted = db.insertNewMedication(patientLabel,editMedName.getText().toString(),
                        finalQuant);
                if (newMedicationDataInserted == true){
                    Toast.makeText(getContext(),"Data Inserted Successfully",
                            Toast.LENGTH_LONG).show();
                    editMedName.getText().clear();
                    editMedQuant.getText().clear();
                } else {
                    Toast.makeText(getContext(),"Data Not Inserted",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
