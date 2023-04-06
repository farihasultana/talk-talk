package com.example.talk_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.talk_talk.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginBtnStart.setOnClickListener(v -> {
            startActivity(new Intent(this,LoginActivity.class));
            finish();


        });

        binding.registerBtnStart.setOnClickListener(v -> {
            startActivity(new Intent(this,RegisterActivity.class));


        });
    }
}