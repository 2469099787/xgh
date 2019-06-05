package com.fruit.xgh.main;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fruit.xgh.R;
import com.fruit.xgh.ShoppingCart.fragment.ShoppingCartFragment;
import com.fruit.xgh.base.BaseFragment;
import com.fruit.xgh.home.fragment.HomeFragment;
import com.fruit.xgh.type.fragment.TypeFragment;
import com.fruit.xgh.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class home extends FragmentActivity {

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;

    @Bind(R.id.rg_main)
    RadioGroup rgMain;

    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_type)
    RadioButton rbType;
    @Bind(R.id.rb_cart)
    RadioButton rbCart;
    @Bind(R.id.rb_user)
    RadioButton rbUser;

    private ArrayList<BaseFragment> fragments;
    private int position;
    private BaseFragment mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initFragment();
        initListener();
       //typeface();
       // initview();


    }

    private void initFragment() {
        fragments = new ArrayList<>();

        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
//
                        break;
                    case R.id.rb_type:
                        position = 1;
//
                        break;
//
                    case R.id.rb_cart:
                        position = 2;

                        break;
                    case R.id.rb_user:
                        position = 3;
//
                        break;
                }

                BaseFragment baseFragment = getFragment(position);
                switchFragment(mContext, baseFragment);
            }
        });

        rgMain.check(R.id.rb_home);

    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (mContext != nextFragment) {
            mContext = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
    private void typeface(){
        Typeface type = Typeface.createFromAsset(getAssets(), "Regular.otf");
        //rbHome.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        rbHome.setTypeface(type);
        rbType.setTypeface(type);
        rbCart.setTypeface(type);
        rbUser.setTypeface(type);
    }

    private void initview(){
       // Drawable drawable = getResources().getDrawable(R.drawable.user_bt_one);
       // drawable.setBounds(0,0,60,60);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
     //   rbUser.setCompoundDrawables(null,drawable,null,null);//设置drawableTop

//        Drawable drawable = getResources().getDrawable(R.drawable.user_bt_one);
//        drawable.setBounds(0,0,60,60);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
//        rbUser.setCompoundDrawables(null,drawable,null,null);//设置drawableTop
    }

}
