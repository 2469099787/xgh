package com.fruit.xgh.user.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.fruit.xgh.R;
import com.fruit.xgh.base.BaseFragment;

public class UserFragment extends BaseFragment{
    private static final String TAG = UserFragment.class.getSimpleName();
    private TextView textView;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        Log.e(TAG,"用户视图被初始化了");

        return view;
    }
    public void initData(){
        super.initData();
        Log.e(TAG,"用户数据被初始化了");
        //textView.setText("用户");

    }
}
