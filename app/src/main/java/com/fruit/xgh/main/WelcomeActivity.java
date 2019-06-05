package com.fruit.xgh.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fruit.xgh.R;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //启动主页面

                startActivity(new Intent(WelcomeActivity.this,Login.class));
                finish();
                //关闭当前页面
                System.out.println("fuck!!");

            }
        }, 5000);
    }

}
