package com.example.smd_po_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.smd_po_module.select_job_notify;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smd_po_module.ui.login.PO_login;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.po_profile).into(profileImage);
        TextView po_name = findViewById(R.id.po_name);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String name= pref.getString("username", null);
        po_name.setText(name);
        TextView po_role = findViewById(R.id.po_role);
        po_role.setText("Placement Officer");
        Button b1;
        b1=findViewById(R.id.add_Company);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Add_Job.class);
                startActivity(i);
            }
        });

        Button b2;
        b2=findViewById(R.id.notify);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), select_job_notify.class);
                startActivity(i);
            }
        });
        Button b3;
        b3=findViewById(R.id.add_job);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Search_company .class);
                startActivity(i);
            }
        });
        Button b4;
        b4=findViewById(R.id.add_selected);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Add_selected1 .class);
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
