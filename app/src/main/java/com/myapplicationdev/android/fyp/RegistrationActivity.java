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

public class RegistrationActivity extends AppCompatActivity {

    // Todo: declaring objects
    EditText myEmail, myPassword;
    Button registerButton;
    TextView myLoginTextView;
    ProgressDialog myProgressDialog;
    // Todo: Firebase objects
    FirebaseAuth myFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        myFirebaseAuth = FirebaseAuth.getInstance();
        myProgressDialog = new ProgressDialog(RegistrationActivity.this);

        registration();
    }

    void registration() {

        // Todo: bind objects
        myEmail = findViewById(R.id.email_reg);
        myPassword = findViewById(R.id.password_reg);
        registerButton = findViewById(R.id.btn_reg);
        myLoginTextView = findViewById(R.id.tv_signIn);


        // Todo:  register action
        registerButton.setOnClickListener(view -> {

            String email = myEmail.getText().toString().trim();
            String pass = myPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                myEmail.setError("This field is required...");
                return;
            }

            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(pass)) {
                myEmail.setError("The fields are required...");
                return;
            }

            myProgressDialog.setMessage("Processing..");
            myProgressDialog.show();

            // Todo: linking with firebase for Register Method
            myFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {

                // if registration is successfully complete , user will be sent to HomeActivity
                if (task.isSuccessful()) {

                    // dismiss dialog after register is successfully complete
                    myProgressDialog.dismiss();
                    Toast.makeText(RegistrationActivity.this, "Registration Complete.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));

                } else {

                    // dismiss dialog after Register is failed
                    myProgressDialog.dismiss();
                    Toast.makeText(RegistrationActivity.this, "Registration Field is required...", Toast.LENGTH_SHORT).show();
                }

            });


        });

        myLoginTextView.setOnClickListener(view -> startActivity(new Intent(RegistrationActivity.this, MainActivity.class)));

    }
}