package com.fruit.xgh.home.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.fruit.xgh.Fruit;
import com.fruit.xgh.R;
import com.fruit.xgh.base.BaseFragment;
import com.fruit.xgh.home.adapter.GoodEatAllAdapter;
import com.fruit.xgh.type.adapter.ItemOrdinaryRightAdapter;
import com.fruit.xgh.type.adapter.TypeLeftAdapter;
import com.fruit.xgh.type.fragment.ListFragment;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

import static com.fruit.xgh.utils.Constants.XGH;

public class GoodEatFragment extends BaseFragment {
    private GridView gridView;
    private List<Fruit.REQUESTBean> list_fruit;
    String url = XGH;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_good_eat, null);
        gridView = (GridView) view.findViewById(R.id.gridv);


        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //联网请求
        //getDataFromNet(urls[0]);
        getDataFromNet(url);
    }
    public void getDataFromNet(String url) {
        //String url = XGH;
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new GoodEatFragment.MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {


        @Override
        public void onBefore(Request request, int id) {
        }

        @Override
        public void onAfter(int id) {
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("TAG", "联网失败" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            //两位请求成功
            System.out.println("联网成功");
            switch (id) {
                case 100:
//                    Toast.makeText(mContext, "http", Toast.LENGTH_SHORT).show();
                    if (response != null) {
                        //解析数据
                        processData(response);
//                        if (isFirst) {
//                            leftAdapter = new TypeLeftAdapter(mContext);
//                            lv_left.setAdapter(leftAdapter);
//                        }


                        //initListener(leftAdapter);


                        GoodEatAllAdapter adapter = new GoodEatAllAdapter(mContext,list_fruit);
                        gridView.setAdapter(adapter);

//                        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
//
//                        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                            @Override
//                            public int getSpanSize(int position) {
//                                if (position == 0) {
//                                    return 1;
//                                } else {
//                                    return 1;
//                                }
//                            }
//                        });
                        //rv_right.setLayoutManager(manager);
                    }

                    break;
                case 101:
                    Toast.makeText(mContext, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }


    private void processData(String json) {
        Gson gson = new Gson();
        Fruit typeBean = gson.fromJson(json, Fruit.class);
        list_fruit = typeBean.getREQUEST();

    }
}
