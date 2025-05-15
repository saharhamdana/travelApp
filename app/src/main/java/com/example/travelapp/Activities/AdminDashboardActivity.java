package com.example.travelapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.databinding.ActivityAdminDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboardActivity extends AppCompatActivity {

    private ActivityAdminDashboardBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListeners();
    }

    private void setupListeners() {
        // Bouton pour ajouter une nouvelle destination
        binding.btnAddDestination.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboardActivity.this, AddDestinationActivity.class);
            startActivity(intent);
        });

        // Bouton pour afficher les utilisateurs (optionnel si tu veux l'ajouter)
        binding.btnViewUsers.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboardActivity.this, ViewUsersActivity.class);
            startActivity(intent);
        });

        // Bouton déconnexion
        binding.btnLogout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Déconnexion réussie", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminDashboardActivity.this, LoginActivity.class));
            finish();
        });
    }
}
