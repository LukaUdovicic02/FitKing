package com.example.fitking.Firebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitking.ui.MainActivity;
import com.example.fitking.ui.NoteFragment;
import com.example.fitking.ui.ProfileFragment;
import com.example.fitking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{

    private TextView register  ,forgotPassword;
    private EditText editTextEmail , editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.register);
        register.setOnClickListener(view -> {
            startActivity(new Intent(this , RegisterUserActivity.class));
        });

        signIn = findViewById(R.id.signIN);
        signIn.setOnClickListener(view -> {
            userLogin();
        });

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);

        progressBar = findViewById(R.id.progressBar1);



        forgotPassword = findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(this, ForgotPassword.class));
        });

        mAuth = FirebaseAuth.getInstance();

    }

    private void userLogin()
    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty())
        {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextEmail.setError("Please enter a valid email! ");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty())
        {
            editTextPassword.setError("Please enter a password");

        }

        if (password.length() < 6)
        {
            editTextPassword.setError("Please enter a password more then 6  characters ");
            editTextEmail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified())
                    {
                        Toast.makeText(LoginActivity.this, "User has been Logged in successfully!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else {
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Please verify your email account", Toast.LENGTH_LONG).show();

                    }
                }
                else {
                Toast.makeText(LoginActivity.this, "Failed to Login!", Toast.LENGTH_LONG).show();
            }}
        });
    }
}
