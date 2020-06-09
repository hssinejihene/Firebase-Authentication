package com.example.projetv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_form extends AppCompatActivity {
    EditText txtEmail,txtPassword;
    Button btn_login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Login Form");

        txtEmail=(EditText)findViewById(R.id.txt_email);
        txtPassword=(EditText)findViewById(R.id.txt_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        firebaseAuth=FirebaseAuth.getInstance();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txtEmail.getText().toString().trim();
                String password= txtPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText( login_form.this,"Please Enter Email",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText( login_form.this,"Please Enter Password",Toast.LENGTH_LONG).show();
                    return;
                }


                if(password.length()<5){
                    Toast.makeText(login_form.this,"Password too short",Toast.LENGTH_LONG).show();
                }






                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login_form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));


                                } else {
                                    Toast.makeText(login_form.this,"Login failed or User not Available",Toast.LENGTH_LONG).show();


                                }


                            }
                        });










            }
        });






    }


    public void btn_Signup_form(View view) {
        startActivity(new Intent(getApplicationContext(),Signup_form.class));
    }
}