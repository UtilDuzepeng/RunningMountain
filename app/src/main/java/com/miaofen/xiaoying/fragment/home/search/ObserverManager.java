package com.miaofen.xiaoying.fragment.home.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.search
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/9
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ObserverManager implements SubjectListener {
    private static ObserverManager observerManager;
    //观察者接口集合
    private List<ObserverListener> list = new ArrayList<>();

    /**
     * 单例
     */
    public static ObserverManager getInstance(){
        if (null == observerManager){
            synchronized (ObserverManager.class){
                if (null == observerManager){
                    observerManager = new ObserverManager();
                }
            }
        }
        return observerManager;
    }

    /**
     * 加入监听队列
     */
    @Override
    public void add(ObserverListener observerListener) {
        list.add(observerListener);
    }

    /**
     * 通知观察者刷新数据
     */
    @Override
    public void notifyObserver(String content) {
        for (ObserverListener observerListener : list){
            observerListener.observerUpData(content);
        }
    }

    /**
     * 监听队列中移除
     */
    @Override
    public void remove(ObserverListener observerListener) {
        if (list.contains(observerListener)){
            list.remove(observerListener);
        }
    }
}
