package com.miaofen.xiaoying.utils;

import android.app.Activity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;

/**
 * 项目名称：com.miaofen.xiaoying.utils
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/11/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ActivityManager {

    private volatile static ActivityManager sInstance = null;


    private ActivityManager() {

    }

    public static ActivityManager getInstance() {

        if (sInstance == null) {
            synchronized (ActivityManager.class) {
                if (sInstance == null) {
                    sInstance = new ActivityManager();
                }
            }
        }

        return sInstance;
    }

    private ArrayDeque<Activity> stack = new ArrayDeque<>();

    public void addActivity(Activity activity) {
        stack.push(activity);
    }

    public void removeActivity(Activity activity) {
        stack.remove(activity);
    }

    public void clearActivities() {

        for (Activity activity : stack) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }

    }

    public void removeAboveActivities(@NotNull Class activityClass) {

        try {
            for (Activity activity = stack.peek(); !activity.getClass().equals(activityClass); activity = stack.pop()) {
                activity.finish();
            }
        } catch (Exception e) {

        }

    }
}

