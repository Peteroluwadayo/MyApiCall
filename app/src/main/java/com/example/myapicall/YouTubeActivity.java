package com.example.myapicall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapicall.databinding.ActivityYouTubeBinding;

public class YouTubeActivity extends DrawerBaseActivity {
    ActivityYouTubeBinding activityYouTubeBinding;

    ImageView youTubeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityYouTubeBinding = ActivityYouTubeBinding.inflate(getLayoutInflater());
        setContentView(activityYouTubeBinding.getRoot());
        allocateActivityTitle("YouTube");

        youTubeButton = findViewById(R.id.youtube);

        youTubeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = "http://www.youtube.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });
    }

}