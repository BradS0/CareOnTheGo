package com.example.careonthego.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.careonthego.R;

public class medicationFragment extends Fragment {

    public medicationFragment() {
        //Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_medication_management, container, false);

        Spinner medicationSpinner = (Spinner) v.findViewById(R.id.medicationSpinner);
        ArrayAdapter<CharSequence> medicationAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.patient_array, android.R.layout.simple_spinner_dropdown_item);
        medicationAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        medicationSpinner.setAdapter(medicationAdapter);

        return v;


    }
}
