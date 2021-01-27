package com.miaofen.xiaoying.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.miaofen.xiaoying.R;

/**
 * 项目名称：com.miaofen.xiaoying.view
 * 类描述：删除评论弹窗
 * 创建人：duzepeng
 * 创建时间：2021/1/25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class WarningTipsDialog extends BaseDialog{

    private View window;
    private TextView delete_comments_title,tv_warning_cancel,tv_warning_confirm;
    private Long commentId;

    public WarningTipsDialog(Context context) {//warning_tips_back
        super(context);//warning_tips_layout
        window = View.inflate(context, R.layout.warning_tips_layout,null);
        delete_comments_title = window.findViewById(R.id.delete_comments_title);
        tv_warning_cancel = window.findViewById(R.id.tv_warning_cancel);
        tv_warning_confirm = window.findViewById(R.id.tv_warning_confirm);
        dialog.setCancelable(false);

    }

    @Override
    public void onClick(View v) {

    }

    public void showLoading(Long commentId){
        setOnClick(commentId);
        show(window);
    }


    public void setOnClick(Long commentId){
        //取消
        tv_warning_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButton.onCancel();
            }
        });
        //确认
        tv_warning_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButton.onConfirm(commentId);
            }
        });

    }


    public WarningTipsDialog setTipMsg(String msg){
        if(msg==null){
            msg = "";
        }
        delete_comments_title.setText(msg);
        return this;
    }

    public WarningTipsDialog setCancelable(boolean flag){
        super.setCancelable(false);
        return this;
    }


    public interface OnClickButton{
        void onCancel();
        void onConfirm(Long commentId);
    }

    private OnClickButton onClickButton;

    public void setOnClickButton(OnClickButton onClickButton) {
        this.onClickButton = onClickButton;
    }


}


