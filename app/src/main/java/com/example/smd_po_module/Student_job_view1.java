package com.example.smd_po_module;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Student_job_view1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_job_view1);
        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);
    }
}
