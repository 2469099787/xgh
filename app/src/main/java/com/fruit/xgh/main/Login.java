package com.fruit.xgh.main;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.fruit.xgh.R;


import android.os.Handler;
import android.os.Message;

import android.util.Log;

import android.widget.EditText;
import android.widget.Toast;

import com.fruit.xgh.User;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {
    private EditText XGH_phone;
    private EditText XGH_pass;
    private int MESSAGE_RESULT_ok =1;

    @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    public void onlogin(View view) {
        XGH_phone = (EditText) findViewById(R.id.uname);
        XGH_pass = (EditText) findViewById(R.id.upass);
        if(XGH_phone.getText().length() >= 11){
            final String username=XGH_phone.getText().toString();
            if(XGH_pass.getText().length() >= 6){
                final String pass_text=XGH_pass.getText().toString();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        httppost(username,pass_text);
                    }
                }.start();

            }else if (XGH_pass.getText().toString().equals("")){
                Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            }else if (XGH_pass.getText().length() < 6){
                Toast.makeText(this,"请确认密码是否大于六位字符",Toast.LENGTH_SHORT).show();
            }
        }else if (XGH_phone.getText().toString().equals("")){
            Toast.makeText(this,"账号不能为空",Toast.LENGTH_SHORT).show();
        }else if (XGH_phone.getText().length() < 11){
            Toast.makeText(this,"请确认账号是否为11位数",Toast.LENGTH_SHORT).show();
        }
    }





    private void httppost(String username,String password){
        String urlStr="http://114.115.255.87/xghServer/api/AppUser_login.action";
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

                SharedPreferences sf = getSharedPreferences("user",0);
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("user",result);
                editor.commit();


                SharedPreferences sf1 = getSharedPreferences("user",0);
                String user =  sf1.getString("user",null);

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
                    //Log.d("gson","qqqqqqqqq"+bean.getMESSAGE());
                    if(bean.getMESSAGE().equals("success")){
                       // Log.d("gson","qqqqqqqqqqqq");
                        Toast.makeText(Login.this,"登陆成功",Toast.LENGTH_LONG).show();
                        //Log.d("Msg success",bean.getPhone());
                        System.out.println("succse");
                        Intent intent = new Intent(Login.this,home.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(Login.this, "账号或密码错误请重试", Toast.LENGTH_SHORT).show();
                    }
                    break;

            }

        }
    };

    public void onRegister(View view) {
        Intent toRegisterActivity =new Intent(this,register.class);
        startActivity(toRegisterActivity);
    }
}
