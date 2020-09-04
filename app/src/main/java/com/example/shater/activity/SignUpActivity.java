package com.example.shater.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shater.R;
import com.example.shater.helper.CacheJson;
import com.example.shater.models.providerInfo;
import com.example.shater.models.userInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    Intent intent ;
    CacheJson cacheJson ;
    Button signUp_btn;
    EditText name_edt, email_edt , password_edt, phone_edt , exper_edt  ;
    Spinner sp_categrayProvider ;
    String catogry ,experience = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        intent = getIntent();
        final String user = intent.getStringExtra("user");

        signUp_btn = (Button) findViewById(R.id.signup_btn);
        name_edt = (EditText) findViewById(R.id.name_edt);
        email_edt = (EditText) findViewById(R.id.email_edt);
        password_edt = (EditText) findViewById(R.id.password_edt);
        phone_edt = (EditText) findViewById(R.id.phone_edt);
        exper_edt = (EditText) findViewById(R.id.exper_edt);
        sp_categrayProvider = (Spinner) findViewById(R.id.sp_categrayProvider);

        if(user.equals("Service Provider")){
            exper_edt.setVisibility(View.VISIBLE);
            sp_categrayProvider.setVisibility(View.VISIBLE);
            final String []category = {"Home decor","Technology","Maintenance","Painting","Parking shades","Electricity"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,category);
            sp_categrayProvider.setAdapter(adapter);
            sp_categrayProvider.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    catogry = (String)adapterView.getItemAtPosition(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }



        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String name = name_edt.getText().toString().trim();
                final String email = email_edt.getText().toString().trim();
                final String password = password_edt.getText().toString().trim();
                final String phone = phone_edt.getText().toString().trim();

                if (user.equals("Service Provider")) {
                    experience = exper_edt.getText().toString().trim();

                }

                //set information of user

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }


                //create user

                if (!user.equals("Admin")){
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                cacheJson = new CacheJson();

                                if (user.equals("customer")){
                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user").child("info");
                                    String userId = mDatabase.push().getKey();
                                    userInfo info = new userInfo(name, email, password, user, phone, userId);
                                    // pushing user to 'users' node using the userId
                                    mDatabase.child(userId).setValue(info);
                                    try {
                                        cacheJson.writeObject(SignUpActivity.this, "info", info);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    startActivity(new Intent(SignUpActivity.this, HomeCustomerActivity.class));
                                    finish();
                                }
                                else if (user.equals("Service Provider")){
                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("provider").child("info");

                                    String userId = mDatabase.push().getKey();

                                    providerInfo info = new providerInfo(name, email, password, user, phone, userId,catogry,experience);
                                    // pushing user to 'users' node using the userId
                                    mDatabase.child(userId).setValue(info);
                                    try {
                                        cacheJson.writeObject(SignUpActivity.this, "info", info);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    startActivity(new Intent(SignUpActivity.this, HomeServiceActivity.class));
                                    finish();
                                }

                            }
                        }
                    });

            }else {
                    startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                    finish();
                }

            }
        });
    }
}

