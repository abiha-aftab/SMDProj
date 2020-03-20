package com.example.smd_po_module;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class studentsignup extends AppCompatActivity {
    Context context;
    AlertDialog.Builder builder;
    DatabaseReference mDatabase;
    String id;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    int a=1;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_signup);
        mDatabase = FirebaseDatabase.getInstance().getReference("student");
        id= mDatabase.push().getKey();

        builder = new AlertDialog.Builder(this);
        EditText editText3 = findViewById(R.id. editText9 ) ;
        editText3.setFilters( new InputFilter[]{ new num()}) ;
       // EditText editText4 = findViewById(R.id. editText16 ) ;
        //editText4.setFilters( new InputFilter[]{ new ValidateFilterNum()}) ;
        EditText editText5 = findViewById(R.id. editText14 ) ;
        editText5.setFilters( new InputFilter[]{ new ValidateFilterNum()}) ;
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            EditText emailId = findViewById(R.id.editText5);
            @Override
            public void onClick(View v) {
                EditText editText1 = findViewById(R.id. editText6 ) ;
                editText1.setFilters( new InputFilter[]{ new ValidateFilterNum()}) ;
                if(editText1.getText().toString().isEmpty()) {
                    a=0;
                    //Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT).show();
                }
                else
                    a=1;

                EditText editText12 = findViewById(R.id. editText12 ) ;
                if(editText12.getText().toString().isEmpty()) {
                    a=0;
                    //Toast.makeText(getApplicationContext(), "Phone is required", Toast.LENGTH_SHORT).show();
                }
                else
                    a=1;

                EditText editText = findViewById(R.id. editText4 ) ;
                editText.setFilters( new InputFilter[]{ new ValidateFilter()}) ;
                if(editText.getText().toString().isEmpty()) {
                    a=0;
                    //Toast.makeText(getApplicationContext(), "Name is required", Toast.LENGTH_SHORT).show();
                }
                else
                    a=1;
                if(emailId.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }else {
                    if (emailId.getText().toString().trim().matches(emailPattern) && a==1) {
                        Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();

                        builder.setMessage("Are you Sure you want to create account ?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        addStudent();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                        Toast.makeText(getApplicationContext(), "you chose no ",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                        //Creating dialog box
                        AlertDialog alert = builder.create();
                        //Setting the title manually
                        alert.setTitle("Confirmation");
                        alert.show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid email address or field empty", Toast.LENGTH_SHORT).show();
                    }

                    }
                }
        });

        EditText editText2 = findViewById(R.id. editText7 ) ;
        editText2.setFilters( new InputFilter[]{ new ValidateFilter()}) ;

        if(savedInstanceState!= null){
            String myString7 = savedInstanceState.getString("MyString7");
            String myString8 = savedInstanceState.getString("MyString8");

            editText3.setText(myString8);
            editText2.setText(myString7);
        }
    }

    public void addStudent(){
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
            mDatabase.child(id).setValue(s);
            Toast.makeText(this,"student added successfully",Toast.LENGTH_LONG).show();
            Intent in = new Intent(getApplicationContext(), Landing_Student.class);
            System.out.println("ID IS "+ id);
            in.putExtra("id", id);
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(name, id); // Storing string
            editor.commit();
            startActivity(in);
            finish();
        }
        else
        {
            Toast.makeText(this,"enter all values",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        EditText editText = findViewById(R.id. editText4 );
        String text= editText.getText().toString();
        savedInstanceState.putString("MyString3", text);

        EditText editText1 = findViewById(R.id. editText5 );
        String text1= editText1.getText().toString();
        savedInstanceState.putString("MyString4", text1);

        EditText editText2 = findViewById(R.id. editText5 );
        String text2= editText2.getText().toString();
        savedInstanceState.putString("MyString5", text2);

        EditText editText6 = findViewById(R.id. editText6 );
        String text6= editText6.getText().toString();
        savedInstanceState.putString("MyString6", text6);

        EditText editText7 = findViewById(R.id. editText7 );
        String text7= editText7.getText().toString();
        savedInstanceState.putString("MyString7", text7);

        EditText editText8 = findViewById(R.id. editText8 );
        String text8= editText8.getText().toString();
        savedInstanceState.putString("MyString8", text8);

        EditText editText9 = findViewById(R.id. editText9 );
        String text9= editText9.getText().toString();
        savedInstanceState.putString("MyString9", text9);
        // etc.
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        String myString3 = savedInstanceState.getString("MyString3");
        String myString4 = savedInstanceState.getString("MyString4");
        String myString5 = savedInstanceState.getString("MyString5");
        String myString6 = savedInstanceState.getString("MyString6");
        String myString7 = savedInstanceState.getString("MyString7");
        String myString8 = savedInstanceState.getString("MyString8");
        String myString9 = savedInstanceState.getString("MyString9");

    }
}
