package com.example.android.finalproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText pass;
    EditText user;
    EditText email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.btnRegister);
        pass = findViewById(R.id.edtPasswordRegister);
        user = findViewById(R.id.edtUsernameRegister);
        email = findViewById(R.id.edtEmailRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onclick", "onClick");
                String username = user.getText().toString().trim();
                String emailuser = email.getText().toString().trim();
                String password = pass.getText().toString();
                String passEncript = Encriptor.MD5(password);

                Server.Daftar(passEncript, username, emailuser, RegisterActivity.this);
            }
        });
    }
}
