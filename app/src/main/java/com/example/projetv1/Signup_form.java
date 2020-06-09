package com.example.projetv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_form extends AppCompatActivity {
    EditText txtEmail,txtPassword,txtConfirmPassword;
    Button btn_register;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        getSupportActionBar().setTitle("Sign up Form");

        txtEmail=(EditText)findViewById(R.id.txt_email);
        txtPassword=(EditText)findViewById(R.id.txt_password);
        txtConfirmPassword=(EditText)findViewById(R.id.confirm);
        btn_register=(Button)findViewById(R.id.btn_register);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);


        firebaseAuth=FirebaseAuth.getInstance();



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txtEmail.getText().toString().trim();
                String password= txtPassword.getText().toString().trim();
                String confirPassord=txtConfirmPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText( Signup_form.this,"Please Enter Email",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText( Signup_form.this,"Please Enter Password",Toast.LENGTH_LONG).show();
                    return;
                }


                if(TextUtils.isEmpty(confirPassord)){
                    Toast.makeText( Signup_form.this,"Please Enter Confirm Password",Toast.LENGTH_LONG).show();
                    return;
                }


                if(password.length()<5){
                    Toast.makeText(Signup_form.this,"Password too short",Toast.LENGTH_LONG).show();
                }


                progressBar.setVisibility(View.VISIBLE);

                if(password.equals(confirPassord)){



                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(Signup_form.this,"Registration complete",Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(Signup_form.this,"Authentification failed",Toast.LENGTH_LONG).show();
                                    }

                                    // ...
                                }
                            });








                }

            }
        });




    }
}
