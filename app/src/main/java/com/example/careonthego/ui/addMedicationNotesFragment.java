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


public class addMedicationNotesFragment extends Fragment {

    private DatabaseHelper db;
    private Spinner pNameSpinner, medNameSpinner;
    private String pNameLabel, medNameLabel;
    private Integer pNamePatientInfoId, medNameMedId;
    private List<String> patientNames, medicationNames;
    private ArrayAdapter<String> medNameAdapter, medPNameAdapter;
    private Button submitMedNotesBtn;
    private EditText editMedNoteInput;

    public addMedicationNotesFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_add_medication_notes, container, false);
        db = new DatabaseHelper(getContext());
        pNameSpinner = (Spinner)v.findViewById(R.id.medNotesNameSpinner);
        medNameSpinner = (Spinner)v.findViewById(R.id.medNotesMedSpinner);
        editMedNoteInput = (EditText)v.findViewById(R.id.medNoteInput);
        submitMedNotesBtn = (Button)v.findViewById(R.id.addNewNoteSubmit);

        loadPNameSpinnerChoices();

        pNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pNameLabel = parent.getItemAtPosition(position).toString();
                pNamePatientInfoId = db.getPatientInfoID(pNameLabel);
                loadMedNameSpinnerChoices(pNamePatientInfoId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        medNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medNameLabel = parent.getItemAtPosition(position).toString();
                medNameMedId = db.getMedicationId(medNameLabel);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addNewMedNotes();
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void loadPNameSpinnerChoices() {
        //Fetching Spinner Elements
        patientNames = db.getPatientLabels();

        //Creating Spinner Adapter
        medPNameAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, patientNames);

        //Setting drop down layout styling
        medPNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Attaching patientAdapter to patientSpinner
        pNameSpinner.setAdapter(medPNameAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void loadMedNameSpinnerChoices(Integer patientInfoId) {
        //Fetching Spinner Elements
        medicationNames = db.getMedicationLabels(patientInfoId);

        //Creating Spinner Adapter
        medNameAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, medicationNames);

        //Setting drop down layout styling
        medNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Attaching patientAdapter to patientSpinner
        medNameSpinner.setAdapter(medNameAdapter);
    }


    public void addNewMedNotes() {
        submitMedNotesBtn.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        boolean updateInserted = db.insertMedicationNoteData(medNameMedId,
                                editMedNoteInput.getText().toString());
                    if (updateInserted == true){
                        Toast.makeText(getContext(),"Data Inserted Successfully",
                                Toast.LENGTH_LONG).show();
                        editMedNoteInput.getText().clear();
                    }else {
                        Toast.makeText(getContext(),"Data Not Inserted",
                                Toast.LENGTH_LONG).show();
                    }



                    }
                }
        );
    }
}
