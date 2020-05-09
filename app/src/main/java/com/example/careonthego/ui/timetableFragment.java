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

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.MainActivity;
import com.example.careonthego.R;

import java.util.ArrayList;

public class timetableFragment extends Fragment {

    DatabaseHelper db;
    View v;
    TableLayout timetableBack;
    TextView taskNameTitle, taskDateTitle, taskStartTimeTitle, taskFinishTimeTitle, taskInfoTitle, entry1, entry2, entry3, entry4, entry5;
    TableRow tableHeaderRow, placeholderRow;
    Integer rowWidth, userId;
    String dayLabel, username;
    Spinner daySpinner;
    ArrayList<String> fetchedTaskInfo;
    Button addNewTimetableNoteBtn;


    public timetableFragment() {
        //Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_timetable, container, false);
        db = new DatabaseHelper(getContext());
        timetableBack = (TableLayout)v.findViewById(R.id.timetableTableBack);


        addNewTimetableNoteBtn = (Button)v.findViewById(R.id.addTimetableNoteBtn);
        addNewTimetableNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new addTimetableNoteFragment());
            }
        });






        daySpinner = (Spinner) v.findViewById(R.id.timetableSpinner); // Creates the spinner object
        ArrayAdapter<CharSequence> timetableAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.day_array, android.R.layout.simple_spinner_dropdown_item);
        // Creates the spinner's adapter
        timetableAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line); //Sets the adapter viewport
        daySpinner.setAdapter(timetableAdapter); //Allocates the adapter to the spinner box
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayLabel = parent.getItemAtPosition(position).toString();
                userId = ((MainActivity)getActivity()).getUserID();
                fetchedTaskInfo = db.getTaskInfo(userId, dayLabel);
                timetableInit(fetchedTaskInfo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void timetableInit(ArrayList<String> timetableInfo){

        timetableBack.removeAllViews();

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

        setTitleComponent(taskNameTitle, timetableBack);
        setTitleComponent(taskDateTitle, timetableBack);
        setTitleComponent(taskStartTimeTitle, timetableBack);
        setTitleComponent(taskFinishTimeTitle, timetableBack);
        setTitleComponent(taskInfoTitle, timetableBack);

        tableHeaderRow.addView(taskNameTitle);
        tableHeaderRow.addView(taskDateTitle);
        tableHeaderRow.addView(taskStartTimeTitle);
        tableHeaderRow.addView(taskFinishTimeTitle);
        tableHeaderRow.addView(taskInfoTitle);
        timetableBack.addView(tableHeaderRow);

        for (int i=0; i < (timetableInfo.size()); i++){
            placeholderRow = new TableRow(getContext());

            entry1 = new TextView(getContext());
            entry2 = new TextView(getContext());
            entry3 = new TextView(getContext());
            entry4 = new TextView(getContext());
            entry5 = new TextView(getContext());

            entry1.setText(timetableInfo.get(i));
            setComponent(entry1, timetableBack);
            entry1.setPadding(3,0,0, 5);
            placeholderRow.addView(entry1);

            i++;

            entry2.setText(timetableInfo.get(i));
            setComponent(entry2, timetableBack);
            placeholderRow.addView(entry2);

            i++;

            entry3.setText(timetableInfo.get(i));
            setComponent(entry3, timetableBack);
            placeholderRow.addView(entry3);

            i++;

            entry4.setText(timetableInfo.get(i));
            setComponent(entry4, timetableBack);
            placeholderRow.addView(entry4);

            i++;

            entry5.setText(timetableInfo.get(i));
            setComponent(entry5, timetableBack);
            placeholderRow.addView(entry5);
            timetableBack.addView(placeholderRow);
        }

        }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TextView setTitleComponent(TextView element, TableLayout tableSize){
        element.setWidth(tableSize.getWidth() / 5);
        element.setGravity(Gravity.CENTER);
        element.setTextColor(Color.BLACK);
        element.setTextSize(14);
        element.setBackgroundResource(R.drawable.box_back);

        return element;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TextView setComponent(TextView element, TableLayout tableSize){
        element.setWidth(tableSize.getWidth() / 5);
        element.setHeight(tableSize.getHeight() / 5);
        element.setGravity(Gravity.CENTER);
        element.setTextColor(Color.BLACK);
        element.setTextSize(14);
       // element.setBackgroundResource(R.drawable.box_back);

        return element;
    }

    public void loadFragment(Fragment fragment) {
        // For loading fragments
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}

