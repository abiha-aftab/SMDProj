package com.example.smd_po_module;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smd_po_module.imageutils.ImageLoader;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_po extends AppCompatActivity {
    Context context;
    AlertDialog.Builder builder;
    DatabaseReference mDatabase;
    String id;
    ImageView imgView;
    ImageLoader imgLoader;
    int a=1;
    private String strURL = "http://technotalkative.com/android.jpg";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_po);
        mDatabase = FirebaseDatabase.getInstance().getReference("PlacementOfficer");
        id= mDatabase.push().getKey();
        System.out.println("IDDDDD"+id);

        builder = new AlertDialog.Builder(this);


        EditText editText3 = findViewById(R.id. editText9 ) ;
        editText3.setFilters( new InputFilter[]{ new ValidateFilter()}) ;
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            EditText emailId = findViewById(R.id.editText5);
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id. editText4 ) ;
                editText.setFilters( new InputFilter[]{ new ValidateFilter()}) ;
                String s1= editText.getText().toString();
                if(s1.isEmpty()) {
                    a=0;
                   // Toast.makeText(getApplicationContext(), "Name is required", Toast.LENGTH_SHORT).show();
                }
                else
                    a=1;

                EditText editText1 = findViewById(R.id. editText6 ) ;
                editText1.setFilters( new InputFilter[]{ new ValidateFilterNum()}) ;

                if(editText1.getText().toString().isEmpty()) {
                    a=0;
                    //Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT).show();
                }
                else
                    a=1;
                if(emailId.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }

                else {
                    if (emailId.getText().toString().trim().matches(emailPattern) && a==1) {
                        //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                        builder.setMessage("Are you Sure you want to create account ?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        addPO();

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
         imgView = (ImageView) findViewById(R.id.imageView2);
        imgLoader = new ImageLoader(this);

        imgLoader.DisplayImage(strURL, imgView);
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

    private void addPO(){
        EditText uname=findViewById(R.id. editText4);
        String name= uname.getText().toString().trim();
        EditText uemail=findViewById(R.id. editText5);
        String email= uemail.getText().toString().trim();
        EditText upassword=findViewById(R.id. editText6);
        String pass= upassword.getText().toString().trim();
        EditText udegree=findViewById(R.id. editText7);
        String degree= udegree.getText().toString().trim();
        EditText utools=findViewById(R.id. editText9);
        String tool= utools.getText().toString().trim();
        EditText uyears=findViewById(R.id. editText8);
        int years= Integer.parseInt(uyears.getText().toString().trim());
        if((!TextUtils.isEmpty(name))&&(!TextUtils.isEmpty(email))&&(!TextUtils.isEmpty(pass))&&(!TextUtils.isEmpty(degree))&&(!TextUtils.isEmpty(tool))){
            System.out.println("IDI"+id);
           PlacementOfficer po= new PlacementOfficer(email,pass,degree,tool,name,id,years);
           mDatabase.child(id).setValue(po);
            Toast.makeText(this,"Officer added successfully",Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), view_po.class);
            System.out.println("ID IS "+ id);
            i.putExtra("id", id);
            startActivity(i);
            finish();
        }
        else
        {
            Toast.makeText(this,"enter all values",Toast.LENGTH_LONG).show();
        }
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
