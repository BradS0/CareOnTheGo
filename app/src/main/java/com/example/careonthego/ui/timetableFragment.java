package com.example.careonthego.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.R;

public class timetableFragment extends Fragment {

    DatabaseHelper db;
    View v;
    TableLayout timetableBack;
    TextView taskNameTitle, taskDateTitle, taskStartTimeTitle, taskFinishTimeTitle, taskInfoTitle;
    TableRow tableHeaderRow;
    int rowWidth;
    public timetableFragment() {
        //Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_timetable, container, false);
        db = new DatabaseHelper(getContext());

        timetableBack = (TableLayout)v.findViewById(R.id.timetableTableBack);


        Spinner timetableSpinner = (Spinner) v.findViewById(R.id.timetableSpinner); // Creates the spinner object
        ArrayAdapter<CharSequence> timetableAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.day_array, android.R.layout.simple_spinner_dropdown_item);
        // Creates the spinner's adapter
        timetableAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line); //Sets the adapter viewport
        timetableSpinner.setAdapter(timetableAdapter); //Allocates the adapter to the spinner box

        timetableInit();
        return v;

    }

    public void timetableInit(){
        tableHeaderRow = new TableRow(getContext());
        rowWidth = tableHeaderRow.getWidth() / 5;
        taskNameTitle = new TextView(getContext());
        taskDateTitle = new TextView(getContext());
        taskStartTimeTitle = new TextView(getContext());
        taskFinishTimeTitle = new TextView(getContext());
        taskInfoTitle = new TextView(getContext());

        taskNameTitle.setText(R.string.task_name_table_title);
        taskDateTitle.setText(R.string.task_date_table_title);
        taskStartTimeTitle.setText(R.string.task_start_table_title);
        taskFinishTimeTitle.setText(R.string.task_finish_table_title);
        taskInfoTitle.setText(R.string.task_information_table_title);

        setTitleRow(taskNameTitle);
        setTitleRow(taskDateTitle);
        setTitleRow(taskStartTimeTitle);
        setTitleRow(taskFinishTimeTitle);
        setTitleRow(taskInfoTitle);

        tableHeaderRow.addView(taskNameTitle);
        tableHeaderRow.addView(taskDateTitle);
        tableHeaderRow.addView(taskStartTimeTitle);
        tableHeaderRow.addView(taskFinishTimeTitle);
        tableHeaderRow.addView(taskInfoTitle);
        timetableBack.addView(tableHeaderRow);

        }

    public TextView setTitleRow(TextView element){
        element.setWidth(205);
        element.setGravity(Gravity.CENTER);
        element.setTextColor(Color.BLACK);
        element.setTextSize(14);

        return element;
    }


}

