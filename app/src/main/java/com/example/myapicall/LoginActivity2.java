package com.example.myapicall;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapicall.ApiClient.RetrofitApiClient;
import com.example.myapicall.LoginInPackage.LoginRequestModel;
import com.example.myapicall.LoginInPackage.LoginResponseModel;
import com.example.myapicall.SignUpPackage.SignupResponseModel;
import com.example.myapicall.utils.Utils;
import com.google.gson.Gson;

import java.io.IOException;


public class LoginActivity2 extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private Button loginButton;
    private ProgressBar ProgressBar;

    private static final String TAG = "LoginActivity2";

    String username, password;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        initView();
        initListeners();

    }


    public void initView() {
        editTextUserName = findViewById(R.id.et_loginUserName);
        editTextPassword = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.btn_login);
        ProgressBar = (ProgressBar) findViewById(R.id.Loading);
    }

    public void initListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = editTextUserName.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                createPostRequest(username, password);
            }
        });
    }

    private void createPostRequest(String username, String password) {
        Utils.showProgressDialog(LoginActivity2.this);

        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setUsername(username);
        loginRequestModel.setPassword(password);

        Log.d(TAG, "create loginRequestPost" + loginRequestModel);

        Call<LoginResponseModel> loginResponseModelCall = RetrofitApiClient.getWebService().getLoginData(loginRequestModel);
        loginResponseModelCall.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "LoginResponse" + response.body());

                    String successMessage = response.body().getMessage();
                    Toast.makeText(LoginActivity2.this, successMessage, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity2.this, DashboardActivity.class);
                    startActivity(intent);
                } else {
                    LoginResponseModel loginResponseModel = null;
                    String jsonString = null;

                    try {
                        if (response.errorBody() != null) {
                            jsonString = response.errorBody().string();
                        }
                        if (jsonString != null) {
                            loginResponseModel = new Gson().fromJson(jsonString, LoginResponseModel.class);
                        }
                        if (loginResponseModel != null) {
                            Toast.makeText(LoginActivity2.this, loginResponseModel.getErrors().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                Utils.dismissProgressDialog();
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Toast.makeText(LoginActivity2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Login response: " + t.getMessage());
                Utils.dismissProgressDialog();
            }
        });
    }
}