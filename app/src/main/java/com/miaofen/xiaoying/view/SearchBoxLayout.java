package com.miaofen.xiaoying.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.Nullable;

import com.miaofen.xiaoying.R;
import com.miaofen.xiaoying.utils.ToastUtils;

import java.util.List;


/**
 * 项目名称：com.miaofen.xiaoying.view
 * 类描述：自定义搜索框
 * 创建人：duzepeng
 * 创建时间：2020/12/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SearchBoxLayout extends LinearLayout {

    private RelativeLayout show_search;

    private TextView tv_position;

    private View view_division;

    private TextSwitcher textSwitcher;

    private TextView t;

    private LinearLayout linear_switcher;

    private TextSwitcherAnimation textSwitcherAnimation;

    public SearchBoxLayout(Context context) {
        super(context);
        initView(context);
    }

    public SearchBoxLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SearchBoxLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.search_box_layout, this);
        show_search = (RelativeLayout) findViewById(R.id.show_search);
        linear_switcher = (LinearLayout) findViewById(R.id.linear_switcher);
        tv_position = (TextView) findViewById(R.id.tv_position);
        view_division = (View) findViewById(R.id.view_division);
        textSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
    }


    /**
     * 设置输入框滚动内容
     *
     * @param context
     * @param list
     */
    public void setDataList(Context context, List<String> list) {
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                t = new TextView(context);
                Drawable dwLeft = getResources().getDrawable(R.drawable.search_icon);
                dwLeft.setBounds(0, 0, 34, 34);
                t.setCompoundDrawables(dwLeft, null, null, null);
                t.setCompoundDrawablePadding(23);
                t.setTextSize(14);
                t.setTextColor(getResources().getColor(R.color.A959CA7));
                t.setPadding(100, 30, 0, 0);
                return t;
            }
        });

        textSwitcherAnimation = new TextSwitcherAnimation(textSwitcher, list);
        textSwitcherAnimation.create();
    }


    /**
     * 设置当前位置
     *
     * @param position
     */
    public void setPositionText(String position) {
        tv_position.setText(position);
    }


    /**
     * 地区点击事件
     */
    public void setOnClickPosition() {
        show_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * 隐藏
     */
    public void setHie() {
        tv_position.setVisibility(GONE);
        view_division.setVisibility(GONE);
    }


    public LinearLayout getLinearLayout(){
        return linear_switcher;
    }

    public String getTextView(){
        return t.getText().toString();
    }

    public int getMarker(){
        return textSwitcherAnimation.getMarker();
    }

}
