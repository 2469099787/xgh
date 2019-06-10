package com.fruit.xgh.home.adapter;

import android.content.Context;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fruit.xgh.R;
import com.fruit.xgh.home.bean.GoodsBean;
import com.fruit.xgh.home.fragment.bean.ResultBeanData;
import com.fruit.xgh.main.GoodsInfoActivity;
import com.fruit.xgh.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;



import java.util.ArrayList;
import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    /**
     *
     五种类型

     */
    /**
     *
     横幅广告

     */
    public static final int BANNER = 0;
    /**
     *
     频道

     */
    public static final int CHANNEL = 1;

    /**
     *
     活动

     */
    public static final int ACT = 2;

    /**
     *
//     秒杀
//
//     */
//    public static final int SECKILL = 4;
    /**
     *
     推荐

     */
    public static final int RECOMMEND = 3;
    private static final String GOODS_BEAN = "goodsBean";
    /**
     *
     热卖

     */
   // public static final int HOT = 5;

    /**
     *
     当前类型
     */
    public int currentType = BANNER;



    /**
     * 用来初始化布局
     */
    private Context mContext;
    private  LayoutInflater mLayoutInflater;
    //数据
    private ResultBeanData.ResultBean resultBean;

    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    /**
     *相当于getview 创建viewHolder部分代码
     * 创建ViewHolder
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_viewpager, null), mContext);
        }
        else if (viewType == CHANNEL){
            return new ChannelViewHolder(mLayoutInflater.inflate(R.layout.channel_item,null),mContext);
        }
        else if (viewType == ACT){
            return new ActViewHolder(mLayoutInflater.inflate(R.layout.act_item,null),mContext);
        }
        else if (viewType == RECOMMEND){
            return new RecommendViewHolder(mLayoutInflater.inflate(R.layout.recommend_item,null),mContext);
        }
        return null;
    }

    /**
     *相当于getview中的绑定数据模块
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //设置banner的数据
            bannerViewHolder.setData(resultBean.getBanner_info());
        }
        else if(getItemViewType(position) == CHANNEL){
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }
        else if (getItemViewType(position) == ACT){
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resultBean.getAct_info());
        }
        else if (getItemViewType(position) == RECOMMEND){
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        }

    }
    class RecommendViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter adapter;
        public RecommendViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            tv_more_recommend =(TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);

        }

        public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            RecommendGridViewAdapter adapter = new RecommendGridViewAdapter(mContext, recommend_info);
            gv_recommend.setAdapter(adapter);

            //设置点击事件
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                    ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean=recommend_info.get(position);
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());

                    startGoodsInfoActivity(goodsBean);
                }
            });

        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private ViewPager act_viewpager;
        public ActViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);

        }

        public void setData(List<ResultBeanData.ResultBean.ActInfoBean> act_info) {

            act_viewpager.setPageMargin(20);//设置page间间距，自行根据需求设置
//            act_viewpager.setOffscreenPageLimit(3);//>=3
//
//            //setPageTransformer 决定动画效果
//            act_viewpager.setPageTransformer(true, new
//                    ScaleInTransformer());
            //有数据
            //设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                /**
                 *
                 * @param container ViwePager
                 * @param position 对应的页面
                 * @return
                 */
                @Override
                public Object instantiateItem( ViewGroup container, int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(mContext).load(Constants.BASE_IMAGE_URL+act_info.get(position).getIcon_url()).into(imageView);
                    //添加到容器中
                    container.addView(imageView);

                    //设置点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "position == "+position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    return imageView;
                }

                @Override
                public void destroyItem( ViewGroup container, int position,  Object object) {
                    container.removeView((View) object);
                }

                /**
                 *
                 * @param view 页面
                 * @param object instantiateItem方法返回的值
                 * @return
                 */
                @Override
                public boolean isViewFromObject( View view,  Object object) {
                    return view == object;
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder{
        public GridView gvChannel;
        public Context mContext;

        public ChannelViewHolder(View itemView, Context mContext) {
            super(itemView);
            gvChannel = (GridView) itemView.findViewById(R.id.gv_channel);
            this.mContext = mContext;
        }

        public void setData(final List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            gvChannel.setAdapter(new ChannelAdapter(mContext, channel_info));

            //点击事件
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==="+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
        }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        //private View itemView;
        private Context mContext;
        private Banner banner;
        public BannerViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }



        //设置banner数据
        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //得到图片集合地址
            List<String> imagesUrl = new ArrayList<>();
            for (int i=0;i<banner_info.size();i++){
                String iamgeUrl = banner_info.get(i).getImage();
                imagesUrl.add(iamgeUrl);
            }
            /**
             * 设置banner循环指示
             */
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            /**
             * 设置手风琴效果
             */
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片- Glide
                    Glide.with(mContext).load(Constants.BASE_IMAGE_URL+url).into(view);

                }
            });
            /**
             * 设置banner点击事件
             */
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                    //startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    /**
     * 启动商品信息列表页面
     * @param goodsBean
     */
    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        mContext.startActivity(intent);
    }


    /**
     * 共有多少个item
     * @return
     */
    @Override
    public int getItemCount() {
        //开发过程中从1-->2·····
        return 4;
    }



    /**
     * 得到类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
//            case SECKILL:
//                currentType = SECKILL;
//                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
//            case HOT:
//                currentType = HOT;
//                break;
        }
        return currentType;
    }

}
