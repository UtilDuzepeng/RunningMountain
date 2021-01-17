package com.miaofen.xiaoying.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miaofen.xiaoying.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * 项目名称：com.miaofen.xiaoying.view
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class RefreshLayout extends LinearLayout implements OnRefreshLoadMoreListener {

    private int page = 1;//加载页数

    private RecyclerView recyclerView;

    private SmartRefreshLayout refreshLayout;

    public RefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    public RefreshLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.public_recyclerview_layout, this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        refreshLayout.setDisableContentWhenRefresh(true);//是否在刷新的时候禁止列表的操作
        refreshLayout.setDisableContentWhenLoading(true);//是否在加载的时候禁止列表的操作

    }

    //刷新
    @Override
    public void onRefresh(@NonNull com.scwang.smartrefresh.layout.api.RefreshLayout refreshLayout) {
        page = 1;
        setOnRefresh.refresh(page, 10);
        refreshLayout.finishRefresh();
    }

    //上拉
    @Override
    public void onLoadMore(@NonNull com.scwang.smartrefresh.layout.api.RefreshLayout refreshLayout) {
        page++;
        setOnRefresh.loadMore(page, 10);
        refreshLayout.finishLoadMore();
    }

    public void autoRefresh() {
        refreshLayout.autoRefresh();//自动刷新
    }

    public void setEnableRefresh(boolean enabled) {
        refreshLayout.setEnableRefresh(enabled);//是否启用下来刷新
    }

    public void setEnableLoadMore(boolean enabled) {
        refreshLayout.setEnableLoadMore(enabled);//是否启用上拉加载功能
    }

    //完成刷新
    public void finishRefresh(){
        refreshLayout.finishRefresh();
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public interface SetOnRefresh {
        void refresh(int pager, int size);

        void loadMore(int pager, int size);
    }

    public SetOnRefresh setOnRefresh;

    public void setSetOnRefresh(SetOnRefresh setOnRefresh) {
        this.setOnRefresh = setOnRefresh;
    }

}
