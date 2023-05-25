package com.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kukdookuu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private FirebaseAuth firebaseAuth;

    private EditText login_email,login_pass;
    private Button login, register;
    private TextView forgot;
    FirebaseUser firebaseUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();



        login_email=findViewById(R.id.login_email);
        login_pass=findViewById(R.id.login_pass);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        forgot=findViewById(R.id.forgot);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e= login_email.getText().toString();
                String p= login_pass.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        checkVerficationMain();
                    }
                });
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String e= forgot.getText().toString();
               if(e.isEmpty())
               {
                   Toast.makeText(LoginActivity.this, "Fill the email", Toast.LENGTH_SHORT).show();
               }
               else{
                   firebaseAuth.sendPasswordResetEmail(e).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful())
                           {
                               Toast.makeText(LoginActivity.this, "Done! Email has been sent", Toast.LENGTH_SHORT).show();
                           }
                           else{
                               Toast.makeText(LoginActivity.this, "failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail= login_email.getText().toString();
                if(mail.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Fill the email", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this, "Email has been sent", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
    private void checkVerficationMain()
    {
        FirebaseUser firebaseUser1= firebaseAuth.getCurrentUser();
        if(firebaseUser1.isEmailVerified()==true)
        {
            Toast.makeText(getApplicationContext(),"Logged",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
        else{
            Toast.makeText(getApplicationContext(),"Verify your mail first",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
    };