package com.example.TeaPlucker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivty extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activty);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                String loggedUserType = sh.getString("userType", "empty");
                String loggedUserName = sh.getString("userName", "empty");

                if (!loggedUserType.equals("empty") || !loggedUserName.equals("empty")) {
                    Intent intent = new Intent(SplashActivty.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivty.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 3000);
    }
}