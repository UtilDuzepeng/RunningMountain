package com.miaofen.xiaoying.activity.signup.examine;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.miaofen.xiaoying.R;
import com.miaofen.xiaoying.utils.ToastUtils;
import com.miaofen.xiaoying.view.BaseDialog;

/**
 * 项目名称：com.miaofen.xiaoying.activity.signup.examine
 * 类描述：拒绝内容标签弹窗
 * 创建人：duzepeng
 * 创建时间：2021/1/12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class RefuseDialog extends BaseDialog {

    private View window;
    private EditText ed_yikes;
    private TextView tv_cancel;
    private TextView tv_yes;

    public RefuseDialog(Context context) {
        super(context);
        window = View.inflate(context, R.layout.refuse_dialog, null);
        ed_yikes = window.findViewById(R.id.ed_yikes);
        tv_cancel = window.findViewById(R.id.tv_cancel);
        tv_yes = window.findViewById(R.id.tv_yes);
        tv_cancel.setOnClickListener(view -> {
            onClickRefuse.onCancel();
        });
        tv_yes.setOnClickListener(view -> {
            if (!ed_yikes.getText().toString().isEmpty()) {
                onClickRefuse.onYes(ed_yikes.getText().toString());
            } else {
                ToastUtils.showToast("拒绝内容不能为空");
            }

        });
        dialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {

    }

    public void showLoading() {
        show(window);
    }

    public RefuseDialog setCancelable(boolean flag) {
        super.setCancelable(false);
        return this;
    }

    public interface OnClickRefuse {
        void onYes(String reason);

        void onCancel();
    }

    private OnClickRefuse onClickRefuse;

    public void setOnClickRefuse(OnClickRefuse onClickRefuse) {
        this.onClickRefuse = onClickRefuse;
    }
}


