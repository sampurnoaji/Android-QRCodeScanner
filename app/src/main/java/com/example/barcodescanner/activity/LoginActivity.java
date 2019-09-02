package com.example.barcodescanner.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barcodescanner.R;

import model.LoginRequest;
import model.ResponseType;
import model.User;
import network.Client;
import network.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText username, password;
    private Button btnLogin;
    private Service service;
    private User user;
    private LoginRequest loginRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "login click", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(LoginActivity.this, UserActivity.class));
//                finish();
                requestUser();
            }
        });
    }

    private void bindView() {
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_btn);
    }

    private void requestUser(){
        service = Client.getClient().create(Service.class);
        Call<ResponseType> loginRequestCall = service.loginRequest("permadi", "Secret123!");
        loginRequestCall.enqueue(new Callback<ResponseType>() {
            @Override
            public void onResponse(Call<ResponseType> call, Response<ResponseType> response) {
                ResponseType responseType = response.body();
                Toast.makeText(LoginActivity.this, ""+responseType.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseType> call, Throwable t) {

            }
        });
    }
}
