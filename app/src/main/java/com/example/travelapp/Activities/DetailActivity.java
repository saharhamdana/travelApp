package com.example.travelapp.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.travelapp.Adapter.PicListAdapter;
import com.example.travelapp.Model.ItemModel;
import com.example.travelapp.R;
import com.example.travelapp.databinding.ActivityDetailBinding;

import java.util.ArrayList;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private ItemModel object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();

        initList();




    }

    private void initList() {
        ArrayList<String> picList=new ArrayList<>(object.getPic());
        Glide.with(this)
                .load(picList.get(0))
                .into(binding.pic);
        binding.picList.setAdapter(new PicListAdapter(picList,binding.pic));
        binding.picList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void setVariable() {
        binding.titleTxt.setText(object.getTitle());
        binding.priceTxt.setText("$"+object.getPrice());
        binding.backBtn.setOnClickListener(v->finish());
        binding.bedTxt.setText(""+object.getBed());
        binding.durationTxt.setText(object.getDuration());
        binding.distanceTxt.setText(object.getDistance());
        binding.descriptionTxt.setText(object.getDescription());
        binding.adressTxt.setText(object.getAddress());
        binding.ratingTxt.setText(object.getScore()+"Rating");
        binding.ratingBar.setRating((float) object.getScore());
    }

    private void getIntentExtra() {
        object =(ItemModel) getIntent().getSerializableExtra("object");
    }
}