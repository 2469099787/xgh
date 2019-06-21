package com.fruit.xgh.type.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fruit.xgh.Fruit;
import com.fruit.xgh.R;
import com.fruit.xgh.utils.Constants;

public class ItemOrdinaryRightAdapter extends BaseAdapter {

    private List<Fruit.REQUESTBean> objects = new ArrayList<Fruit.REQUESTBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemOrdinaryRightAdapter(Context context,List<Fruit.REQUESTBean> objects) {
        this.objects = objects;
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
            convertView = layoutInflater.inflate(R.layout.item_ordinary_right, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Fruit.REQUESTBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Fruit.REQUESTBean object, ViewHolder holder) {
        //TODO implement
        Glide.with(context).load(Constants.HTTP +object.getPicture()+".jpg").into(holder.ivOrdinaryRight);
        holder.tvOrdinaryRight.setText(object.getName()+"");
        holder.ipCover.setText("￥"+object.getPrice()+"");
        holder.tvNumber.setText(object.getSpecificaion()+"/份");
        holder.dengji.setText(object.getGrade()+"");
    }

    protected class ViewHolder {
        private LinearLayout llRoot;
    private ImageView ivOrdinaryRight;
    private TextView tvOrdinaryRight;
    private TextView ipCover;
    private TextView tvNumber;
    private TextView dengji;

        public ViewHolder(View view) {
            llRoot = (LinearLayout) view.findViewById(R.id.ll_root);
            ivOrdinaryRight = (ImageView) view.findViewById(R.id.iv_ordinary_right);
            tvOrdinaryRight = (TextView) view.findViewById(R.id.tv_ordinary_right);
            ipCover = (TextView) view.findViewById(R.id.ip_cover);
            tvNumber = (TextView) view.findViewById(R.id.tvNumber);
            dengji = (TextView)view.findViewById(R.id.dengji);
        }
    }
}
