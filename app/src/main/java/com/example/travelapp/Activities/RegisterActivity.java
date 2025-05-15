package com.example.travelapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.btnRegister.setOnClickListener(view -> registerUser());

        // Rediriger vers LoginActivity si l’utilisateur a déjà un compte
        binding.loginRedirect.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void registerUser() {
        String name = binding.editName.getText().toString().trim();
        String email = binding.editEmail.getText().toString().trim();
        String password = binding.editPassword.getText().toString().trim();
        int selectedId = binding.radioGroup.getCheckedRadioButtonId();

        if (TextUtils.isEmpty(name)) {
            binding.editName.setError("Enter name");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            binding.editEmail.setError("Enter email");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            binding.editPassword.setError("Enter password");
            return;
        }
        if (selectedId == -1) {
            Toast.makeText(this, "Select a role", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadio = findViewById(selectedId);
        String role = selectedRadio.getText().toString();

        binding.btnRegister.setEnabled(false);

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    binding.btnRegister.setEnabled(true);

                    if (task.isSuccessful()) {
                        String userId = auth.getCurrentUser().getUid();

                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("name", name);
                        userMap.put("email", email);
                        userMap.put("role", role);

                        database.getReference().child("Users").child(userId)
                                .setValue(userMap)
                                .addOnCompleteListener(task1 -> {
                                    Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    if (role.equalsIgnoreCase("Admin")) {
                                        startActivity(new Intent(this, AdminDashboardActivity.class));
                                    } else {
                                        startActivity(new Intent(this, MainActivity.class));
                                    }
                                    finish();
                                });
                    } else {
                        Toast.makeText(this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
    }
}
