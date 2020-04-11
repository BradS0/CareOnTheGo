package com.example.careonthego.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.R;

public class feedbackFragment extends Fragment {

    EditText editFeedback;
    Button submitFeedbackBtn;
    RatingBar feedbackBar;
    DatabaseHelper db;
    Boolean type;

    public feedbackFragment() {
        //Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View Initialisation
        View v =  inflater.inflate(R.layout.fragment_feedback, container, false);

         //Spinner Code
        Spinner feedbackSpinner = (Spinner) v.findViewById(R.id.feedbackSpinner);
        ArrayAdapter<CharSequence> feedbackAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.feedback_array, android.R.layout.simple_spinner_dropdown_item);
        feedbackAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        feedbackSpinner.setAdapter(feedbackAdapter);

        //DB Access Initialisation
        db = new DatabaseHelper(getContext());
        editFeedback = (EditText)v.findViewById(R.id.feedbackInput);
        submitFeedbackBtn = v.findViewById(R.id.feedbackSubmitButton);
        feedbackBar = (RatingBar)v.findViewById(R.id.ratingInput);
        type = true;

        feedbackSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            int count=0;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(count >= 1){

                    switch(position){
                        case 0:
                            loadFragment(new feedbackFragment());
                            Toast.makeText(getContext(),"hello",
                                    Toast.LENGTH_LONG).show();
                        case 1:
                            loadFragment(new timetableFragment());
                            Toast.makeText(getContext(),"AAAAAAAAAAAAAA",
                                    Toast.LENGTH_LONG).show();
                    }
                }
                count++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addFeedbackData();
        return v;

    }

    public void addFeedbackData() {
        submitFeedbackBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean feedbackInserted = db.insertFeedbackData(type,
                                editFeedback.getText().toString(),
                                feedbackBar.getRating());
                        if (feedbackInserted == true) {
                            Toast.makeText(getContext(),"Data Inserted Successfully",
                                    Toast.LENGTH_LONG).show();
                            editFeedback.getText().clear();
                            feedbackBar.setRating(0F);
                        } else {
                            Toast.makeText(getContext(), "Data Not Inserted",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    private void spinnerCheck(Spinner spinner) {

    }



    public void loadFragment(Fragment fragment) {
        // For loading fragments
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment); //Error might be here.
        transaction.addToBackStack(null);
        transaction.commit();
    }

}


