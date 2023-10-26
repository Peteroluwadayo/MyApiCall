package com.example.myapicall.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.example.myapicall.R;

public class Utils {
    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Activity activity) {
        progressDialog = new ProgressDialog(activity);
        if (activity != null && !activity.isFinishing()) {
            progressDialog.show();
            progressDialog.setContentView(R.layout.progressbar);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
