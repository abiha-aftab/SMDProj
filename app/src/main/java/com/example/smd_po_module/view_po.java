package com.example.smd_po_module;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class view_po extends Activity {
    Context context;
    AlertDialog.Builder builder;

    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.view_po);



        String id1 = getIntent().getStringExtra("id");
        DatabaseReference PO = FirebaseDatabase.getInstance().getReference("PlacementOfficer");

        PO.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    for(DataSnapshot productSnapshot : dataSnapshot.getChildren()){
                        PlacementOfficer p = productSnapshot.getValue(PlacementOfficer.class);
                        System.out.println("usernamee"+p.getUsername());
                        if(p.getPid().equals(id1)) {
                            TextView name= findViewById(R.id.textView4);
                            name.setText(p.getUsername());
                            TextView email= findViewById(R.id.textView5);
                            email.setText(p.getEmail());
                            TextView pass= findViewById(R.id.textView8);
                            pass.setText(p.getPassword());
                            TextView id= findViewById(R.id.textView10);
                            id.setText(p.getPid());
                            TextView degree= findViewById(R.id.textView18);
                            degree.setText(p.getQualification());
                            TextView years= findViewById(R.id.textView13);
                            //`years.setText(p.getExperience());
                            TextView tools= findViewById(R.id.textView15);
                            tools.setText(p.getTools());
                        }
                    }

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        Button but = findViewById(R.id.button1);
        builder = new AlertDialog.Builder(this);
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                builder.setMessage("Do you want to delete this account ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String id1 = getIntent().getStringExtra("id");
                                deletePO(id1);
                                finish();
                                //Toast.makeText(getApplicationContext(), "you choose yes ",
                                   //     Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                //Toast.makeText(getApplicationContext(), "you choose no ",
                                  //      Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Confirmation");
                alert.show();
            }
        });

        Button butt1 = findViewById(R.id.button);
        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("id");
                System.out.println("The id is"+id);
                Intent i = new Intent(getApplicationContext(), add_po_update.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });

    }

    private void deletePO(String id) {
        DatabaseReference po = FirebaseDatabase.getInstance().getReference("PlacementOfficer").child(id);
        po.removeValue();
        Toast.makeText(this,"PO deleted",Toast.LENGTH_LONG).show();
    }
}