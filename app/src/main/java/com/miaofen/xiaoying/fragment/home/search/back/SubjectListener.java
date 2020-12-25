package com.miaofen.xiaoying.fragment.home.search.back;

import com.miaofen.xiaoying.fragment.home.search.back.ObserverListener;

/**
 * 项目名称：com.miaofen.xiaoying.fragment.home.search
 * 类描述：被观察者接口
 * 创建人：duzepeng
 * 创建时间：2020/12/9
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface SubjectListener {
    void add(ObserverListener observerListener);
    void notifyObserver(String content);
    void remove(ObserverListener observerListener);

}
