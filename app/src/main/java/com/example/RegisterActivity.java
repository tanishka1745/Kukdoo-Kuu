package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.kukdookuu.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText reg_email,reg_pass,reg_re_email;
    private Button register_account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg_email=findViewById(R.id.reg_email);
        reg_pass=findViewById(R.id.reg_pass);
        reg_re_email=findViewById(R.id.reg_re_pass);
        register_account=findViewById(R.id.register_account);
        register_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String e= reg_email.getText().toString();
                String p= reg_pass.getText().toString();
                String rp= reg_re_email.getText().toString();

                if(e.isEmpty() || p.isEmpty() || rp.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Fill all the field", Toast.LENGTH_SHORT).show();
                }
                else if(p.equals(rp))
                {
                    BackendlessUser user= new BackendlessUser();
                    user.setEmail(e);
                    user.setPassword(p);
                    Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            RegisterActivity.this.finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(RegisterActivity.this, "error:"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}