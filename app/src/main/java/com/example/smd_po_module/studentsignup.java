package com.example.smd_po_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class studentsignup extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_signup);
        mDatabase = FirebaseDatabase.getInstance().getReference("student");
        id = mDatabase.push().getKey();

        this.<EditText>findViewById(R.id.editText4).setFilters(new InputFilter[]{new ValidateFilter()});
        this.<EditText>findViewById(R.id.editText6).setFilters(new InputFilter[]{new ValidateFilterNum()});
        this.<EditText>findViewById(R.id.editText7).setFilters(new InputFilter[]{new ValidateFilter()});
        this.<EditText>findViewById(R.id.editText9).setFilters(new InputFilter[]{new num()});
        this.<EditText>findViewById(R.id.editText14).setFilters(new InputFilter[]{new ValidateFilterNum()});

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
                    .setPositiveButton("Yes", (dialog, which) -> addStudent())
                    .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                    .create();
            alert.setTitle("Confirmation");
            alert.show();
        });
    }

    /** Returns a message describing the first invalid field, or null when the form is complete. */
    private String findProblem() {
        if (!FormRules.allFilled(text(R.id.editText4), text(R.id.editText6),
                text(R.id.editText12), text(R.id.editText7))) {
            return "Fill in name, password, phone and degree";
        }
        if (!FormRules.isEmail(text(R.id.editText5))) {
            return "Enter a valid email address";
        }
        if (!FormRules.isGpa(text(R.id.editText9))) {
            return "CGPA must be between 0 and 4";
        }
        if (this.<RadioGroup>findViewById(R.id.rg).getCheckedRadioButtonId() == -1) {
            return "Choose a gender";
        }
        return null;
    }

    private String text(int viewId) {
        return this.<EditText>findViewById(viewId).getText().toString().trim();
    }

    private void addStudent() {
        RadioGroup rg = findViewById(R.id.rg);
        String gender = this.<RadioButton>findViewById(rg.getCheckedRadioButtonId()).getText().toString();
        Spinner batchSpinner = findViewById(R.id.spinner);
        int batch = parseInt(batchSpinner.getSelectedItem().toString());
        String name = text(R.id.editText4);

        student s = new student(id, name, text(R.id.editText5), text(R.id.editText6),
                text(R.id.editText12), gender, checkedSkills(), text(R.id.editText7),
                text(R.id.editText9), batch, text(R.id.editText14));
        mDatabase.child(id).setValue(s);
        Toast.makeText(this, "Student added successfully", Toast.LENGTH_LONG).show();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        pref.edit().putString(name, id).apply();

        Intent in = new Intent(getApplicationContext(), Landing_Student.class);
        in.putExtra("id", id);
        startActivity(in);
        finish();
    }

    private String checkedSkills() {
        List<String> skills = new ArrayList<>();
        for (int boxId : new int[]{R.id.checkBox, R.id.checkBox1, R.id.checkBox2, R.id.checkBox3}) {
            CheckBox box = findViewById(boxId);
            if (box.isChecked()) {
                skills.add(box.getText().toString());
            }
        }
        return TextUtils.join(", ", skills);
    }
}
