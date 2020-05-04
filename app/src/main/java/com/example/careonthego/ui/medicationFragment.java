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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.R;

import java.util.ArrayList;
import java.util.List;

public class medicationFragment extends Fragment {

    Spinner medicationSpinner;
    DatabaseHelper db;
    TableLayout tableBack;
    private TableRow row1, titleRow;
    TextView med1, med2, extraMedNotesDisplay, tableMedTitle, tableMedQuant;
    String medPatientLabel, medExtraNotes;
    Integer medPatientInfoId, boxSize;

    Button newMedicationBtn, newMedNotesBtn;

    public medicationFragment() {
        //Constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Database and View Instantiation
        db = new DatabaseHelper(getContext());
        View v =  inflater.inflate(R.layout.fragment_medication_management, container, false);

        //Table Declaration
        tableBack = (TableLayout)v.findViewById(R.id.tableView);

        //Button Declaration
        newMedicationBtn = (Button)v.findViewById(R.id.newMedBtn);
        newMedNotesBtn = (Button)v.findViewById(R.id.newMedNoteBtn);
        newMedicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new addNewMedicationFragment());
            }
        });
        newMedNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new addMedicationNotesFragment());
            }
        });

        // Spinner Declaration and listener implementation
        medicationSpinner = (Spinner)v.findViewById(R.id.medicationSpinner);
        extraMedNotesDisplay = (TextView)v.findViewById(R.id.medNoteDisplay);

        loadSpinnerChoices();

        //Find a a way to clear this for people who don't have extra notes. Could make it so that the user has to put n/a if no extra notes.
        medicationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medPatientLabel = parent.getItemAtPosition(position).toString();
                medPatientInfoId = db.getPatientInfoID(medPatientLabel);
                medExtraNotes = db.getMedicationNotes(medPatientInfoId);
                if (medExtraNotes!=null){
                    extraMedNotesDisplay.setText(medExtraNotes);
                }

                Toast.makeText(parent.getContext(), "You selected: " + medPatientLabel,
                        Toast.LENGTH_LONG).show();
                ArrayList<String> retrievedMedication = db.getMedicationInfo(medPatientInfoId);
                tableInit(retrievedMedication);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        //This removes the existing entries upon changing patient selection.
        tableBack.removeAllViews();
        //Dynamically creates textviews to be inserted into the table containing the information from the medication table relevant to the patient.
        titleRow = new TableRow(getContext());
        tableMedTitle = new TextView(getContext());
        tableMedQuant = new TextView(getContext());

        tableMedTitle.setWidth(tableBack.getWidth() / 2);
        tableMedTitle.setText(R.string.table_title);
        tableMedTitle.setGravity(Gravity.CENTER);
        tableMedTitle.setTextSize(18);
        tableMedTitle.setTextColor(Color.BLACK);

        tableMedQuant.setWidth(tableBack.getWidth() / 2);
        tableMedQuant.setText(R.string.table_title);
        tableMedQuant.setGravity(Gravity.CENTER);
        tableMedQuant.setTextSize(18);
        tableMedQuant.setTextColor(Color.BLACK);

        titleRow.addView(tableMedTitle);
        titleRow.addView(tableMedQuant);
        tableBack.addView(titleRow);
        for(int i = 0; i < retrievedMedication.size(); i++) {
            row1 = new TableRow(getContext());
            med1 = new TextView(getContext());
            med1.setWidth(tableBack.getWidth() / 2);
            med1.setText(retrievedMedication.get(i));
            med1.setTextSize(18);
            med1.setTextColor(Color.BLACK);
            med1.setGravity(Gravity.CENTER);
            row1.addView(med1);

            i++;

            med2 = new TextView(getContext());
            med2.setWidth(tableBack.getWidth() / 2);
            med2.setText(retrievedMedication.get(i));
            med2.setTextSize(18);
            med2.setTextColor(Color.BLACK);
            med2.setGravity(Gravity.CENTER);
            row1.addView(med2);
            tableBack.addView(row1);
        }
    }

    public void loadFragment(Fragment fragment) {
        // For loading fragments
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public TextView setElement(TextView element) {
        element.setWidth(boxSize);
        element.setGravity(Gravity.CENTER);
        element.setTextSize(18);
        element.setTextColor(Color.BLACK);
        return element;
    }

}
