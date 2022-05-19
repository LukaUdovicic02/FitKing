package com.example.fitking.Firebase;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity
{

    private EditText emailEditText;
    private Button resetPasswordBtn;
    private ProgressBar progressBar;

    FirebaseAuth auth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.email);
        resetPasswordBtn = findViewById(R.id.resetPassword);
        progressBar = findViewById(R.id.progressBar3);

        auth = FirebaseAuth.getInstance();

        resetPasswordBtn.setOnClickListener(v -> {

            resetPassword();

        });

    }

    private void resetPassword() {

        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty())
        {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailEditText.setError("Please enter a valid email! ");
            emailEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(ForgotPassword.this, "Check your email to reset your password! ", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ForgotPassword.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
