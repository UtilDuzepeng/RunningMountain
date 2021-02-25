package com.miaofen.xiaoying.activity.location;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amap.api.location.AMapLocation;
import com.miaofen.xiaoying.R;
import com.miaofen.xiaoying.base.BaseActivity;
import com.miaofen.xiaoying.utils.LocationUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 地理位置页面
 */
public class LocationActivity extends BaseActivity {

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private static final int PERMISSON_REQUESTCODE = 0;
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    private LocationUtils locationUtils;

    @Override
    public int returnLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
    }

    @Override
    public void initView() {
        super.initView();
        if (locationUtils == null) {
            locationUtils = new LocationUtils(this);
        }
        locationCallBac();
    }

    @Override
    public void initData() {
        super.initData();
    }

    /**
     * 检查权限
     *
     * @param
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        //获取权限列表
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            //list.toarray将集合转化为数组
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        //for (循环变量类型 循环变量名称 : 要被遍历的对象)
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }


    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] paramArrayOfInt) {
        Log.e("TAG", "我执行了" + requestCode);
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {      //没有授权
                showMissingPermissionDialog();              //显示提示信息
                isNeedCheck = false;
            } else {
                locationCallBac();
            }
        }
    }

    private void locationCallBac() {
        locationUtils.setmLocationCallBack(new LocationUtils.LocationCallBack() {
            @Override
            public void setLocation(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        double lat = amapLocation.getLatitude();//获取纬度
                        double lng = amapLocation.getLongitude();//获取经度
                        // 设置当前地图显示为当前位置
                        int locationType = amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        Log.e("TAG", "获取当前定位结果来源 -> :" + locationType);
                        double latitude = amapLocation.getLatitude();//获取纬度
                        Log.e("TAG", "获取纬度 -> :" + latitude);
                        double longitude = amapLocation.getLongitude();//获取经度
                        Log.e("TAG", "获取经度 -> :" + longitude);
                        float accuracy = amapLocation.getAccuracy();//获取精度信息
                        Log.e("TAG", "获取精度信息 -> :" + accuracy);
                        String address = amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                        Log.e("TAG", "地址 -> :" + address);
                        String country = amapLocation.getCountry();//国家信息
                        Log.e("TAG", "国家信息 -> :" + country);
                        String province = amapLocation.getProvince();//省信息
                        Log.e("TAG", "省信息 -> :" + province);
                        String city = amapLocation.getCity();//城市信息
                        Log.e("TAG", "城市信息 -> :" + city);
                        String district = amapLocation.getDistrict();//城区信息
                        Log.e("TAG", "城区信息 -> :" + district);
                        String street = amapLocation.getStreet();//街道信息
                        Log.e("TAG", "街道信息 -> :" + street);
                        String streetNum = amapLocation.getStreetNum();//街道门牌号信息
                        Log.e("TAG", "街道门牌号信息 -> :" + streetNum);
                        String cityCode = amapLocation.getCityCode();//城市编码
                        Log.e("TAG", "城市编码 -> :" + cityCode);
                        String adCode = amapLocation.getAdCode();//地区编码
                        Log.e("TAG", "地区编码 -> :" + adCode);
                        String aoiName = amapLocation.getAoiName();//获取当前定位点的AOI信息
                        Log.e("TAG", "获取当前定位点的AOI信息 -> :" + aoiName);
                        String buildingId = amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                        Log.e("TAG", "获取当前室内定位的建筑物Id -> :" + buildingId);
                        String floor = amapLocation.getFloor();//获取当前室内定位的楼层
                        Log.e("TAG", "获取当前室内定位的楼层 -> :" + floor);
                        int gpsAccuracyStatus = amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                        Log.e("TAG", "获取GPS的当前状态 -> :" + gpsAccuracyStatus);
                        //获取定位时间
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);
                        Log.e("TAG", "时间 -> :" + df);

                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//                        Log.e("AmapError   ", "location Error, ErrCode:"
//                                + amapLocation.getErrorCode() + ", errInfo:"
//                                + amapLocation.getErrorInfo());
                    }
                }
            }
        });

    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }


    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}