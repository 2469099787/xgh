package com.fruit.xgh.user.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.fruit.xgh.R;
import com.fruit.xgh.base.BaseFragment;
import com.fruit.xgh.main.Login;
import com.fruit.xgh.main.register;

public class UserFragment extends BaseFragment{
    private static final String TAG = UserFragment.class.getSimpleName();
    private TextView Login;
    private TextView Register;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        Log.e(TAG,"用户视图被初始化了");
        Login = (TextView) view.findViewById(R.id.user_login);
        Register = (TextView) view.findViewById(R.id.user_register);
        return view;
    }
    public void initData(){
        super.initData();
        Log.e(TAG,"用户数据被初始化了");
        //textView.setText("用户");
        initListener();

    }

    private void initListener(){
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, com.fruit.xgh.main.Login.class));
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, register.class));
            }
        });
    }
}
