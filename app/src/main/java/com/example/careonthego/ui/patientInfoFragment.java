package com.example.careonthego.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.careonthego.R;

public class patientInfoFragment extends Fragment {

    public patientInfoFragment() {
        //Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflates the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_information, container, false);
    }
}
