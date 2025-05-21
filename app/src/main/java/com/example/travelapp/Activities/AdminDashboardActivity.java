package com.example.travelapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.databinding.ActivityAdminDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboardActivity extends AppCompatActivity {

    private ActivityAdminDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListeners();
    }

    private void setupListeners() {
        // 📌 Carte : Ajouter une destination
        binding.cardAddDestination.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboardActivity.this, AddDestinationActivity.class);
            startActivity(intent);
        });

        // 📌 Carte : Voir les utilisateurs
        binding.cardViewUsers.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboardActivity.this, ViewUsersActivity.class);
            startActivity(intent);
        });

        // 📌 Bouton : Déconnexion
        binding.btnLogout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Déconnexion réussie", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminDashboardActivity.this, LoginActivity.class));
            finish();
        });
    }
}
