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
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smd_po_module.Landing_Student;
import com.example.smd_po_module.MainActivity;
import com.example.smd_po_module.PlacementOfficer;
import com.example.smd_po_module.admin;
import com.example.smd_po_module.MainActivityAdmin;
import com.example.smd_po_module.R;
import com.example.smd_po_module.student;
import com.example.smd_po_module.studentsignup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;



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
        profileImage.setImageResource(R.drawable.logo);
        progressBar = findViewById(R.id.progress);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        findViewById(R.id.signup).setOnClickListener(
                v -> startActivity(new Intent(getApplicationContext(), studentsignup.class)));
        findViewById(R.id.login).setOnClickListener(v -> validateCredentials());
        presenter = new LoginPresenter(this, new LoginInteractor());
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
        hideProgress();
        RadioGroup rg = findViewById(R.id.rg);
        int roleId = rg.getCheckedRadioButtonId();
        if (roleId == -1) {
            Toast.makeText(getApplicationContext(), "Choose a role", Toast.LENGTH_SHORT).show();
            return;
        }
        String enteredName = username.getText().toString().trim();
        getApplicationContext().getSharedPreferences("MyPref", 0)
                .edit().putString("username", enteredName).apply();

        if (roleId == R.id.radioButton) {
            signIn("student", "name", enteredName, Landing_Student.class,
                    user -> user.getValue(student.class).getPassword());
        } else if (roleId == R.id.radioButton3) {
            signIn("PlacementOfficer", "username", enteredName, MainActivity.class,
                    user -> user.getValue(PlacementOfficer.class).getPassword());
        } else {
            signIn("admin", "username", enteredName, MainActivityAdmin.class,
                    user -> user.getValue(admin.class).getPassword());
        }
    }

    private interface PasswordOf {
        String read(DataSnapshot user);
    }

    /** Looks the account up under {@code node} and opens {@code home} if the password matches. */
    private void signIn(String node, String nameField, String enteredName,
                        Class<?> home, PasswordOf passwordOf) {
        String enteredPassword = password.getText().toString().trim();
        Query query = FirebaseDatabase.getInstance().getReference(node)
                .orderByChild(nameField).equalTo(enteredName);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                    return;
                }
                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    if (enteredPassword.equals(passwordOf.read(user))) {
                        Intent intent = new Intent(getApplicationContext(), home);
                        intent.putExtra("id", user.getKey());
                        startActivity(intent);
                        return;
                    }
                }
                Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void validateCredentials() {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }
}