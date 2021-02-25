package com.miaofen.xiaoying.common.data.remote;

import android.util.Log;

import com.miaofen.xiaoying.common.data.bean.CommonResponse;
import com.miaofen.xiaoying.common.data.remote.exception.ResponseException;
import com.miaofen.xiaoying.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 项目名称：com.miaofen.xiaoying.common
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public abstract class CommonObserver<T> implements Observer<CommonResponse<T>> {

    abstract protected void success(T data);

    protected void failure(Throwable e, String errMsg) {
        ToastUtils.showToast(errMsg);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(CommonResponse<T> response) {

        if (response.isSuccessful()) {
            success(response.getData());
        } else {
            if (response.isExpire()) {
                doExpire();
            } else {
                failure(new ResponseException(), response.getMessage());
            }
        }
    }

    private void doExpire() {
        //todo:  登录失效退出账号
    }

    @Override
    public void onError(Throwable e) {
        Log.e("TAG","---------" +e.getMessage());
        Logger.e(e, e.getMessage());
//        failure(e, "网络异常，请稍候重试");
    }

    @Override
    public void onComplete() {

    }
}

