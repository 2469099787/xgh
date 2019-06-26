package com.fruit.xgh.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fruit.xgh.Fruit;
import com.fruit.xgh.R;
import com.fruit.xgh.ShoppingCart.utils.CartProvider;
import com.fruit.xgh.home.bean.GoodsBean;
import com.fruit.xgh.utils.Constants;
import com.fruit.xgh.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class GoodsInfoActivity extends Activity implements View.OnClickListener {
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private Button btn_more;
    private TextView tvMoreShare;
    private TextView tvMoreSearch;
    private TextView tvMoreHome;

    private TextView guigeQian;
    private TextView guigeHou;
    private TextView xiaoLiang;
    private TextView dengjiQing;
    private TextView dengjiHou;
    private TextView chuCun;
    private TextView yuanJia;

    private GoodsBean goodsBean;
    private Fruit.REQUESTBean fruitBean;
    private List<Fruit.REQUESTBean> fruit_list;

    private CartProvider cartProvider;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-08 20:45:12 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ibGoodInfoBack = (ImageButton) findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = (ImageButton) findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = (TextView) findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = (TextView) findViewById(R.id.tv_good_info_store);
        wbGoodInfoMore = (WebView) findViewById(R.id.wb_good_info_more);
        llGoodsRoot = (LinearLayout) findViewById(R.id.ll_goods_root);
        tvGoodInfoCallcenter = (TextView) findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = (TextView) findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = (TextView) findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = (Button) findViewById(R.id.btn_good_info_addcart);

        guigeQian = (TextView)findViewById(R.id.good_guige_qian);
        guigeHou = (TextView)findViewById(R.id.guige);
        xiaoLiang = (TextView)findViewById(R.id.good_xiaoliang);
        dengjiQing = (TextView)findViewById(R.id.good_dengji);
        dengjiHou = (TextView)findViewById(R.id.good_jipei);
        chuCun  = (TextView)findViewById(R.id.chucun);
        yuanJia = (TextView)findViewById(R.id.good_yuanjia);
        btn_more = (Button) findViewById(R.id.btn_more);
        tvMoreShare = (TextView) findViewById(R.id.tv_more_share);
        tvMoreSearch = (TextView) findViewById(R.id.tv_more_search);
        tvMoreHome = (TextView) findViewById(R.id.tv_more_home);

        ibGoodInfoBack.setOnClickListener(this);
        ibGoodInfoMore.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);


        tvMoreShare.setOnClickListener(this);
        tvMoreSearch.setOnClickListener(this);
        tvMoreHome.setOnClickListener(this);

        btn_more.setOnClickListener(this);

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);
        tvGoodInfoCallcenter.setOnClickListener(this);

        yuanJia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-08 20:45:12 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == ibGoodInfoBack) {
            finish();
        } else if (v == ibGoodInfoMore) {
//             Handle clicks for ibGoodInfoMore
            Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
        } else if (v == btn_more) {
            // ll_root.setVisibility(View.GONE);
        } else if (v == tvMoreShare) {
            Toast.makeText(GoodsInfoActivity.this, "分享", Toast.LENGTH_SHORT).show();
//            showShare();
        } else if (v == tvMoreSearch) {
            Toast.makeText(GoodsInfoActivity.this, "搜索", Toast.LENGTH_SHORT).show();
        } else if (v == tvMoreHome) {
            // Constants.isBackHome = true;
            finish();
        } else if (v == tvGoodInfoCallcenter) {
            Toast.makeText(GoodsInfoActivity.this, "购物车", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, CallCenterActivity.class);
//            startActivity(intent);
        } else if (v == tvGoodInfoCollection) {
            Toast.makeText(GoodsInfoActivity.this, "分享", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCart) {
           Toast.makeText(GoodsInfoActivity.this, "加入购物车", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(this, ShoppingCartActivity.class);
            //startActivity(intent);
            //cartProvider.addData(goodsBean);

        } else if (v == btnGoodInfoAddcart) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this, "立即购买", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();
        //getInfo();
        //cartProvider = CartProvider.getInstance();

        GoodsBean goodsBean = (GoodsBean) getIntent().getSerializableExtra("goodsBean");
        if (goodsBean != null) {
            //Toast.makeText(this, "goodsBean=="+goodsBean.toString(), Toast.LENGTH_SHORT).show();
            setDataForView(goodsBean);

        }
    }

//    private void getInfo() {
//        new Thread(() -> {
//            List<KeyValue> list = new ArrayList<>();
//            list.add(new KeyValue("id",1));
//                HttpUtils.postfrom("AppFruit_getFruitById.action", list, new HttpUtils.MyResponseHandler() {
//                    @Override
//                    protected void mySuccess(String s) {
//                        Log.d(TAG, "mySuccess: "+s);
//                    }
//
//                    @Override
//                    protected void myError(Throwable throwable, boolean b) {
//
//                    }
//                });
//        }).start();
//    }

    private void setDataForView(GoodsBean goodsBean) {
        //设置图片
        Glide.with(this).load(Constants.HTTP + goodsBean.getFigure() + ".jpg").into(ivGoodInfoImage);
        //设置名字
        tvGoodInfoName.setText(goodsBean.getName());
        //设置价格
        tvGoodInfoPrice.setText("￥" + goodsBean.getCover_price());
        //设置详情
        tvGoodInfoDesc.setText(goodsBean.getRemind());
        //设置规格
        guigeQian.setText(goodsBean.getSpecificaion());
        guigeHou.setText(goodsBean.getSpecificaion());
        //销量
        xiaoLiang.setText(goodsBean.getSalesVolume()+"/份");
        //级别
        dengjiQing.setText(goodsBean.getGrade());
        dengjiHou.setText(goodsBean.getGrade());
        //存储
        chuCun.setText(goodsBean.getSrorageCondition());


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
