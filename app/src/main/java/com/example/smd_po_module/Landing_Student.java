package com.example.smd_po_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smd_po_module.ui.login.PO_login;
import com.squareup.picasso.Picasso;

public class Landing_Student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing__student);


        String id = "0000";
               id= getIntent().getStringExtra("id");
        System.out.println("id in intent "+id);
        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.po_profile).into(profileImage);
        TextView po_name = findViewById(R.id.po_name);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String name= pref.getString("username", null);
        if((id)==null) {
            id = pref.getString(name, null);
            System.out.println("id in SP "+id);
        }
        po_name.setText(name);
        TextView po_role = findViewById(R.id.po_role);
        po_role.setText("Student");
        Button b1;
        b1=findViewById(R.id.add_Company);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Student_list_job.class);
                startActivity(i);
            }
        });

        Button b2;
        b2=findViewById(R.id.notify);
        String finalId = id;
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), student_update .class);
                //System.out.println("IDDD"+id);
                i.putExtra("id", finalId);
                startActivity(i);
            }
        });
        Button b3;
        b3=findViewById(R.id.add_job);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Global.intent1='y';

                Intent i = new Intent(getApplicationContext(), list_Jobs.class);
                startActivity(i);

            }
        });
        Button b4;
        b4=findViewById(R.id.add_selected);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.intent='c';
                Global.intent1='c';
                Intent i = new Intent(getApplicationContext(), list_student .class);
                startActivity(i);
            }
        });
        Button b5;
        b5=findViewById(R.id.log_out);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PO_login.class);
                startActivity(i);
            }
        });




    }
    }

