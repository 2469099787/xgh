package com.fruit.xgh.type.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.view.View;
import android.widget.FrameLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.fruit.xgh.R;
import com.fruit.xgh.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class TypeFragment extends BaseFragment{

    private TextView iv_type_search;
    private FrameLayout fl_type;
    private List<BaseFragment> fragmentList;
    private Fragment tempFragment;
    public ListFragment listFragment;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        iv_type_search = (TextView) view.findViewById(R.id.iv_type_search);
        fl_type = (FrameLayout) view.findViewById(R.id.fl_type);

        iv_type_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "search", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

    @Override
    public void initData() {
        super.initData();

        initFragment();


    }



    @Override
    public void onResume() {
        super.onResume();
        switchFragment(tempFragment, fragmentList.get(0));
    }

    public void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }

                    transaction.add(R.id.fl_type, nextFragment, "tagFragment").commit();
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

    private void initFragment() {
        fragmentList = new ArrayList<>();
        listFragment = new ListFragment();

        fragmentList.add(listFragment);

        switchFragment(tempFragment, fragmentList.get(0));
    }
}
