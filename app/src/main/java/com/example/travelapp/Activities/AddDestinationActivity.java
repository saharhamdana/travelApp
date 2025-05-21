package com.example.travelapp.Activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.databinding.ActivityAddDestinationBinding;

public class AddDestinationActivity extends AppCompatActivity {

    // Déclaration de l'objet ViewBinding
    private ActivityAddDestinationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialisation de ViewBinding
        binding = ActivityAddDestinationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Gestion du clic sur le bouton Ajouter
        binding.btnAddDestination.setOnClickListener(v -> {
            // Récupération des données saisies
            String name = binding.editDestinationName.getText().toString().trim();
            String description = binding.editDescription.getText().toString().trim();
            String imageUrl = binding.editImageUrl.getText().toString().trim();
            String priceStr = binding.editPrice.getText().toString().trim();

            // Vérification des champs obligatoires
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(description) ||
                    TextUtils.isEmpty(imageUrl) || TextUtils.isEmpty(priceStr)) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double price = Double.parseDouble(priceStr);

                // ➕ Tu peux ici ajouter la logique d'ajout en base de données
                // Exemple : envoyer les données vers Firebase ou autre

                Toast.makeText(this, "Destination ajoutée avec succès", Toast.LENGTH_SHORT).show();

                // Réinitialiser les champs
                binding.editDestinationName.setText("");
                binding.editDescription.setText("");
                binding.editImageUrl.setText("");
                binding.editPrice.setText("");

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Le prix est invalide", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
