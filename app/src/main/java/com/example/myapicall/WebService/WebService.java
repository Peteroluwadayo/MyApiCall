package com.example.myapicall.WebService;

import com.example.myapicall.LoginInPackage.LoginRequestModel;
import com.example.myapicall.LoginInPackage.LoginResponseModel;
import com.example.myapicall.SignUpPackage.SignupRequestModel;
import com.example.myapicall.SignUpPackage.SignupResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface WebService {
    // this is my end point, it can be delete,push,get,post,etc..
    @POST("api/v1/users/register")
    Call<SignupResponseModel> getSignupData(@Body SignupRequestModel signupRequestModel);

    @POST("api/v1/users/login")
    Call<LoginResponseModel> getLoginData(@Body LoginRequestModel loginRequestModel);

    @GET("api/v1/events")
    Call<LoginResponseModel> getLoginData();

    //patch,put,delete,
}
