
package com.example.myapicall.SignUpPackage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Error {

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "Error{" +
                "message='" + message + '\'' +
                '}';
    }
}
