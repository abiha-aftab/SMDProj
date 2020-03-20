/*
 *
 *  * Copyright (C) 2018 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.example.smd_po_module.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smd_po_module.Api;
import com.example.smd_po_module.Hero;
import com.example.smd_po_module.Landing_Student;
import com.example.smd_po_module.MainActivity;
import com.example.smd_po_module.PlacementOfficer;
import com.example.smd_po_module.ValidateFilter;
import com.example.smd_po_module.admin;
import com.example.smd_po_module.retrofit;
import com.example.smd_po_module.MainActivityAdmin;
import com.example.smd_po_module.R;
import com.example.smd_po_module.repository.ScreenTimeRepo;
import com.example.smd_po_module.student;
import com.example.smd_po_module.studentsignup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PO_login extends AppCompatActivity implements LoginView {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po_login);

        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);
        progressBar = findViewById(R.id.progress);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {

                                                             Intent i= new Intent(getApplicationContext(), studentsignup.class);
                                                                startActivity(i);
                                                         }
                                                     });
        getData();
        findViewById(R.id.login).setOnClickListener(v -> validateCredentials());
        presenter = new LoginPresenter(this, new LoginInteractor());
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();



        Api api = retrofit.create(Api.class);

        Call<List<Hero>> call = api.getData();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroList = response.body();
                System.out.println("RESPONSE "+response.body());

                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getStatus_code();


                    ScreenTimeRepo screenTimeRepo = new ScreenTimeRepo(PO_login.this);
                    String status_code= heroList.get(i).getStatus_code();
                    String version = heroList.get(i).getVersion();
                    String app_meta_data = heroList.get(i).getApp_meta_name();
                    screenTimeRepo.insertTask(status_code,version,app_meta_data);
                    screenTimeRepo.getTask(status_code);
                }
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failedd", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {


        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        RadioButton radiob;
        String module = null;
        final String value =
                ((RadioButton) findViewById(rg.getCheckedRadioButtonId()))
                        .getText().toString();
        int selectedId = rg.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radiob = (RadioButton) findViewById(selectedId);
        module = radiob.getText().toString();


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        EditText e = findViewById(R.id.username);
        editor.putString("username", e.getText().toString()); // Storing string
        /*rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               module[0] =value;
            }
        });*/
        ;
        editor.commit();
        if (module.equals("As Student")) {


            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("student");

            Query query = databaseReference.orderByChild("name").equalTo(username.getText().toString().trim());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // dataSnapshot is the "issue" node with all children with id 0

                        for (DataSnapshot user : dataSnapshot.getChildren()) {
                            // do something with the individual "issues"
                            student p = user.getValue(student.class);

                            if (p.getPassword().equals(password.getText().toString().trim())) {


                                System.out.println("student");
                                Intent i = new Intent(getApplicationContext(), Landing_Student.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Database error", Toast.LENGTH_LONG).show();
                }
            });


        } else if (module.equals("As PO")) {


            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PlacementOfficer");

            Query query = databaseReference.orderByChild("username").equalTo(username.getText().toString().trim());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // dataSnapshot is the "issue" node with all children with id 0

                        for (DataSnapshot user : dataSnapshot.getChildren()) {
                            // do something with the individual "issues"
                            PlacementOfficer p = user.getValue(PlacementOfficer.class);

                            if (p.getPassword().equals(password.getText().toString().trim())) {


                                System.out.println("PO");
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Database error", Toast.LENGTH_LONG).show();
                }
            });

        } else if (module.equals("As Admin")) {


            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("admin");

            Query query = databaseReference.orderByChild("username").equalTo(username.getText().toString().trim());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // dataSnapshot is the "issue" node with all children with id 0

                        for (DataSnapshot user : dataSnapshot.getChildren()) {
                            // do something with the individual "issues"
                            admin p = user.getValue(admin.class);
                            System.out.println(p.getPassword());

                            if (p.getPassword().equals(password.getText().toString().trim())) {
                                System.out.println("admin");
                                Intent i = new Intent(getApplicationContext(), MainActivityAdmin.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Database error", Toast.LENGTH_LONG).show();
                }
            });

        }
    }
    private void validateCredentials() {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }
}