package com.example.smd_po_module;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_po extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_po);
        mDatabase = FirebaseDatabase.getInstance().getReference("PlacementOfficer");
        id = mDatabase.push().getKey();

        this.<EditText>findViewById(R.id.editText4).setFilters(new InputFilter[]{new ValidateFilter()});
        this.<EditText>findViewById(R.id.editText6).setFilters(new InputFilter[]{new ValidateFilterNum()});
        this.<EditText>findViewById(R.id.editText7).setFilters(new InputFilter[]{new ValidateFilter()});
        this.<EditText>findViewById(R.id.editText9).setFilters(new InputFilter[]{new ValidateFilter()});

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            String problem = findProblem();
            if (problem != null) {
                Toast.makeText(getApplicationContext(), problem, Toast.LENGTH_SHORT).show();
                return;
            }
            AlertDialog alert = new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to create the account?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, which) -> addPO())
                    .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                    .create();
            alert.setTitle("Confirmation");
            alert.show();
        });
    }

    private String findProblem() {
        if (!FormRules.allFilled(text(R.id.editText4), text(R.id.editText6),
                text(R.id.editText7), text(R.id.editText9))) {
            return "Fill in name, password, degree and tools";
        }
        if (!FormRules.isEmail(text(R.id.editText5))) {
            return "Enter a valid email address";
        }
        if (FormRules.parseYears(text(R.id.editText8)) < 0) {
            return "Years of experience must be a whole number";
        }
        return null;
    }

    private String text(int viewId) {
        return this.<EditText>findViewById(viewId).getText().toString().trim();
    }

    private void addPO() {
        PlacementOfficer po = new PlacementOfficer(text(R.id.editText5), text(R.id.editText6),
                text(R.id.editText7), text(R.id.editText9), text(R.id.editText4), id,
                FormRules.parseYears(text(R.id.editText8)));
        mDatabase.child(id).setValue(po);
        Toast.makeText(this, "Officer added successfully", Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), view_po.class);
        i.putExtra("id", id);
        startActivity(i);
        finish();
    }
}
