package com.example.travelapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.Model.User;
import com.example.travelapp.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final List<User> userList;
    private final OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(User user);
    }

    public UserAdapter(List<User> userList, OnDeleteClickListener deleteClickListener) {
        this.userList = userList;
        this.deleteClickListener = deleteClickListener;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userEmail;
        ImageView userImage, btnDelete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userEmail = itemView.findViewById(R.id.userEmail);
            userImage = itemView.findViewById(R.id.userImage);
            btnDelete = itemView.findViewById(R.id.btnDeleteUser);
        }

        public void bind(User user, OnDeleteClickListener listener) {
            userName.setText(user.getUsername());
            userEmail.setText(user.getEmail());

            btnDelete.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onDeleteClick(user);
                }
            });
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(userList.get(position), deleteClickListener);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
