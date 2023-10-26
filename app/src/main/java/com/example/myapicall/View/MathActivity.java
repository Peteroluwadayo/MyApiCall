package com.example.myapicall.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapicall.R;
import com.example.myapicall.AddActivity;

public class MathActivity extends AppCompatActivity {


    private AppCompatButton buttonAdd;
    private AppCompatButton buttonSub;
    private AppCompatButton buttonMulti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        buttonAdd = findViewById(R.id.btnAdd);
        buttonSub = findViewById(R.id.btnSub);
        buttonMulti = findViewById(R.id.btnMulti);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathActivity.this, AddActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}