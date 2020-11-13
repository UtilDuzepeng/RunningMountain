package com.miaofen.xiaoying.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.miaofen.xiaoying.R;
import com.miaofen.xiaoying.comm.Config;
import com.miaofen.xiaoying.utils.StringUtil;
import com.miaofen.xiaoying.utils.ToastUtils;

/**
 * 项目名称：com.miaofen.xiaoying.view
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public abstract class BaseDialog implements View.OnClickListener{

    protected String TAG = this.getClass().getSimpleName();

    protected Dialog dialog;

    protected WindowManager.LayoutParams lp;
    protected Window dialogWindow;
    protected boolean isShow = false;

    public BaseDialog(Context context) {
        dialog = new Dialog(context, R.style.dialog);
    }

    /**
     * dialog显示
     * @param v：显示的view
     * @param heightScale：显示高度占屏幕设为百分比
     * @param widthScale：显示宽度占屏幕设为百分比
     */
    protected void show(View v, float heightScale, float widthScale) {
        show(v);
        lp = dialogWindow.getAttributes();
        lp.height = (int) (Config.screenHeight * heightScale);
        lp.width = (int) (Config.screenWidth * widthScale);
        dialogWindow.setAttributes(lp);
    }

    /**
     * dialog显示：高度以view的实际高度显示
     * @param v：显示的view
     * @param widthScale：显示宽度占屏幕设为百分比
     */
    protected void show(View v, float widthScale) {
        show(v);
        lp = dialogWindow.getAttributes();
        lp.width = (int) (Config.screenWidth * widthScale);
        dialogWindow.setAttributes(lp);
    }

    /**
     * dialog显示：宽高以自己定义的view的宽高显示
     * @param v：显示的view
     */
    protected void show(View v) {
        if (dialog.isShowing()) {
            return;
        }
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {
            dismiss();
            return;
        }
        dialogWindow = this.dialog.getWindow();
        isShow = true;
        dialog.getWindow().setContentView(v);
        this.dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialog) {
                isShow = false;
                doOnDismiss();
            }
        });
    }

    public BaseDialog setCancelable(boolean flag){
        dialog.setCancelable(flag);
        dialog.setCanceledOnTouchOutside(flag);
        return this;
    }

    // dialog消失后调用的方法
    protected void doOnDismiss() {
    }

    public void dismiss() {
        this.dialog.cancel();
        isShow = false;
    }

    public boolean isShow() {
        return isShow;
    }

    // 显示toast
    protected void showToast(String msg) {
        if (StringUtil.isNull(msg)) {
            return;
        }
        ToastUtils.showToast(msg);
    }
}



