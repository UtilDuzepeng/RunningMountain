package com.miaofen.xiaoying.view;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import com.miaofen.xiaoying.R;
import com.miaofen.xiaoying.common.data.bean.response.ImagerDataBean;
import com.miaofen.xiaoying.fragment.ImageAdapter;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;

/**
 * 项目名称：com.miaofen.xiaoying.view
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class BannerLayout extends LinearLayout {

    private Banner banner;

    public BannerLayout(Context context) {
        super(context);
        initView(context);
    }

    public BannerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BannerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.banner_layout, this);
        banner = (Banner) findViewById(R.id.banner);
    }


    public void setFilletBanner(ArrayList<ImagerDataBean> list_path, Activity activity){
        banner.addBannerLifecycleObserver((LifecycleOwner) activity)//添加生命周期观察者
                .setAdapter(new ImageAdapter(list_path))
                .setIndicator(new CircleIndicator(activity));
    }


}
