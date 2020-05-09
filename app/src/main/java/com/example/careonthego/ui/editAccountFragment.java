package com.example.careonthego.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.MainActivity;
import com.example.careonthego.R;


public class editAccountFragment extends Fragment {

    View v;
    DatabaseHelper db;
    Integer userId;
    EditText newUsernameInput, newPasswordInput;
    Button submitAccountBtn;
    Boolean dataSubmissionResult;
    Intent restartIntent;


    public editAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_edit_account, container, false);
        db = new DatabaseHelper(getContext());

        newUsernameInput = (EditText)v.findViewById(R.id.newUsernameInput);
        newPasswordInput = (EditText)v.findViewById(R.id.newPasswordInput);
        submitAccountBtn = (Button)v.findViewById(R.id.editAccountSubmitBtn);

        submitAccountBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                userId = ((MainActivity)getActivity()).getUserID();
                dataSubmissionResult = db.updateUserDetails(userId, newUsernameInput.getText().toString(), newPasswordInput.getText().toString());
                if (dataSubmissionResult == true){
                    Toast.makeText(getContext(),"Data Inserted Successfully",
                            Toast.LENGTH_LONG).show();
                    newUsernameInput.getText().clear();
                    newPasswordInput.getText().clear();
                    restartIntent = getContext().getPackageManager()
                            .getLaunchIntentForPackage(getContext().getPackageName());
                    restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(restartIntent);
                }else {
                    Toast.makeText(getContext(),"Data Not Inserted",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }
}
