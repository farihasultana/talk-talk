package com.example.talk_talk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.talk_talk.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    FirebaseAuth userAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser!= null){
            startActivity( new Intent(this, MainActivity.class));
            finish();
        }

        userAuth =FirebaseAuth.getInstance();

        binding.loginButton.setOnClickListener(v -> {

            String email =binding.userEmail.getText().toString().trim();
            String password =binding.userPassword.getText().toString().trim();



            if (email.equals("")){
                binding.userEmail.setError("Email can't be empty");
            }

            else if (password.equals("")){
                binding.userPassword.setError("password can't be empty");
            }

            else{
                logInButton( email,password);
            }

        });
    }

    private void logInButton(String email, String password) {
        userAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginActivity.this, "Successfully registered..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showAlert(e.getLocalizedMessage() );

            }
        });
    }

    private void showAlert(String errMsg){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Warning!");
        builder.setMessage(errMsg);
        builder.setIcon(android.R.drawable.stat_notify_error);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    }



