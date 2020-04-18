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
import androidx.fragment.app.FragmentTransaction;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.R;

public class reportBugFragment extends Fragment {

    Spinner bugSpinner;
    ArrayAdapter<CharSequence> bugAdapter;
    Button submitBugButton;
    DatabaseHelper db;
    EditText bugInput;
    Boolean type;
    Float rating;

    public reportBugFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_report_bug, container, false);
        db = new DatabaseHelper(getContext());

        type = false;
        rating = 0f;
        bugInput = (EditText)v.findViewById(R.id.bugReportInput);
        submitBugButton = (Button)v.findViewById(R.id.submitBugBtn);

        bugSpinner = (Spinner) v.findViewById(R.id.bugSpinner);
        bugAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.bug_array, android.R.layout.simple_spinner_dropdown_item);
        bugAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bugSpinner.setAdapter(bugAdapter);

        bugSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            int count=0;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(count >= 1){

                    switch(position){
                        case 0:
                            loadFragment(new reportBugFragment());
                        case 1:
                            loadFragment(new feedbackFragment());
                    }
                }
                count++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addBugData();
        return v;

    }

    public void addBugData() {
        submitBugButton.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        boolean feedbackInserted = db.insertFeedbackData(type,
                                bugInput.getText().toString(),
                                rating);
                        if (feedbackInserted == true) {
                            Toast.makeText(getContext(),"Data Inserted Successfully",
                                    Toast.LENGTH_LONG).show();
                            bugInput.getText().clear();
                        } else {
                            Toast.makeText(getContext(), "Data Not Inserted",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }



    public void loadFragment(Fragment fragment) {
        // For loading fragments
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment); //Error might be here.
        transaction.addToBackStack(null);
        transaction.commit();
    }


    // Add bug report write to db code here
}
