package com.example.myapicall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapicall.ApiClient.RetrofitApiClient;
import com.example.myapicall.SignUpPackage.SignupRequestModel;
import com.example.myapicall.SignUpPackage.SignupResponseModel;
import com.example.myapicall.utils.Utils;
import com.google.gson.Gson;

import java.io.IOException;

public class SignUpActivity2 extends AppCompatActivity {
    //      Widget   Object
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneNumberEditText;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private AppCompatButton signupButton;

    ProgressDialog progressDialog;


    //Declare the activity global string TAG
    private static final String TAG = "SignupActivity2";

    // also declare the string object global
    String firstName, lastName, phoneNumber, userName, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);


        //init initView && initListener
        initView();
        initListeners();

    }

    public void initView() {

        // Instantiate the object
        firstNameEditText = findViewById(R.id.et_first_name);
        lastNameEditText = findViewById(R.id.et_Last_name);
        phoneNumberEditText = findViewById(R.id.et_Phone_number);
        userNameEditText = findViewById(R.id.et_user_name);
        passwordEditText = findViewById(R.id.et_password);
        signupButton = findViewById(R.id.btn_signup_button);


    }

    //set a click listener on the button and get find your getText().toSting () and trim () to all the object..
    // create a postRequest for the whole object fields
    public void initListeners() {
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = firstNameEditText.getText().toString().trim();
                lastName = lastNameEditText.getText().toString().trim();
                phoneNumber = phoneNumberEditText.getText().toString().trim();
                userName = userNameEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();

                createPostRequest(firstName, lastName, phoneNumber, userName, password);
            }
        });

    }

    // create a post request of signupRequestModel and set the object/Log.d pls signupRequestModel
    private void createPostRequest(String firstName, String lastName, String phoneNumber,
                                   String userName, String password) {
        SignupRequestModel signupRequestModel = new SignupRequestModel();
        signupRequestModel.setFirstName(firstName);
        signupRequestModel.setLastName(lastName);
        signupRequestModel.setPhoneNumber(phoneNumber);
        signupRequestModel.setUsername(userName);
        signupRequestModel.setPassword(password);

        Utils.showProgressDialog(this);

        Log.d(TAG, "create signupRequestPost" + signupRequestModel);


        // we are calling the Webservice call<SignupResponseModel> plus the RetrofitAPI,getting the webservice method
        //plus getSignUpData with signupRequestModel
        //signupResponseModel.enqueue, new callback<signupResponseModel>
        Call<SignupResponseModel> signupResponseModelCall = RetrofitApiClient.getWebService().getSignupData(signupRequestModel);
        signupResponseModelCall.enqueue(new Callback<SignupResponseModel>() {

            @Override
            public void onResponse(Call<SignupResponseModel> call, Response<SignupResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "create signupResponse" + response.body().toString());


                    String myFirstName = response.body().getResult().getFirstName();
                    String myLastName = response.body().getResult().getLastName();
                    String myPhoneNumber = response.body().getResult().getPhoneNumber();
                    String myUserName = response.body().getResult().getUsername();

                    String successMessage = response.body().getMessage();
                    Toast.makeText(SignUpActivity2.this, successMessage, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignUpActivity2.this, LoginActivity2.class);
                    startActivity(intent);

                } else {
                    SignupResponseModel signupResponse = null;
                    String jsonString = null;

                    try {
                        if (response.errorBody() != null && response.errorBody().string() != null)
                            jsonString = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (jsonString != null)
                        signupResponse = new Gson().fromJson(jsonString, SignupResponseModel.class);

                    if (signupResponse != null) {
                        Toast.makeText(SignUpActivity2.this, signupResponse.getErrors().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                Utils.dismissProgressDialog();
            }

            @Override
            public void onFailure(Call<SignupResponseModel> call, Throwable t) {
                Log.d(TAG, "create signupResponse error" + t.getMessage());
                Utils.dismissProgressDialog();
                Toast.makeText(SignUpActivity2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}