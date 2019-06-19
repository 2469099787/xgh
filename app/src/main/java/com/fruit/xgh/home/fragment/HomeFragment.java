package com.fruit.xgh.home.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fruit.xgh.Map.ActivityMap;
import com.fruit.xgh.R;
import com.fruit.xgh.base.BaseFragment;
import com.fruit.xgh.home.adapter.HomeFragmentAdapter;
import com.fruit.xgh.home.bean.ResultBeanData;
import com.fruit.xgh.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class HomeFragment extends BaseFragment{
    private static final String TAG = HomeFragment.class.getSimpleName();
    private TextView text_position;
    private RecyclerView rvHome;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private TextView photo_position;
    /**
     * 返回的数据
     */
    private ResultBeanData.ResultBean resultBean;
    private HomeFragmentAdapter adapter;
    @Override
    public View initView() {
        Log.e(TAG,"主页视图被初始化了");
        View view = View.inflate(mContext,R.layout.fragment_home,null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        tv_search_home = (TextView)view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);
        photo_position = (TextView) view.findViewById(R.id.photo_position);
        text_position = (TextView)view.findViewById(R.id.text_position);

        //设置点击事件
        initListener();
        return view;
    }
    public void initData(){
        super.initData();
        Log.e(TAG,"主页数据被初始化了");
        //联网请求主页的数据
        getDataFromNet();


    }

    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
//                .addParams("username", "hyman")
//                .addParams("password", "123")
                .build()
                .execute(new StringCallback()
                {
                    /**
                     * 当联网失败的时候回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"首页请求失败==="+e.getMessage());
                    }

                    /**
                     * 当联网成功的时候回调
                     * @param response 请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"首页请求成功==="+response);
                        //解析数据
                        processData(response);
                    }


                });
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json,ResultBeanData.class);
         resultBean =resultBeanData.getResult();
         if (resultBean != null){
             //有数据
            //设置适配器
             adapter = new HomeFragmentAdapter(mContext,resultBean);
             rvHome.setAdapter(adapter);

             //设置布局管理者
             rvHome.setLayoutManager(new GridLayoutManager(mContext,1));
         }else {
             //没有数据
         }
         Log.e(TAG,"测试=>获取热门数据名字=="+resultBean.getHot_info().get(0).getName());
        
    }

    private void initListener() {

        //置顶的监听

//        ib_top.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //回到顶部
//
//                rvHome.scrollToPosition(0);
//            }
//        });

        //搜素的监听

        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //消息的监听

        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //地图的监听
        photo_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "进入地图", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ActivityMap.class);
                mContext.startActivity(intent);
            }
        });

        }
    }
