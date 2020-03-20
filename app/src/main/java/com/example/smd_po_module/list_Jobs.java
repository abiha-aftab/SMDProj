package com.example.smd_po_module;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smd_po_module.classes.Job_data;
import com.squareup.picasso.Picasso;

public class list_Jobs extends AppCompatActivity {
    RecyclerView recycler;
    adapter_job adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__jobs);

        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);
        recycler=findViewById(R.id.recyclerViewDemo);
        adapter=new adapter_job(this, Job_data.getdata());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
       Global.intent='J';
        Button but=findViewById(R.id.notify123);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Add_Job2.class);
                startActivity(i);
            }
        });


    }

}
