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

public class ItemChannelAdapter extends BaseAdapter {

    private List<Fruit.REQUESTBean> objects = new ArrayList<Fruit.REQUESTBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemChannelAdapter(Context context,List<Fruit.REQUESTBean> objects) {
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
            convertView = layoutInflater.inflate(R.layout.item_channel, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Fruit.REQUESTBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Fruit.REQUESTBean object, ViewHolder holder) {
        //TODO implement
        Glide.with(context).load(Constants.HTTP +object.getPicture()+".jpg").into(holder.ImageChannel);
        holder.NameChanner.setText(object.getName()+"");

    }

    protected class ViewHolder {
        private ImageView ImageChannel;
    private TextView NameChanner;

        public ViewHolder(View view) {
            ImageChannel = (ImageView) view.findViewById(R.id.image_channel);
            NameChanner = (TextView) view.findViewById(R.id.name_channel);
        }
    }
}
