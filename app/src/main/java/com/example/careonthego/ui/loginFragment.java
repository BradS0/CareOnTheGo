package com.example.careonthego.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.careonthego.DatabaseHelper;
import com.example.careonthego.MainActivity;
import com.example.careonthego.R;

public class loginFragment extends Fragment {

    DatabaseHelper db;
    EditText usernameInput, passwordInput;
    Button loginSubmitBtn, forgotCredentialsBtn;
    String username, password;
    Boolean credentialCheck;

    public loginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DatabaseHelper(getContext());
        View v =  inflater.inflate(R.layout.fragment_login, container, false);

        usernameInput = (EditText)v.findViewById(R.id.usernameInput);
        passwordInput = (EditText)v.findViewById(R.id.passwordInput);
        loginSubmitBtn = (Button)v.findViewById(R.id.loginSubmitBtn);
        forgotCredentialsBtn = (Button)v.findViewById(R.id.forgotLoginBtn);

        loginSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                credentialCheck = db.checkLoginCredentials(username, password);
                if (credentialCheck!=null && credentialCheck == true){
                    loadFragment(new timetableFragment());
                    ((MainActivity)getActivity()).loadNavBar();
                } else {
                    Toast.makeText(getContext(), "Incorrect Login Credentials, please try again",
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
