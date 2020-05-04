package com.example.careonthego.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.MainActivity;
import com.example.careonthego.R;

public class addTimetableNoteFragment extends Fragment {

    View v;
    DatabaseHelper db;
    EditText noteNameInput, noteDateInput, noteStartInput, noteEndInput, noteInfoInput;
    Button submitNoteDataBtn;
    String fetchedUser, startTimeConversion, endTimeConversion;
    Integer userId, startTimeInt, endTimeInt;

    public addTimetableNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_add_timetable_note, container, false);
        db = new DatabaseHelper(getContext());

        noteNameInput = (EditText)v.findViewById(R.id.noteNameInput);
        noteDateInput = (EditText)v.findViewById(R.id.noteDateInput);
        noteStartInput = (EditText)v.findViewById(R.id.noteStartInput);
        noteEndInput = (EditText)v.findViewById(R.id.noteEndInput);
        noteInfoInput = (EditText)v.findViewById(R.id.noteInfoInput);
        submitNoteDataBtn = (Button)v.findViewById(R.id.addNoteSubmitBtn);

        addNewNoteData();
        return v;
    }

    public void addNewNoteData(){

        submitNoteDataBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                fetchedUser = ((MainActivity)getActivity()).getUsername();
                userId = db.getUserId(fetchedUser);
                startTimeConversion = noteStartInput.getText().toString();
                startTimeInt = Integer.parseInt(startTimeConversion);
                endTimeConversion = noteEndInput.getText().toString();
                endTimeInt = Integer.parseInt(endTimeConversion);

                boolean newNoteInserted = db.insertTimetableNoteData(userId,
                        noteNameInput.getText().toString(),
                        noteDateInput.getText().toString(),
                        startTimeInt,
                        endTimeInt,
                        noteInfoInput.getText().toString()
                        );
                if (newNoteInserted == true){
                    Toast.makeText(getContext(),"Data Inserted Successfully",
                            Toast.LENGTH_LONG).show();
                    noteNameInput.getText().clear();
                    noteDateInput.getText().clear();
                    noteStartInput.getText().clear();
                    noteEndInput.getText().clear();
                    noteInfoInput.getText().clear();
                } else {
                    Toast.makeText(getContext(),"Data Not Inserted",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}
