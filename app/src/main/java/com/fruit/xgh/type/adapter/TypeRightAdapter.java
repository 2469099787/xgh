package com.fruit.xgh.type.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.fruit.xgh.Fruit;
import com.fruit.xgh.R;

import com.fruit.xgh.type.bean.TypeBean;
import com.fruit.xgh.utils.Constants;

import com.bumptech.glide.Glide;

import java.util.List;


public class TypeRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    /**
     * 常用分类
     */
    private List<TypeBean.ResultBean.ChildBean> child;
    private List<Fruit.REQUESTBean> fruits;
    /**
     * 热卖商品列表的数据
     */

    /**
     * 普通的
     */
    public static final int ORDINARY = 0;


    /**
     * 当前的类型
     */
    public int currentType;

    private final LayoutInflater mLayoutInflater;

    public TypeRightAdapter(Context mContext, List<Fruit.REQUESTBean> result) {
        this.mContext = mContext;

        mLayoutInflater = LayoutInflater.from(mContext);

        if (result != null && result.size() > 0) {
            //fruits = result.get(0).getREQUEST();

        }
    }




//    public TypeRightAdapter(Context mContext, List<Fruit.REQUESTBean> result1) {
//        this.mContext = mContext;
//
//        mLayoutInflater = LayoutInflater.from(mContext);
//
//        if (result1 != null && result1.size() > 0) {
//            //fruit = result1.get(0).getRemind();
//
//        }
//    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new OrdinaryViewHolder(mLayoutInflater.inflate(R.layout.item_ordinary_right, null), mContext);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == ORDINARY){
            OrdinaryViewHolder ordinaryViewHolder = (OrdinaryViewHolder) holder;
           // ordinaryViewHolder.setData(fruits.get(position),position);
               ordinaryViewHolder.setData(fruits.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {

            currentType = ORDINARY;
        return currentType;
    }

    @Override
    public int getItemCount() {
        return fruits.size() + 1 ;
    }

    class OrdinaryViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ImageView iv_ordinary_right;
        private TextView tv_ordinary_right;
        private TextView ip_cover;
        private LinearLayout ll_root;

        public OrdinaryViewHolder(View itemView, final Context mContext) {
            super(itemView);
            this.mContext = mContext;
            iv_ordinary_right = (ImageView) itemView.findViewById(R.id.iv_ordinary_right);
            tv_ordinary_right = (TextView) itemView.findViewById(R.id.tv_ordinary_right);
            ip_cover = (TextView)itemView.findViewById(R.id.ip_cover);
            ll_root = (LinearLayout) itemView.findViewById(R.id.ll_root);



        }

//        public void setData(TypeBean.ResultBean.ChildBean childBean, final int position) {
//            //加载图片
//            Glide.with(mContext)
//                    .load(Constants.BASE_URl_IMAGE +childBean.getPic())
//                    .into(iv_ordinary_right);
//            //设置名称
//            tv_ordinary_right.setText(childBean.getName());
//            ip_cover.setText(childBean.getCover_price());
//            ll_root.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                 Toast.makeText(mContext, "posotion" + position, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }

        public void setData(Fruit.REQUESTBean fruitBean) {
            //加载图片
            Glide.with(mContext)
                    .load(Constants.XGH +fruitBean.getPicture())
                    .into(iv_ordinary_right);
            //设置名称
            tv_ordinary_right.setText(fruitBean.getName());
            ip_cover.setText(fruitBean.getPrice());
            ll_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "posotion" , Toast.LENGTH_SHORT).show();
                }
            });
        }


    }


}
