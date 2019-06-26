package com.fruit.xgh.ShoppingCart.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.fruit.xgh.R;
import com.fruit.xgh.base.BaseFragment;


public class ShoppingCartFragment extends BaseFragment{
    private static final String TAG = ShoppingCartFragment.class.getSimpleName();
    private TextView textView;
    @Override
    public View initView() {
        View view = View.inflate(mContext,R.layout.fragment_cart,null);
//        textView = new TextView(mContext);
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextSize(25);
//        textView.setTextColor(Color.RED);


        Log.e(TAG,"购物车视图被初始化了");
        return view;
    }
    public void initData(){
        super.initData();
        Log.e(TAG,"购物车数据被初始化了");
//        textView.setText("购物车");


//        List<GoodsBean> goodsBeanList = CartProvider.getInstance().getAllData();
//            for (int i=0;i<goodsBeanList.size();i++){
//                Log.e("TAG",goodsBeanList.get(i).toString());
//
//            }

    }
}
