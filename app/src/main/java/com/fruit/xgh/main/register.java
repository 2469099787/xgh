package com.fruit.xgh.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fruit.xgh.R;
import com.fruit.xgh.User;
import com.fruit.xgh.entity.GsonloginBean;
import com.fruit.xgh.utils.Constants;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;


public class register extends AppCompatActivity {

    String TAG = register.class.getCanonicalName();
    private EditText et_data_uname;
    private EditText VerificationCode;
    private EditText et_data_upass;
    private EditText ConfirmPassword;
    private TextView Verification;
    private TextView LoginNow;
    private HashMap<String, String> stringHashMap;
    private int MESSAGE_RESULT_ok =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_test);
        LoginNow = (TextView) findViewById(R.id.LoginNow);



        //已有账号，立即登录
        LoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this,Login.class);
                startActivity(intent);
            }
        });

    }

    public void onRegister(View view) {
        et_data_uname = (EditText) findViewById(R.id.et_data_uname);
        et_data_upass = (EditText) findViewById(R.id.et_data_upass);
        ConfirmPassword = (EditText)findViewById(R.id.ConfirmPW);
        if(et_data_uname.getText().length() >= 11){
            final String username=et_data_uname.getText().toString();
            if ( et_data_upass.getText().length() >= 6) {
                final String pass_text = et_data_upass.getText().toString();
                if (!ConfirmPassword.getText().toString().equals("")  ) {
                    if (ConfirmPassword.getText().toString().equals(et_data_upass.getText().toString())  ) {

                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                httppost(username, pass_text);
                            }
                        }.start();
                    }else {
                        Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
                }

            } else if (et_data_upass.getText().toString().equals("")){
                Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            }
            else if (et_data_upass.getText().length() < 6){
                Toast.makeText(this,"输入密码至少6位字符",Toast.LENGTH_SHORT).show();
            }
        }else if (et_data_uname.getText().toString().equals("")){
            Toast.makeText(this,"账号不能为空",Toast.LENGTH_SHORT).show();
        }
        else if (et_data_uname.getText().length()< 11){
            Toast.makeText(this,"请确认账号是否为11位数",Toast.LENGTH_SHORT).show();
        }
    }

    private void httppost(String username,String password){
        String urlStr="http://114.115.255.87/xghServer/api/AppUser_register.action";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection post = (HttpURLConnection) url.openConnection();
            post.setReadTimeout(500);
            post.setConnectTimeout(500);
            //设置post请求
            post.setRequestMethod("POST");
            String key="userPhone="+username+"&userPassword="+password;
            PrintWriter writer = new PrintWriter(post.getOutputStream());
            writer.write(key);
            writer.flush();
            writer.close();
            if(post.getResponseCode()==200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(post.getInputStream()));
                String readertext="";
                String result="";
                while((readertext=reader.readLine())!=null){
                    result+=readertext;
                }
                Log.e("httpPost",""+result);
                Message message = new Message();
                message.what =MESSAGE_RESULT_ok;
                message.obj=result;
                handler.sendMessage(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    String  str = (String)msg.obj;
                    Gson gson =new Gson();
                    Log.d("gson",str);
                    User bean = gson.fromJson(str, User.class);
                    if(bean.getMESSAGE().equals("success")){
                        Toast.makeText(register.this,"注册成功",Toast.LENGTH_LONG).show();
                        Log.d("Msg success",bean.toString());
                        Intent intent = new Intent(register.this,Login.class);
                        startActivity(intent);
                        finish();
                    }
                    break;

            }
        }
    };


}
