package com.miaofen.xiaoying.activity.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.miaofen.xiaoying.R;
import com.miaofen.xiaoying.base.BaseActivity;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

public class ImgZoomActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private TextView imageCount;
    private ViewPager imagePager;
    private ArrayList<String> imgs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_zoom);
        initView();
        initdate();
    }

    private void initdate() {
        imgs = getIntent().getStringArrayListExtra("img");
        imagePager.setAdapter(new ImgPagerAda(imgs));
    }

    public void initView() {
        imageCount = findViewById(R.id.tv_num);
        imagePager = findViewById(R.id.image_pager);
        imagePager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        imageCount.setText(imgs.size()+"/"+(position+1));
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

    private  class ImgPagerAda extends PagerAdapter {
        private ArrayList<String> imgs;

        public ImgPagerAda(ArrayList<String> imgs) {
            this.imgs = imgs;
        }

        @Override
        public int getCount() {
            return imgs.size();
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