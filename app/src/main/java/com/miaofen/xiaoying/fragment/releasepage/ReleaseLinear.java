package com.miaofen.xiaoying.fragment.releasepage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.miaofen.xiaoying.R;

/**
 * 项目名称：com.miaofen.xiaoying.fragment.releasepage
 * 类描述：发布页面
 * 创建人：duzepeng
 * 创建时间：2020/11/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ReleaseLinear extends LinearLayout {

    public ReleaseLinear(Context context) {
        super(context);
        initView(context);
    }

    public ReleaseLinear(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ReleaseLinear(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.release_linear_layout, this);
    }

}
