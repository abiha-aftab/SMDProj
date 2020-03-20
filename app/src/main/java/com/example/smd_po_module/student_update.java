package com.example.smd_po_module;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Update;

import com.example.smd_po_module.ui.login.PO_login;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Integer.parseInt;

public class student_update extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    DatabaseReference mDatabase;
    String id;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_update);
        id = getIntent().getStringExtra("id");
        mDatabase = FirebaseDatabase.getInstance().getReference("student").child(id);

        EditText editText = findViewById(R.id. editText4 ) ;
        editText.setFilters( new InputFilter[]{ new ValidateFilter()}) ;
        EditText editText3 = findViewById(R.id. editText9 ) ;
        editText3.setFilters( new InputFilter[]{ new num()}) ;
        EditText editText5 = findViewById(R.id. editText14 ) ;
        editText5.setFilters( new InputFilter[]{ new ValidateFilterNum()}) ;

        AlertDialog.Builder builder;
        Button button1 = findViewById(R.id.button1);
        builder = new AlertDialog.Builder(this);
        button1.setOnClickListener(new View.OnClickListener() {

                                      @Override
                                      public void onClick(View view) {
                                          builder.setMessage("Do you want to delete this account ?")
                                                  .setCancelable(false)
                                                  .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                      public void onClick(DialogInterface dialog, int id) {
                                                          String id1 = getIntent().getStringExtra("id");
                                                          deleteStudent(id1);
                                                          Toast.makeText(getApplicationContext(),"Account Deactivated", Toast.LENGTH_LONG).show();
                                                          Intent i = new Intent(getApplicationContext(), PO_login.class);
                                                          startActivity(i);
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


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            EditText emailId = findViewById(R.id.editText5);
            @Override
            public void onClick(View v) {
                if(emailId.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }else {
                    if (emailId.getText().toString().trim().matches(emailPattern)) {
                        UpdateStudent();
                       // Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        EditText editText1 = findViewById(R.id. editText6 ) ;
        editText1.setFilters( new InputFilter[]{ new ValidateFilterNum()}) ;
        EditText editText12 = findViewById(R.id. editText12 ) ;
        if(editText12.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Phone is required", Toast.LENGTH_SHORT).show();
        }

        EditText editText2 = findViewById(R.id. editText7 ) ;
        editText2.setFilters( new InputFilter[]{ new ValidateFilter()}) ;
    }
    public void UpdateStudent(){
        String skills = "";
        int i=0;
        EditText e= findViewById(R.id.editText4);
        String name= e.getText().toString();
        EditText e1= findViewById(R.id.editText5);
        String email= e1.getText().toString();
        EditText e2= findViewById(R.id.editText6);
        String password= e2.getText().toString();
        EditText e3= findViewById(R.id.editText12);
        String phone= e3.getText().toString();
        RadioGroup rg= findViewById(R.id.rg);
        String gender = null;
        final String value =
                ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))
                        .getText().toString();
        int selectedId = rg.getCheckedRadioButtonId();
        RadioButton radiob;

        // find the radiobutton by returned id
        radiob = (RadioButton) findViewById(selectedId);
        gender=radiob.getText().toString();
        EditText e4= findViewById(R.id.editText7);
        String degree= e4.getText().toString();
        EditText e5= findViewById(R.id.editText9);
        String gpa= e5.getText().toString();
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        int year = parseInt(mySpinner.getSelectedItem().toString());
        CheckBox cb= findViewById(R.id.checkBox);
        String web;
        if(cb.isChecked()){
            web=cb.getText().toString();
            skills=web;
        }
        String android;
        if(cb.isChecked()){
            android=cb.getText().toString();
            skills=android;
        }
        String database;
        if(cb.isChecked()){
            database=cb.getText().toString();
            skills=database;
        }
        String networking;
        if(cb.isChecked()){
            networking=cb.getText().toString();
            skills=networking;
        }

        EditText et= findViewById(R.id.editText14);
        String experience= (et.getText().toString());


        if((!TextUtils.isEmpty(name))&&(!TextUtils.isEmpty(email))&&(!TextUtils.isEmpty(password))&&(!TextUtils.isEmpty(degree))){
            student s= new student(id, name,  email, password, phone, gender, skills, degree, gpa, year, experience);
            mDatabase.setValue(s);
            Toast.makeText(this,"Updated successfully",Toast.LENGTH_LONG).show();
            Intent in = new Intent(getApplicationContext(), Landing_Student.class);
            System.out.println("ID IS "+ id);
            in.putExtra("id", id);
            startActivity(in);
            finish();
        }
        else
        {
            Toast.makeText(this,"enter all values",Toast.LENGTH_LONG).show();
        }
    }

    private void deleteStudent(String id) {
        DatabaseReference po = FirebaseDatabase.getInstance().getReference("student").child(id);
        po.removeValue();
        Toast.makeText(this,"Account deactivated",Toast.LENGTH_LONG).show();
    }
}
