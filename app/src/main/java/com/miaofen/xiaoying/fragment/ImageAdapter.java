package com.miaofen.xiaoying.fragment;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.miaofen.xiaoying.R;
import com.miaofen.xiaoying.common.data.bean.response.ImagerDataBean;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * 项目名称：com.miaofen.xiaoying.fragment
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ImageAdapter extends BannerAdapter<ImagerDataBean, ImageAdapter.BannerViewHolder> {

    public ImageAdapter(List<ImagerDataBean> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, ImagerDataBean data, int position, int size) {
        //图片加载自己实现
        Glide.with(holder.itemView)
                .load(data.getUrl())
                .fallback(R.drawable.zhanweitu)
                .error(R.drawable.error_zhanweitu)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.imageView);
//        holder.imageView.setImageResource(data.getUrl());
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}