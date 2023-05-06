package com.example;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.kukdookuu.R;

public class LoginActivity extends AppCompatActivity {

    private ActionBar actionBar;

    private EditText login_email,login_pass;
    private Button login, register;
    private TextView forgot;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("red"));
        actionBar.setBackgroundDrawable(colorDrawable);

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
                if(e.isEmpty() || p.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Fill the field", Toast.LENGTH_SHORT).show();
                }
                else{
                    Backendless.UserService.login(e, p, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            LoginActivity.this.finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(LoginActivity.this, "Error:"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
       forgot.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String e= login_email.getText().toString();
               if(e.isEmpty())
               {
                   Toast.makeText(LoginActivity.this, "Fill email", Toast.LENGTH_SHORT).show();
               }
               else{
                   Backendless.UserService.restorePassword(e, new AsyncCallback<Void>() {
                       @Override
                       public void handleResponse(Void response) {
                           Toast.makeText(LoginActivity.this, "Sent Successfully", Toast.LENGTH_SHORT).show();
                       }

                       @Override
                       public void handleFault(BackendlessFault fault) {
                           Toast.makeText(LoginActivity.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   });
               }
           }
       });

    }
}