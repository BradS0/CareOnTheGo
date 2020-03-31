package com.example.careonthego.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.careonthego.R;

public class feedbackFragment extends Fragment {

    public feedbackFragment() {
        //Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_feedback, container, false);

        Spinner feedbackSpinner = (Spinner) v.findViewById(R.id.feedbackSpinner);
        ArrayAdapter<CharSequence> feedbackAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.feedback_array, android.R.layout.simple_spinner_dropdown_item);
        feedbackAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        feedbackSpinner.setAdapter(feedbackAdapter);

        return v;

    }
}


