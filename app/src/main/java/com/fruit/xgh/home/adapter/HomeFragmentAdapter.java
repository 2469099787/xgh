package com.fruit.xgh.home.adapter;

import android.content.Context;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fruit.xgh.Fruit;
import com.fruit.xgh.R;
import com.fruit.xgh.home.bean.ChannelBean;
import com.fruit.xgh.home.bean.GoodsBean;
import com.fruit.xgh.home.bean.ResultBeanData;
import com.fruit.xgh.home.fragment.GoodEatFragment;
import com.fruit.xgh.main.GoodsInfoActivity;
import com.fruit.xgh.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;



import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    /**
     *五种类型
     */
    /**
     *横幅广告
     */
    public static final int BANNER = 0;
    /**
     *频道
     */
    public static final int CHANNEL = 1;
    /**
     *活动
     */
    public static final int ACT = 2;
    /**
     *水果
     */
    public static final int RECOMMEND = 3;
    private static final String GOODS_BEAN = "goodsBean";
    /**
     *
     *当前类型
     */
    public int currentType = BANNER;


    /**
     * 用来初始化布局
     */
    private Context mContext;
    private  LayoutInflater mLayoutInflater;
    //数据
    private ResultBeanData.ResultBean resultBean;
    private List<Fruit.REQUESTBean> fruitBean;
    private ChannelBean.ResultBean channelBean;

    public HomeFragmentAdapter(Context mContext, List<Fruit.REQUESTBean> fruitBean) {
        this.mContext = mContext;
        this.fruitBean = fruitBean;
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
            return new ChannelViewHolder(mLayoutInflater.inflate(R.layout.layout_channel,null),mContext);
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
            bannerViewHolder.setData(fruitBean);
        }
        else if(getItemViewType(position) == CHANNEL){
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            //channelViewHolder.setData(fruitBean);
        }
        else if (getItemViewType(position) == ACT){
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(fruitBean);
        }
        else if (getItemViewType(position) == RECOMMEND){
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(fruitBean);
        }

    }


    /**
     * 轮播
     */
    class BannerViewHolder extends RecyclerView.ViewHolder{
        //private View itemView;
        private Context mContext;
        private Banner banner;
        private ArrayList<String> list_path;
        public BannerViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }



        //设置banner数据
        public void setData(List<Fruit.REQUESTBean> fruitBean) {
            //得到图片集合地址

//            list_path = new ArrayList<>();
//            list_path.add(Constants.BANNER);
//            list_path.add(Constants.BANNER1);

//                 String[] imagesUrl = new String[]{
//                     Constants.BANNER,
//                         Constants.BANNER1,
//                         Constants.BANNER2
//
//        };
            List<String> imagesUrl = new ArrayList<>();
            for (int i=1;i<4;i++){
                //String iamgeUrl = Constants.BANNER;
                //Log.d("图片请求地址：" ,iamgeUrl);
                imagesUrl.add(fruitBean.get(i).getFruitDetail().getPicture1()+".jpg");
                //imagesUrl.add(resultBean.get(i).getImage());
            }
            banner.setDelayTime(5000);
            /**
             * 设置banner循环指示
             */
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            /**
             * 设置手风琴效果
             */
            banner.setBannerAnimation(Transformer.Accordion);
//            banner.setImages(imagesUrl);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    /**
                     * 这里你可以根据框架灵活设置
                     */
                    Glide.with(mContext)
                            .load(Constants.HTTP + url)
                            .into(view);
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
     * 四个按钮
     */
    class ChannelViewHolder extends RecyclerView.ViewHolder{
        public GridView gvChannel;
        public Context mContext;
        private TextView tuijian_p;
        private TextView shuiGuo;
        private TextView qianDao;
        private TextView youHui;


        public ChannelViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            gvChannel = (GridView) itemView.findViewById(R.id.gv_channel);
            tuijian_p = (TextView) itemView.findViewById(R.id.tj_p);
            shuiGuo = (TextView) itemView.findViewById(R.id.shuiguo);
            qianDao = (TextView) itemView.findViewById(R.id.qiandao);
            youHui = (TextView) itemView.findViewById(R.id.youhui);

            tuijian_p.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG,"mmm");
                    Toast.makeText(mContext, "推荐", Toast.LENGTH_SHORT).show();

//                    Intent intent = new Intent(mContext, GoodEatFragment.class);
//                    mContext.startActivity(intent);

                }
            });

            shuiGuo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG,"mmm");
                    Toast.makeText(mContext, "新鲜水果", Toast.LENGTH_SHORT).show();
                }
            });

            qianDao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG,"mmm");
                    Toast.makeText(mContext, "签到有礼", Toast.LENGTH_SHORT).show();
                }
            });

            youHui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG,"mmm");
                    Toast.makeText(mContext, "优惠券", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(final List<Fruit.REQUESTBean> fruitBean) {
            gvChannel.setAdapter(new ItemChannelAdapter(mContext, fruitBean));

            //点击事件
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==="+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 热门横幅
     */
    class ActViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private ViewPager act_viewpager;
        public ActViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);

        }

        public void setData(List<Fruit.REQUESTBean> fruitBean) {

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
                    return 3;
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
                    Glide.with(mContext).load(Constants.HOT ).error(R.drawable.hot3).into(imageView);
                    //Glide.with(mContext).load(Constants.HTTP + fruitBean.get(position).getPicture()+".jpg" ).error(R.drawable.hot3).into(imageView);
                   // Glide.with(mContext).load(Constants.TEST+fruitBean.get(position).getPicture()).into(imageView);
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

    /**
     * 水果
     */
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

        public void setData(List<Fruit.REQUESTBean> fruitBean) {
            //RecommendGridViewAdapter adapter = new RecommendGridViewAdapter(mContext, recommend_info);
            ItemRecommendGridViewAdapter adapter = new ItemRecommendGridViewAdapter(mContext,fruitBean);
            gv_recommend.setAdapter(adapter);

            //设置点击事件
           gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int cover_price = fruitBean.get(position).getPrice();
                    String name = fruitBean.get(position).getName();
                    String figure = fruitBean.get(position).getPicture();
                    String product_id = fruitBean.get(position).getIntro();
                    String remind = fruitBean.get(position).getRemind();
                    String specificaion = fruitBean.get(position).getSpecificaion();
                    String grade = fruitBean.get(position).getGrade();
                    String srorageCondition = fruitBean.get(position).getSrorageCondition();
                    int salesVolume = fruitBean.get(position).getSalesVolume();
                    GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id,remind, specificaion, grade, srorageCondition, salesVolume);
                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    //intent.putExtra(GOODS_BEAN,fruitBean.get(position).getId());
                    intent.putExtra(GOODS_BEAN,goodsBean);
                    mContext.startActivity(intent);

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
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
        }
        return currentType;
    }


}
