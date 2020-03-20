package com.example.smd_po_module;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smd_po_module.classes.Student_data;
import com.squareup.picasso.Picasso;

public class notify_students1 extends AppCompatActivity {
    RecyclerView recycler;
    adapter_student adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_students1);
        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);
        Global.intent = 'S';
        recycler=findViewById(R.id.recyclerViewDemo);
        adapter=new adapter_student(this, Student_data.getdata());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
