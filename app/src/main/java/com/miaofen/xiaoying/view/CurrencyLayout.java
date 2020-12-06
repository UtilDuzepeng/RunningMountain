package com.miaofen.xiaoying.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.miaofen.xiaoying.R;
import com.miaofen.xiaoying.utils.ToastUtils;

/**
 * 项目名称：com.miaofen.xiaoying.view
 * 类描述：通用的列表item
 * 创建人：duzepeng
 * 创建时间：2020/12/2
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class CurrencyLayout extends LinearLayout {

    public CurrencyLayout(Context context) {
        super(context);
        initView(context);
    }

    public CurrencyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CurrencyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    //头像
    private ImageView portrait;
    //名称,摩托车型号,创建时间,小队名称,小队介绍,出发地,距离,目的地,途经地,出发与结束时间,
    private TextView netName, motorcycleType, dataTime, subject, whatPublish, placeDeparture, distance, destination,
            route, playtime;
    //收藏,评论,转发
    private LinearLayout collection, comment, forward;
    //详情
    private LinearLayout item_details;

    private ImageView image_collection;

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.item_currency, this);
        portrait = (ImageView) findViewById(R.id.image_portrait);
        netName = (TextView) findViewById(R.id.net_name);
        motorcycleType = (TextView) findViewById(R.id.motorcycle_type);
        dataTime = (TextView) findViewById(R.id.data_time);
        subject = (TextView) findViewById(R.id.subject);
        whatPublish = (TextView) findViewById(R.id.what_to_publish);
        placeDeparture = (TextView) findViewById(R.id.place_departure);
        distance = (TextView) findViewById(R.id.distance);
        destination = (TextView) findViewById(R.id.destination);
        route = (TextView) findViewById(R.id.route);
        playtime = (TextView) findViewById(R.id.playtime);
        playtime = (TextView) findViewById(R.id.playtime);
        collection = (LinearLayout) findViewById(R.id.collection);
        comment = (LinearLayout) findViewById(R.id.comment);
        forward = (LinearLayout) findViewById(R.id.forward);
        item_details = (LinearLayout) findViewById(R.id.item_details);
        image_collection = (ImageView) findViewById(R.id.image_collection);
        onClickCollection();
        onClickComment();
        onClickForward();
    }

    //todo 预留方法 满足特殊需求
    public void setType(int type) {

    }

    /**
     * 头像
     *
     * @param url
     */
    public void setPortrait(String url) {
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .skipMemoryCache(false);//内存缓存
        Glide.with(this).load(url).apply(mRequestOptions).into(portrait);
    }

    /**
     * 名称
     *
     * @param name
     */
    public void setNetName(String name) {
        netName.setText(name);
    }

    /**
     * 摩托车型号
     *
     * @param type
     */
    public void setMotorcycleType(String type) {
        motorcycleType.setText(type);
    }

    /**
     * 创建时间
     *
     * @param time
     */
    public void setDataTime(String time) {
        dataTime.setText(time);
    }

    /**
     * 小队名称
     *
     * @param ject
     */
    public void setSubject(String ject) {
        subject.setText(ject);
    }

    /**
     * 小队介绍
     *
     * @param publish
     */
    public void setWhatPublish(String publish) {
        whatPublish.setText(publish);
    }

    /**
     * 出发地
     *
     * @param departure
     */
    public void setPlaceDeparture(String departure) {
        placeDeparture.setText(departure);
    }

    /**
     * 距离
     *
     * @param tance
     */
    public void setDistance(String tance) {
        distance.setText(tance);
    }

    /**
     * 目的地
     *
     * @param tination
     */
    public void setDestination(String tination) {
        destination.setText(tination);
    }

    /**
     * 途经地
     *
     * @param rou
     */
    public void setRoute(String rou) {
        route.setText(rou);
    }

    /**
     * 出发与结束时间
     *
     * @param time
     */
    public void setPlaytime(String time) {
        playtime.setText(time);
    }

    /**
     * 收藏
     */
    public void onClickCollection() {
        collection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ToastUtils.showToast("收藏");
            }
        });
    }

    /**
     * 评论
     */
    public void onClickComment() {
        comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ToastUtils.showToast("评论");
            }
        });
    }

    /**
     * 转发
     */
    public void onClickForward() {
        forward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ToastUtils.showToast("转发");
            }
        });
    }

    /**
     * item 详情
     */
    public LinearLayout getItemOnClick() {
        return item_details;
    }


}
