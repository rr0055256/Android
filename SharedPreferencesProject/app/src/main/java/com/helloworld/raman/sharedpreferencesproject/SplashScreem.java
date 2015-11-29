package com.helloworld.raman.sharedpreferencesproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;



/**
 * Created by raman on 28/11/15.
 */
public class SplashScreem extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreem.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }


}
