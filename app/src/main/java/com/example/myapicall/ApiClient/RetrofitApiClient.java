package com.example.myapicall.ApiClient;

import com.example.myapicall.WebService.WebService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {
    public static final String Base_Url = "https://63cf-102-89-47-166.ngrok-free.app";
    private static Retrofit retrofit;

    // Create a static Retrofit method
    public static Retrofit getRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Base_Url).client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;

    }

    public static WebService getWebService() {
        WebService myWebService = getRetrofit().create(WebService.class);
        return myWebService;
    }
}
