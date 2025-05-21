package com.example.travelapp.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.travelapp.Adapter.UserAdapter;
import com.example.travelapp.Model.User;
import com.example.travelapp.databinding.ActivityViewUsersBinding;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class ViewUsersActivity extends AppCompatActivity {

    private ActivityViewUsersBinding binding;
    private final List<User> userList = new ArrayList<>();
    private UserAdapter adapter;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usersRef = FirebaseDatabase.getInstance().getReference("Users");

        adapter = new UserAdapter(userList, user -> deleteUser(user));
        binding.recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewUsers.setAdapter(adapter);

        fetchUsers();


    }

    private void fetchUsers() {
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    User user = data.getValue(User.class);
                    if (user != null) {
                        user.setUid(data.getKey()); // important
                        userList.add(user);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewUsersActivity.this, "Erreur de chargement", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteUser(User user) {
        if (user.getUid() == null) return;

        new AlertDialog.Builder(this)
                .setTitle("Supprimer l'utilisateur")
                .setMessage("Êtes-vous sûr de vouloir supprimer cet utilisateur ?")
                .setPositiveButton("Oui", (dialog, which) -> {
                    usersRef.child(user.getUid()).removeValue()
                            .addOnSuccessListener(unused -> {
                                userList.remove(user);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(this, "Utilisateur supprimé", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Erreur suppression", Toast.LENGTH_SHORT).show();
                            });
                })
                .setNegativeButton("Annuler", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
