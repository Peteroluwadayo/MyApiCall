package com.example.myapicall.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapicall.MainActivity;
import com.example.myapicall.R;

public class GameResultActivity extends AppCompatActivity {

    TextView result;
    Button playAgain;
    Button exit;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        result = findViewById(R.id.tv_score_smile);
        playAgain = findViewById(R.id.btn_playAgain);
        exit = findViewById(R.id.btn_exit);
        // making the result show in the
        Intent intent = getIntent();
        score = intent.getIntExtra("score",0);
        String userScore = String.valueOf(score);
        result.setText("Your score: "+userScore);

        // taking two parameter. Result page to MainActivity
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}