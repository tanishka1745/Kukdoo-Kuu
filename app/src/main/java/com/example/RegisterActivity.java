package com.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.kukdookuu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText reg_email,reg_pass,reg_re_pass;
    FirebaseAuth firebaseAuth;
    private Button register_account,back_tologin;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg_email=findViewById(R.id.reg_email);
        reg_pass=findViewById(R.id.reg_pass);
        back_tologin=findViewById(R.id.back_tologin);

        reg_re_pass=findViewById(R.id.reg_re_pass);
        register_account=findViewById(R.id.registered);
        firebaseAuth=FirebaseAuth.getInstance();
        back_tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        register_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e=reg_email.getText().toString();
                String p=reg_pass.getText().toString();
                String r=reg_re_pass.getText().toString();
                if(p.equals(r))
                {
                    firebaseAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            sendEmailVerification();
                        }
                    });
                }

            }
        });
    }
    private void sendEmailVerification()
    {
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"Verification Email is sent, Verify and Log In again",Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Failed to sent verification Email",Toast.LENGTH_SHORT).show();
        }
    }
}