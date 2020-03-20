package com.example.smd_po_module;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smd_po_module.classes.Job_data;
import com.example.smd_po_module.notify_students1;
import com.squareup.picasso.Picasso;

public class select_job_notify extends AppCompatActivity {
    RecyclerView recycler;
    adapter_job adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_job_to_notify);
        ImageView profileImage = findViewById(R.id.profile);
        Global.intent='N';
        Picasso.get().load(R.drawable.logo).into(profileImage);
        recycler=findViewById(R.id.recyclerViewDemo);
        adapter=new adapter_job(this, Job_data.getdata());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));


    }
}
