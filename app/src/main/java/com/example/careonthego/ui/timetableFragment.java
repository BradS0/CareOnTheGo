package com.example.careonthego.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.careonthego.R;

public class timetableFragment extends Fragment {

    public timetableFragment() {
        //Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_timetable, container, false);

        Spinner timetableSpinner = (Spinner) v.findViewById(R.id.timetableSpinner); // Creates the spinner object
        ArrayAdapter<CharSequence> timetableAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.day_array, android.R.layout.simple_spinner_dropdown_item); // Creates the spinner's adapter
        timetableAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line); //Sets the adapter viewport
        timetableSpinner.setAdapter(timetableAdapter); //Allocates the adapter to the spinner box

        return v;

    }


}

