package edu.andrews.cptr252.lisabeth.contacts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FirstFragmentLogin extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View Fragment1View = inflater.inflate(R.layout.fragment_first_login, container, false);
        EditText editName = Fragment1View.findViewById(R.id.editUserName);
        EditText editPassword = Fragment1View.findViewById(R.id.editPassword);
        Button btnConfrim = Fragment1View.findViewById(R.id.btnLogin);
        Button btnNewUser = Fragment1View.findViewById(R.id.btnNewUser);
        EditText editConfirmPassword = Fragment1View.findViewById(R.id.editConfrimPass);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        btnConfrim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Retrieve entered username and password
                    String username = editName.getText().toString();
                    String password = editPassword.getText().toString();

                    // Implement authentication logic here
                    if (username.equals("Admin") && password.equals("123")) {
                        // Successful login
                        Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        // Failed login
                        Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
        });

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return Fragment1View;

    }

}