package com.miaofen.xiaoying.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * 项目名称：com.miaofen.xiaoying.utils
 * 类描述：动画工具
 * 创建人：duzepeng
 * 创建时间：2020/12/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */


public class AnimationUtils {
    public enum AnimationState{
        STATE_SHOW,
        STATE_HIDDEN
    }
    /**
     * 渐隐渐现动画
     * @param view 需要实现动画的对象
     * @param state 需要实现的状态
     * @param duration 动画实现的时长（ms）
     */
    public static void showAndHiddenAnimation(final View view, AnimationState state, long duration){
        float start = 0f;
        float end = 0f;
        if(state == AnimationState.STATE_SHOW){
            end = 1f;
            view.setVisibility(View.VISIBLE);
        } else
        if(state == AnimationState.STATE_HIDDEN){
            start = 1f;
            view.setVisibility(View.INVISIBLE);
        }
        AlphaAnimation animation = new AlphaAnimation(start, end);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
            }
        });
        view.setAnimation(animation);
        animation.start();
    }
}
