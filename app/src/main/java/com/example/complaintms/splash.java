package com.example.complaintms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    ImageView image;
    TextView tt , tt2 ;

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        image = findViewById(R.id.image);
        tt = findViewById(R.id.tt);
        tt2 = findViewById(R.id.tt2);
        image.animate().alpha(4000).setDuration(0);
        handler = new Handler();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(splash.this,Login.class);
                splash.this.startActivity(mainIntent);
                splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}