package com.example.islam.weather;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainActivity = new Intent(WelcomeActivity.this, ChooseCity.class);
                startActivity(mainActivity);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
