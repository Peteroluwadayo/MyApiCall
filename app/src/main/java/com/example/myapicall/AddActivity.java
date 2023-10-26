package com.example.myapicall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapicall.View.GameResultActivity;

import java.util.Locale;
import java.util.Random;

public class AddActivity extends AppCompatActivity {

    TextView score;
    TextView life;
    TextView time;

    TextView question;
    EditText answer;

    Button ok;
    Button next;

    Random random = new Random();
    int number1;
    int number2;

    int userAnswer;
    int realAnswer;

    int userScore = 0;
    int userLife = 3;

    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS = 60000;
    Boolean timer_running;
    long timer_left_in_milis = START_TIMER_IN_MILIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        // defined the compotent

        score  = findViewById(R.id.textview_score);
        life  = findViewById(R.id.textview_life);
        time  = findViewById(R.id.textview_time);
        question = findViewById(R.id.textview_question);
        answer = findViewById(R.id.et_write_answer);
        ok = findViewById(R.id.btn_ok);
        next = findViewById(R.id.btn_nextQuestion);

        gameContinue();


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userAnswer = Integer.valueOf(answer.getText().toString());

                pauseTimer();

                if (userAnswer == realAnswer)
                {

                    userScore = userScore + 10;
                    score.setText("" + userScore);
                    question.setText("Congratulation your answer is correct");

                }
                else
                {
                    userLife = userLife -1;
                    life.setText(""+ userLife);
                    question.setText("Sorry your answer is wrong");

                }


            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer.setText("");
                gameContinue();
                resetTimer();

                if(userLife == 0)
                {
                    Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddActivity.this, GameResultActivity.class);
                    intent.putExtra("score",userScore);
                    startActivity(intent);
                    finish();

                }
                else
                {

                }

            }
        });

    }
    public void gameContinue()
    {
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);

        realAnswer = number1 + number2;

        question.setText(number1 + " + " + number2);
        startTimer();

    }
    public void startTimer()
    {
        timer = new CountDownTimer(timer_left_in_milis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timer_left_in_milis = millisUntilFinished;
                updateText();

            }

            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife = userLife - 1;
                life.setText(""+userLife);
                question.setText("Sorry! Time is up!");

            }
        }.start();

        timer_running = true;
    }
    public void updateText()
    {
        int second = (int)(timer_left_in_milis / 1000) % 60;
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);
    }
    public void pauseTimer()
    {
        timer.cancel();
        timer_running = false;
    }
    public void resetTimer()
    {
        timer_left_in_milis = START_TIMER_IN_MILIS;
        updateText();
    }
}