package com.fruit.xgh.home.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fruit.xgh.Fruit;
import com.fruit.xgh.R;
import com.fruit.xgh.main.MyApplication;
import com.fruit.xgh.utils.Constants;

public class ItemRecommendGridViewAdapter extends BaseAdapter {

    private List<Fruit.REQUESTBean> objects = new ArrayList<Fruit.REQUESTBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemRecommendGridViewAdapter(Context context,List<Fruit.REQUESTBean> objects) {
        this.objects =objects;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Fruit.REQUESTBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_recommend_grid_view, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Fruit.REQUESTBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Fruit.REQUESTBean object, ViewHolder holder) {
        //TODO implement
        Glide.with(context).load(Constants.HTTP +object.getPicture()+".jpg").into(holder.ivRecommend);
        holder.tvName.setText(object.getName()+"");
        holder.tvPrice.setText("￥"+object.getPrice()+"");
        holder.tvNumber.setText(object.getSpecificaion()+"/份");
    }

    protected class ViewHolder {
        private ImageView ivRecommend;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvNumber;
        public ViewHolder(View view) {
            ivRecommend = (ImageView) view.findViewById(R.id.iv_recommend);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_price);
            tvNumber =  (TextView)view.findViewById(R.id.number);
        }
    }
}
