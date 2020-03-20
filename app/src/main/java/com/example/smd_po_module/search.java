package com.example.smd_po_module;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smd_po_module.data.DATAforSearch;
import com.example.smd_po_module.data.model.PO_DATA;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class search extends AppCompatActivity {
    poSearchadapter po;
    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        recycler= findViewById(R.id.recyclerViewDemo);
        ArrayList<PlacementOfficer> data= new ArrayList<>();

        DatabaseReference PO = FirebaseDatabase.getInstance().getReference("PlacementOfficer");

        PO.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    for(DataSnapshot productSnapshot : dataSnapshot.getChildren()){
                        PlacementOfficer p = productSnapshot.getValue(PlacementOfficer.class);
                        data.add(p);
                    }

                    poSearchadapter adapter = new poSearchadapter(search.this, data);
                    recycler.setAdapter(adapter);

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}