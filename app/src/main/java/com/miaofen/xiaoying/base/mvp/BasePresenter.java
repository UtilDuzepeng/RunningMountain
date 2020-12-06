package com.miaofen.xiaoying.base.mvp;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 项目名称：com.miaofen.xiaoying.base.mvp
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class BasePresenter<V extends IView> implements IPresenter{

    protected WeakReference<V> mRootView;
    private CompositeDisposable mCompositeDisposable;

    @SuppressWarnings("unchecked")
    public BasePresenter(V view) {
        this.mRootView = new WeakReference<>(view);
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        unDispose();
    }


    public void addDispose(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        //将所有 Disposable 放入集中处理
        mCompositeDisposable.add(disposable);
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证 Activity 结束时取消所有正在执行的订阅
        }
        mCompositeDisposable = null;
    }
}
