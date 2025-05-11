package com.example.travelapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.travelapp.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.introBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }
        });

    }
}