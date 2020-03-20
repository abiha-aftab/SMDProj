package com.example.smd_po_module;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smd_po_module.classes.Student_data;
import com.squareup.picasso.Picasso;

public class list_student  extends AppCompatActivity {
    RecyclerView recycler;
    adapter_student adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);
        recycler=findViewById(R.id.recyclerViewDemo);
        adapter=new adapter_student(this, Student_data.getdata());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        Global.intent='N';
    }
}
