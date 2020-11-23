package com.miaofen.xiaoying.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miaofen.xiaoying.R;
import com.miaofen.xiaoying.adapter.CommentRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.miaofen.xiaoying.view
 * 类描述：首页详情评论列表
 * 创建人：duzepeng
 * 创建时间：2020/11/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class CommentListLayout extends LinearLayout {

    private RecyclerView conmment;

    private CommentRecyclerViewAdapter mAdapter;

    private List<String> list = new ArrayList<>();

    public CommentListLayout(Context context) {
        super(context);
        initView(context);
    }

    public CommentListLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CommentListLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.comment_layout, this);
        conmment = (RecyclerView)findViewById(R.id.conmment_recycler);
        conmment.setLayoutManager(new LinearLayoutManager(context));
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        mAdapter = new CommentRecyclerViewAdapter(R.layout.comment_item,list,context);
        conmment.setAdapter(mAdapter);
    }



}
