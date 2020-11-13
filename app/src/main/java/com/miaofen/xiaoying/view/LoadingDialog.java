package com.miaofen.xiaoying.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.miaofen.xiaoying.R;

/**
 * 项目名称：com.miaofen.xiaoying.view
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class LoadingDialog extends BaseDialog{

    private View window;
    private TextView tvMsg;

    public LoadingDialog(Context context) {
        super(context);
        window = View.inflate(context, R.layout.dialog_loading_tip,null);
        tvMsg = window.findViewById(R.id.tv_msg);
        dialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {

    }

    public void show(){
        show(window);
    }

    public LoadingDialog setTipMsg(String msg){
        if(msg==null){
            msg = "";
        }
        tvMsg.setText(msg);
        return this;
    }

    public LoadingDialog setCancelable(boolean flag){
        super.setCancelable(false);
        return this;
    }

}


