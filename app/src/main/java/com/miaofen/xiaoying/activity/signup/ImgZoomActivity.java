package com.miaofen.xiaoying.activity.signup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.miaofen.xiaoying.R;
import com.miaofen.xiaoying.base.BaseActivity;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * 图片查看
 */
public class ImgZoomActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private TextView imageCount, title_bar_title;
    private ViewPager imagePager;
    private ImageView title_bar_back;

    private ArrayList<String> imgs;

    @Override
    public void initView() {
        super.initView();
        imageCount = findViewById(R.id.tv_num);
        title_bar_title = findViewById(R.id.title_bar_title);
        title_bar_back = findViewById(R.id.title_bar_back);
        imagePager = findViewById(R.id.image_pager);
        title_bar_back.setVisibility(View.VISIBLE);
        imagePager.setOnPageChangeListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        imgs = getIntent().getStringArrayListExtra("img");
        imagePager.setAdapter(new ImgPagerAda(imgs));
        title_bar_title.setText("查看图片");
        title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        imageCount.setText((position + 1) + "/" + imgs.size());
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public int returnLayoutId() {
        return R.layout.activity_img_zoom;
    }

    private class ImgPagerAda extends PagerAdapter {
        private ArrayList<String> imgs;

        public ImgPagerAda(ArrayList<String> imgs) {
            this.imgs = imgs;
        }

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            PhotoView photoView = new PhotoView(ImgZoomActivity.this);
            Glide.with(ImgZoomActivity.this)
                    .load(imgs.get(position))
                    .fallback(R.drawable.zhanweitu)
                    .error(R.drawable.error_zhanweitu)
                    .fitCenter()
                    .into(photoView);
            view.addView(photoView);
            return photoView;
        }
    }
}