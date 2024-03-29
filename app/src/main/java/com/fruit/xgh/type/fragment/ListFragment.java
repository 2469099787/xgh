package com.fruit.xgh.type.fragment;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.fruit.xgh.Fruit;
import com.fruit.xgh.R;
import com.fruit.xgh.base.BaseFragment;
import com.fruit.xgh.home.bean.GoodsBean;
import com.fruit.xgh.main.GoodsInfoActivity;
import com.fruit.xgh.type.adapter.ItemOrdinaryRightAdapter;
import com.fruit.xgh.type.adapter.TypeLeftAdapter;
import com.fruit.xgh.type.bean.TypeBean;
import com.fruit.xgh.utils.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;


/**
 * 分类页面
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends BaseFragment {

    private FrameLayout fl_list_container;
    private ListView lv_left;
    private GridView rv_right;
    private List<TypeBean.ResultBean> result;
    private List<Fruit.REQUESTBean> list_fruit;

    private static final String GOODS_BEAN = "goodsBean";
    private Context context;

    private String[] urls = new String[]{Constants.HTTP+"api/AppFruit_findFruitByNew.action", Constants.XGH, Constants.XG, Constants.XGH,
            Constants.XGH, Constants.XGH};
    private String url = Constants.XGH;

    private TypeLeftAdapter leftAdapter;
    private boolean isFirst = true;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_list, null);
        lv_left = (ListView) view.findViewById(R.id.lv_left);
        rv_right = (GridView) view.findViewById(R.id.rv_right);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //联网请求
       getDataFromNet(urls[0]);
        //getDataFromNet();
    }

    /**
     * 具体的联网请求代码
     */
    public void getDataFromNet(String url) {
       // String url = XGH;
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
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
                        if (isFirst) {
                            leftAdapter = new TypeLeftAdapter(mContext);
                            lv_left.setAdapter(leftAdapter);
                        }


                        initListener(leftAdapter);

                        //解析右边数据
//                        TypeRightAdapter rightAdapter = new TypeRightAdapter(mContext, fruit);
//                        rv_right.setAdapter(rightAdapter);
//
                        ItemOrdinaryRightAdapter adapter = new ItemOrdinaryRightAdapter(mContext,list_fruit);
                        rv_right.setAdapter(adapter);
                        rv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                int cover_price = list_fruit.get(position).getPrice();
                                String name = list_fruit.get(position).getName();
                                String figure = list_fruit.get(position).getPicture();
                                String product_id = list_fruit.get(position).getIntro();
                                String remind = list_fruit.get(position).getRemind();
                                String specificaion = list_fruit.get(position).getSpecificaion();
                                String grade = list_fruit.get(position).getGrade();
                                String srorageCondition = list_fruit.get(position).getSrorageCondition();
                                int salesVolume = list_fruit.get(position).getSalesVolume();
                                GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id,remind, specificaion, grade, srorageCondition, salesVolume);
                                Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                                //intent.putExtra(GOODS_BEAN,list_fruit.get(position).getId());
                                intent.putExtra(GOODS_BEAN,goodsBean);
                                mContext.startActivity(intent);
                            }
                        });


                        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);

                        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                if (position == 0) {
                                    return 1;
                                } else {
                                    return 1;
                                }
                            }
                        });
                        //rv_right.setLayoutManager(manager);
                    }

                    break;
                case 101:
                    Toast.makeText(mContext, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

    private void initListener(final TypeLeftAdapter adapter) {
        //点击监听
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelected(position);//刷新
                if (position != 0) {
                    isFirst = false;
                }
                getDataFromNet(urls[position]);
                leftAdapter.notifyDataSetChanged();
            }
        });

        //选中监听
        lv_left.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelected(position);//刷新

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void processData(String json) {
        Gson gson = new Gson();
        Fruit typeBean = gson.fromJson(json, Fruit.class);
        list_fruit = typeBean.getREQUEST();

    }
}