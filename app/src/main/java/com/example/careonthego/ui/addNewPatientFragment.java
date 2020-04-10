package com.example.careonthego.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.R;

public class addNewPatientFragment extends Fragment {

    EditText editFirstName, editSurname, editAge, editAddress, editExtraNotes, editEmergencyNumbers;
    Button submitPatientDataBtn;
    String convertAge;
    Integer finalEditAge;
    DatabaseHelper myDb;

    public addNewPatientFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myDb = new DatabaseHelper(getContext());

        View v =  inflater.inflate(R.layout.fragment_add_new_patient, container, false);

        editFirstName = (EditText)v.findViewById(R.id.pFirstNameInput);
        editSurname = (EditText)v.findViewById(R.id.pSurnameInput);
        editAge = (EditText)v.findViewById(R.id.pAgeInput);
        editAddress = (EditText)v.findViewById(R.id.pAddressInput);
        editExtraNotes = (EditText)v.findViewById(R.id.pExtraNotesInput);
        editEmergencyNumbers = (EditText)v.findViewById(R.id.pNumberInput);
        // Inflate the layout for this fragment
        addData();

        return v;
    }

    public void addData() {
        submitPatientDataBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        convertAge = editAge.getText().toString();
                        finalEditAge = Integer.parseInt(convertAge);
                        boolean isInserted = myDb.insertPatientData(editFirstName.getText().toString(),
                                editSurname.getText().toString(),
                                finalEditAge,
                                editAddress.getText().toString(),
                                editExtraNotes.getText().toString(),
                                editEmergencyNumbers.getText().toString());
                        if (isInserted == true){
                            Toast.makeText(getContext(),"Data Inserted Successfully",
                                    Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(getContext(),"Data Not Inserted",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

}
