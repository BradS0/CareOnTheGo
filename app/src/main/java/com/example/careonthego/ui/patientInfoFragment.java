package com.example.careonthego.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.careonthego.R;

public class patientInfoFragment extends Fragment {

    public patientInfoFragment() {
        //Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_patient_information, container, false);

        Spinner patientSpinner = (Spinner) v.findViewById(R.id.patientSpinner);
        ArrayAdapter<CharSequence> patientAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.patient_array, android.R.layout.simple_spinner_dropdown_item);
        patientAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        patientSpinner.setAdapter(patientAdapter);

        return v;

    }
}


