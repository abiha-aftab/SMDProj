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



public class view_po extends Activity {
    Context context;
    AlertDialog.Builder builder;

    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.view_po);



        String id1 = getIntent().getStringExtra("id");
        if (id1 == null) {
            finish();
            return;
        }
        DatabaseReference officer = FirebaseDatabase.getInstance()
                .getReference("PlacementOfficer").child(id1);
        officer.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PlacementOfficer p = dataSnapshot.getValue(PlacementOfficer.class);
                if (p == null) {
                    Toast.makeText(getApplicationContext(), "Officer not found", Toast.LENGTH_LONG).show();
                    return;
                }
                ((TextView) findViewById(R.id.textView4)).setText(p.getUsername());
                ((TextView) findViewById(R.id.textView5)).setText(p.getEmail());
                // Never echo the stored password back onto the screen.
                ((TextView) findViewById(R.id.textView8)).setText("********");
                ((TextView) findViewById(R.id.textView10)).setText(p.getPid());
                ((TextView) findViewById(R.id.textView18)).setText(p.getQualification());
                ((TextView) findViewById(R.id.textView13)).setText(
                        getResources().getQuantityString(R.plurals.years_experience,
                                p.getExperience(), p.getExperience()));
                ((TextView) findViewById(R.id.textView15)).setText(p.getTools());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database error", Toast.LENGTH_LONG).show();
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
                Intent i = new Intent(getApplicationContext(), add_po_update.class);
                i.putExtra("id", id1);
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