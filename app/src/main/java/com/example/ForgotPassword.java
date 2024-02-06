package com.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kukdookuu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText r_email;
    private FirebaseAuth firebaseAuth;

    private Button forgotP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        r_email=findViewById(R.id.reset_email);
        firebaseAuth=FirebaseAuth.getInstance();
        forgotP=findViewById(R.id.reset);
        forgotP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e=r_email.getText().toString();
                if(e.isEmpty())
                {
                    Toast.makeText(ForgotPassword.this, "Enter email here", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(e).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful())
                           {
                               Toast.makeText(ForgotPassword.this, "Email is sent", Toast.LENGTH_SHORT).show();
                           }
                           else{
                               Toast.makeText(ForgotPassword.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                           }
                        }
                    });
                }
            }
        });


    }
}