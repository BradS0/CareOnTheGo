package com.example.careonthego.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.MainActivity;
import com.example.careonthego.R;

public class accountManagementFragment extends Fragment {

    View v;
    DatabaseHelper db;
    Button editAccountBtn, deleteAccountBtn;
    Integer userId;
    Boolean deleteCheck;
    Intent restartIntent;

    public accountManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_account_management, container, false);
        db = new DatabaseHelper(getContext());
        editAccountBtn = (Button)v.findViewById(R.id.editAccountBtn);
        deleteAccountBtn = (Button)v.findViewById(R.id.deleteAccountBtn);

        // Set listener for Edit Account Details Button
        editAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new editAccountFragment());
            }
        });

        // Set listener for Delete Account Button, Deletes user from system.
        deleteAccountBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                userId = ((MainActivity)getActivity()).getUserID();
                deleteCheck = db.removeUser(userId);
                if(deleteCheck == true){
                    Toast.makeText(getContext(),"User Deleted Successfully",
                            Toast.LENGTH_LONG).show();
                    restartIntent = getContext().getPackageManager()
                            .getLaunchIntentForPackage(getContext().getPackageName());
                    restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(restartIntent);
                } else {
                    Toast.makeText(getContext(),"User Deletion Unsuccessful",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        return v;
    }

    public void loadFragment(Fragment fragment) {
        // For loading fragments
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
