package com.fruit.xgh.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.fruit.xgh.R;


@SuppressLint("Registered")
public class Start extends AppCompatActivity {


    private final int SPLASH_DISPLAY_LENGHT;

    {
        SPLASH_DISPLAY_LENGHT = 1500;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //设置全屏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent ma=new Intent(Start.this,home.class);
                Start.this.startActivity(ma);
                Start.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
}