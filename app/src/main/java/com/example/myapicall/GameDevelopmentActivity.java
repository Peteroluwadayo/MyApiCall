package com.example.myapicall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapicall.View.MathActivity;

public class GameDevelopmentActivity extends AppCompatActivity {

    private TextView textViewMathGame;
    private TextView textView2D;
    private TextView textView3D;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_development);

        textViewMathGame = findViewById(R.id.mobile_game);
        textView2D = findViewById(R.id.tv2d_game);
        textView3D = findViewById(R.id.tv3d_game);



        textViewMathGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(GameDevelopmentActivity.this, MathActivity.class);
                startActivity(myIntent);
            }
        });

    }
}