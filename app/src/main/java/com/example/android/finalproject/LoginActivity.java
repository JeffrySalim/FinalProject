package com.example.android.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnRegister;
    EditText edtUsername;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager
        .LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtUsername.getText())) {
                    edtUsername.setError("Masukkan Username");
                }
                if (TextUtils.isEmpty(edtPassword.getText())) {
                    edtPassword.setError("Masukkan Password");
                } else {
                    String username = edtUsername.getText().toString().trim();
                    String password = edtPassword.getText().toString();
                    String PassMd5 = MD5(password);
                    Log.d("Pass", "Password Md5 Login "+MD5(password));
                    Server.Login(username,PassMd5,LoginActivity.this);
                    if (username.equals(username) && password.equals(password)) {
                        Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_LONG).show();
                        Home();
                    } else {
                        if (password.equals(password)) {
                            Toast.makeText(LoginActivity.this, "Username Anda Salah", Toast.LENGTH_LONG).show();
                        }
                        else if (username.equals(username)) {
                            Toast.makeText(LoginActivity.this, "Password Anda Salah", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Anda Belum Terdaftar", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Home(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
