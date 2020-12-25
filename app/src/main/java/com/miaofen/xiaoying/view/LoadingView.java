package com.miaofen.xiaoying.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.miaofen.xiaoying.R;

/**
 * 项目名称：com.miaofen.xiaoying.view
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class LoadingView extends BaseDialog{

    private View window;
    private ProgressBar progressBar;
    private TextView tvMsg;
    private ImageView iv;

    public LoadingView(Context context) {
        super(context);
        window = View.inflate(context, R.layout.loading_view,null);
        progressBar = window.findViewById(R.id.loading_progressbar);
        tvMsg = window.findViewById(R.id.tv);
        iv = window.findViewById(R.id.iv);
        dialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {

    }

    public void showLoading(){
        show(window);
    }

    /**
     * 成功
     */
    public void showSuccess() {
        setTipMsg("加载成功");
        iv.setImageResource(R.mipmap.load_success);
        iv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    /**
     *失败
     */
    public void showFail() {
        setTipMsg("加载失败");
        iv.setImageResource(R.mipmap.load_fail);
        iv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }



    public LoadingView setTipMsg(String msg){
        if(msg==null){
            msg = "";
        }
        tvMsg.setText(msg);
        return this;
    }

    public LoadingView setCancelable(boolean flag){
        super.setCancelable(false);
        return this;
    }

}


