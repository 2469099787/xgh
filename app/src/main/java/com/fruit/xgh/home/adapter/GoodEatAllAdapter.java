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
import com.fruit.xgh.utils.Constants;

public class GoodEatAllAdapter extends BaseAdapter {

    private List<Fruit.REQUESTBean> objects = new ArrayList<Fruit.REQUESTBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public GoodEatAllAdapter(Context context,List<Fruit.REQUESTBean> objects) {
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
            convertView = layoutInflater.inflate(R.layout.good_eat_all, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Fruit.REQUESTBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Fruit.REQUESTBean object, ViewHolder holder) {
        //TODO implement
        Glide.with(context).load(Constants.HTTP +object.getPicture()+".jpg").into(holder.image);
        holder.name.setText(object.getName()+"");
        //holder.ipCover.setText("￥"+object.getPrice()+"");
        holder.fen.setText(object.getSpecificaion()+"/份");
       // holder.dengji.setText(object.getGrade()+"");
    }

    protected class ViewHolder {
        private ImageView image;
    private TextView name;
    private TextView fen;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            fen = (TextView) view.findViewById(R.id.fen);
        }
    }
}
