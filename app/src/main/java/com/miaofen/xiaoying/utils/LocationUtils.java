package com.miaofen.xiaoying.utils;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * 项目名称：com.miaofen.xiaoying.utils
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class LocationUtils {
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption = null;
    // 定位回调
    public LocationCallBack mLocationCallBack;
    //声明定位回调监听器
    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            Log.i("TAG", "==" + aMapLocation.getLatitude());
            if (mLocationCallBack != null) {
                mLocationCallBack.setLocation(aMapLocation);
            }
        }
    };

    public LocationUtils(Context context) {
        mLocationClient = new AMapLocationClient(context);
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //  获取一次定位结果：默认false 连续定位 徐设置定位间隔 默认采用连续定位模式，时间间隔2000ms
        mLocationOption.setOnceLocation(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //启动定位
        mLocationClient.startLocation();
    }

    public void destory() {
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务
    }

    public void restart() {

        if (mLocationClient == null) {
            return;
        }
        //启动定位
        mLocationClient.startLocation();
    }


    public interface LocationCallBack {
        void setLocation(AMapLocation location);
    }

    public void setmLocationCallBack(LocationCallBack location) {
        mLocationCallBack = location;
    }

}
