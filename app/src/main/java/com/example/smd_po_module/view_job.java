package com.example.smd_po_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class view_job extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job);
        Button b2;
        b2=findViewById(R.id.notify);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), notify_students1.class);
                startActivity(i);
            }
        });
        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);

    }
}
