package com.myapplicationdev.android.fyp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    // Todo: declaring objects
    EditText myEmailEditText, myPasswordEditText;
    Button myLoginButton;
    TextView iForgetPasswordTextView, mySignupHereTextView;
    ProgressDialog myProgressDialog;

    // Todo: Firebase objects
    FirebaseAuth myFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Returns an instance of this class corresponding
        // to the default FirebaseApp instance.
        myFirebaseAuth = FirebaseAuth.getInstance();

        if (myFirebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        myProgressDialog = new ProgressDialog(LoginActivity.this);

        loginDetails();
    }

    // Todo: Login Method
    void loginDetails() {

// Todo: Binding UI elements
        myEmailEditText = findViewById(R.id.email_login);
        myPasswordEditText = findViewById(R.id.password_login);
        myLoginButton = findViewById(R.id.btn_login);
        mySignupHereTextView = findViewById(R.id.tv_signup);


        // Todo: Login Method
        myLoginButton.setOnClickListener(view -> {

            String email = myEmailEditText.getText().toString().trim();
            String pass = myPasswordEditText.getText().toString().trim();

            // Verification for user inputs
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                myEmailEditText.setError("This field is required...");
                return;
            }

            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(pass)) {
                myEmailEditText.setError("The fields are required...");
                return;
            }


            myProgressDialog.setMessage("Processing...");
            myProgressDialog.show();

            myFirebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    myProgressDialog.dismiss();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, "Login successful...", Toast.LENGTH_SHORT).show();
                } else {
                    myProgressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login failed...", Toast.LENGTH_SHORT).show();

                }
            });

        });

        //Todo:  Registration activity

        mySignupHereTextView.setOnClickListener(view -> startActivity(
                new Intent(LoginActivity.this, RegistrationActivity.class)));
    }
}